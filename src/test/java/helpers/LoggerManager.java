package helpers;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * LoggerManager is a singleton class that manages the logging system.
 * It ensures that logs are properly initialized and managed across tests.
 */
public class LoggerManager {
    // Private static final BufferedLogAppender instance to ensure thread safety.
    // It is necessary to print sequentially logs for tests executed in parallel.
    private static final BufferedLogAppender bufferedLogAppender = new BufferedLogAppender();

    // Private static volatile boolean loggingInitialized to ensure thread safety.
    private static volatile boolean loggingInitialized = false;

    // Private static final Object LOCK to ensure thread safety.
    private static final Object LOCK = new Object();

    // Private static final String DEFAULT_TEST_NAME to ensure thread safety.
    private static final String DEFAULT_TEST_NAME = "GlobalSetup";

    static {
        // Initialize logging when the class is loaded.
        initializeLogging();
    }

    private static void initializeLogging() {
        // Synchronized block to ensure thread safety.
        // This ensures that only one thread can initialize logging at a time.
        synchronized (LOCK) {
            if (!loggingInitialized) {
                // Set default test name for setup/configuration logs
                MDC.put("testName", DEFAULT_TEST_NAME);

                // Start the bufferedLogAppender
                bufferedLogAppender.start();

                // Get the root logger and remove existing appenders
                Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
                rootLogger.detachAndStopAllAppenders();

                // Add the bufferedLogAppender to the root logger
                rootLogger.addAppender(bufferedLogAppender);

                // Set loggingInitialized to true to indicate that logging has been initialized
                loggingInitialized = true;
            }
        }
    }

    /**
     * Get a logger for a specific class.
     * 
     * @param clazz The class to get a logger for.
     * @return The logger for the class.
     */
    public static Logger getLogger(Class<?> clazz) {
        return (Logger) LoggerFactory.getLogger(clazz);
    }

    /**
     * Get and clear the logs for a specific test.
     * 
     * @param testName The name of the test.
     * @return The logs for the test as a string.
     */
    public static String getAndClearLogs(String testName) {
        return bufferedLogAppender.getAndClearLogs(testName);
    }

    /**
     * Print the logs for the default test.
     */
    public static void printGlobalLogs() {
        System.out.println(getAndClearLogs(DEFAULT_TEST_NAME));
    }
}