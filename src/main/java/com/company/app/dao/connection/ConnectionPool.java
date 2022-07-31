package com.company.app.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;

    private final static int DEFAULT_POOL_SIZE = 16;

    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    ConnectionPool(String driver, String url, String user, String password) {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();

        try {
            Class.forName(driver);
            logger.info("Databased driver loaded");
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnections.offer(new ProxyConnection(connection));
                logger.info("Connection created");
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connection created");
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;

        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Failed to get Connection", e);
        }
        return connection;
    }
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxy && givenAwayConnections.remove(connection)) {
            freeConnections.offer(proxy);
        } else {
            logger.warn("Returned not proxy connection");
        }
    }
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.error("Failed to destroy the pool", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("Driver ={} deregistered", driver);
            } catch (SQLException e) {
                logger.error("Failed deregister database drivers", e);
            }
        });
    }
}
