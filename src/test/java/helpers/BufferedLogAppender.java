package helpers;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.MDC;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Collections;
import java.util.Set;

public class BufferedLogAppender extends AppenderBase<ILoggingEvent> {
    private final ConcurrentMap<String, StringBuilder> logBuffers = new ConcurrentHashMap<>();
    private static final Set<String> processedLogs = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Override
    protected void append(ILoggingEvent event) {
        String testName = MDC.get("testName");
        if (testName != null) {
            String logKey = testName + event.getFormattedMessage();
            if (processedLogs.add(logKey)) {
                logBuffers.computeIfAbsent(testName, k -> new StringBuilder())
                         .append(String.format("[%s] [%s] %s%n", 
                             event.getLevel(),
                             testName, 
                             event.getFormattedMessage()));
            }
        }
    }

    public String getAndClearLogs(String testName) {
        StringBuilder logs = logBuffers.remove(testName);
        if (logs != null) {
            processedLogs.removeIf(key -> key.startsWith(testName));
            return logs.toString();
        }
        return "";
    }
} 