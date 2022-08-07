package com.company.app.dao.connection;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
@Log4j2
public class ConnectionPool {

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;

    private final static int DEFAULT_POOL_SIZE = 16;


    ConnectionPool(String driver, String url, String user, String password) {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();

        try {
            Class.forName(driver);
            log.info("Databased driver loaded");
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnections.offer(new ProxyConnection(connection));
                log.info("Connection created");
            }

        } catch (ClassNotFoundException | SQLException e) {
            log.error("Failed to connection created");
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;

        try {
            if (freeConnections.size() == 0){
                throw new RuntimeException("Out of free database connections");
            }
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
            log.info("Connection received");
            if(freeConnections.size() + givenAwayConnections.size() != DEFAULT_POOL_SIZE){
                throw new RuntimeException("Database connection lost");
            }
        } catch (InterruptedException e) {
            log.error("Failed to get Connection", e);
        }
        return connection;
    }
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxy && givenAwayConnections.remove(connection)) {
            freeConnections.offer(proxy);
        } else {
            log.warn("Returned not proxy connection");
            throw new RuntimeException("Returned not proxy connection");
        }
    }
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                log.error("Failed to destroy the pool", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                log.info("Driver ={} deregistered", driver);
            } catch (SQLException e) {
                log.error("Failed deregister database drivers", e);
            }
        });
    }
}
