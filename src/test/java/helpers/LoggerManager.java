package helpers;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class LoggerManager {
    private static final BufferedLogAppender bufferedLogAppender = new BufferedLogAppender();
    private static volatile boolean loggingInitialized = false;
    private static final Object LOCK = new Object();

    static {
        initializeLogging();
    }

    private static void initializeLogging() {
        synchronized (LOCK) {
            if (!loggingInitialized) {
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
} 