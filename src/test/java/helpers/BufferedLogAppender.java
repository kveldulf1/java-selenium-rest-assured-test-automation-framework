package helpers;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.MDC;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Collections;
import java.util.Set;

/**
 * A custom Logback appender that buffers logs for each test.
 * This implementation is helpful when running tests in parallel.
 * Thanks to it you can see the logs for each test sequentially in the console.
 */
public class BufferedLogAppender extends AppenderBase<ILoggingEvent> {
    // ConcurrentMap to store logs for each test
    private final ConcurrentMap<String, StringBuilder> logBuffers = new ConcurrentHashMap<>();
    // Set to store processed logs to avoid duplicates
    private static final Set<String> processedLogs = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Override
    protected void append(ILoggingEvent event) {
        // Get the test name from the MDC
        String testName = MDC.get("testName");
        if (testName != null) {
            String logKey = testName + event.getFormattedMessage();
            if (processedLogs.add(logKey)) {
                String logMessage = String.format("[%s] [%s] %s%n",
                        event.getLevel(),
                        testName,
                        event.getFormattedMessage());
                // Apply red color for ERROR level logs
                if (event.getLevel() == ch.qos.logback.classic.Level.ERROR) {
                    logMessage = "\u001B[31m" + logMessage + "\u001B[0m";
                }
                // Append the log message to the buffer for the current test
                logBuffers.computeIfAbsent(testName, k -> new StringBuilder())
                        .append(logMessage);
            }
        }
    }

    /**
     * This method ensures that each log message is only processed and displayed once
     * per test, preventing duplicate log entries.
     */
    public String getAndClearLogs(String testName) {
        StringBuilder logs = logBuffers.remove(testName);
        if (logs != null) {
            processedLogs.removeIf(key -> key.startsWith(testName));
            return logs.toString();
        }
        // If no logs are found for the test, return an empty string
        return "";
    }
}