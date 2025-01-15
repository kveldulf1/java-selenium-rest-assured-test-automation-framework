package helpers;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.MDC;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BufferedLogAppender extends AppenderBase<ILoggingEvent> {
    private final ConcurrentMap<String, StringBuilder> logBuffers = new ConcurrentHashMap<>();

    @Override
    protected void append(ILoggingEvent event) {
        String testName = MDC.get("testName");
        if (testName != null) {
            logBuffers.computeIfAbsent(testName, k -> new StringBuilder())
                      .append(event.getFormattedMessage())
                      .append(System.lineSeparator());
        }
    }

    public String getAndClearLogs(String testName) {
        return logBuffers.remove(testName).toString();
    }
} 