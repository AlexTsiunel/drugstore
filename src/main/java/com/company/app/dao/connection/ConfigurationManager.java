package com.company.app.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigurationManager {
    INSTANCE;

    public static final String CONFIG_FILE = "/aplication.properties";
    Properties properties;
    private Logger logger = LogManager.getLogger(ConfigurationManager.class);


    private ConfigurationManager() {

        properties = new Properties();
        try( InputStream in = getClass().getResourceAsStream(CONFIG_FILE)) {
            properties.load(in);
            logger.info("Readed properties file");
        } catch (IOException e) {
            logger.error("Failed to reade the properties file", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
