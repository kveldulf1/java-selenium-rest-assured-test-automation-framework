package helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigurationReader is a singleton class that reads configuration properties
 * from a file.
 * It uses the Singleton pattern to ensure that only one instance of the class
 * is created.
 */
public class ConfigurationReader {
    // Private static volatile instance to ensure thread safety. Volatile ensures
    // that the instance is fully constructed before any thread can access it.
    private static volatile ConfigurationReader instance;
    private final Properties properties;

    private ConfigurationReader() {
        properties = loadProperties();
    }

    public static ConfigurationReader getInstance() {
        if (instance == null) {
            // Synchronized block to ensure thread safety.
            // This ensures that only one thread can create an instance of the class at a time.
            synchronized (ConfigurationReader.class) {
                if (instance == null) {
                    instance = new ConfigurationReader();
                }
            }
        }
        return instance;
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        String configurationPath = "src/test/resources/configurations/configuration.properties";

        
        try (BufferedReader reader = new BufferedReader(new FileReader(configurationPath))) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration: " + e.getMessage());
        }
        return props;
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            throw new RuntimeException("browser not specified in configuration.properties");
        }
        return browser;
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public String getBaseUrl() {
        String url = properties.getProperty("baseURL");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("baseURL not specified in configuration.properties");
        }
        return url;
    }
}
