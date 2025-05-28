package logging_system.logappender;

import logging_system.LogMessage;

/**
 * LogAppender implementation that writes log messages to the standard output (console).
 * This is the default appender used by the logging system.
 */
public class ConsoleLogAppender implements LogAppender {
    
    /**
     * Writes the log message to the standard output (console).
     *
     * @param logMessage the log message to be written to console
     */
    @Override
    public void append(LogMessage logMessage) {
        if (logMessage != null && logMessage.getMessage() != null) {
            System.out.println(logMessage.getMessage());
        }
    }
}
