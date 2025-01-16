package helpers;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggerManager {
    private static final BufferedLogAppender bufferedLogAppender = new BufferedLogAppender();
    private static volatile boolean loggingInitialized = false;
    private static final Object LOCK = new Object();
    private static final String DEFAULT_TEST_NAME = "GlobalSetup";

    static {
        initializeLogging();
    }

    private static void initializeLogging() {
        synchronized (LOCK) {
            if (!loggingInitialized) {
                // Set default test name for setup/configuration logs
                MDC.put("testName", DEFAULT_TEST_NAME);
                
                bufferedLogAppender.start();
                Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
                rootLogger.detachAndStopAllAppenders(); // Remove existing appenders
                rootLogger.addAppender(bufferedLogAppender);
                loggingInitialized = true;
            }
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        return (Logger) LoggerFactory.getLogger(clazz);
    }

    public static String getAndClearLogs(String testName) {
        return bufferedLogAppender.getAndClearLogs(testName);
    }

    public static void printGlobalLogs() {
        System.out.println(getAndClearLogs(DEFAULT_TEST_NAME));
    }
} 