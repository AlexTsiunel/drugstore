package com.company.app.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.Connection;

public class DataSource implements Closeable {
    private static DataSource INSTANCE;
    private ConnectionPool connectionPool;
    private final String url;
    private final String password;
    private final String user;
    private final String driver;

    private static Logger logger = LogManager.getLogger(DataSource.class);

    private DataSource() {
        url = ConfigurationManager.INSTANCE.getProperty("db.url");
        password = ConfigurationManager.INSTANCE.getProperty("db.password");
        user = ConfigurationManager.INSTANCE.getProperty("db.user");
        driver = ConfigurationManager.INSTANCE.getProperty("db.driver");

    }

    public Connection getConnection() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool(driver, url, user, password);
            logger.info("Connection pool initialized");
        }
        return connectionPool.getConnection();
    }

    ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public static DataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataSource();
        }
        return INSTANCE;
    }

    @Override
    public void close() {
        if (connectionPool != null) {
            connectionPool.destroyPool();
            connectionPool = null;
            logger.info("Connection poll destroyed");
        }
    }
}
