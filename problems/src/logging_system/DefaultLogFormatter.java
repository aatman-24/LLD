package logging_system;

import java.time.format.DateTimeFormatter;

/**
 * Default implementation of LogFormatter that formats log messages with timestamp, log level, and message.
 * The format is: [timestamp] [LOG_LEVEL] message
 */
public class DefaultLogFormatter extends LogFormatter {
    /** Formatter for the timestamp in the log message */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    /**
     * Formats a log message into a string with the format: [timestamp] [LOG_LEVEL] message
     *
     * @param logMessage the log message to format
     * @return a formatted string containing the timestamp, log level, and message
     */
    @Override
    public String format(LogMessage logMessage) {
        String formattedTimestamp = logMessage.getLocalDateTime().format(DATE_FORMATTER);
        return String.format("[%s] [%s] %s",
                formattedTimestamp,
                logMessage.getLogLevel().name(),
                logMessage.getMessage());
    }
}
