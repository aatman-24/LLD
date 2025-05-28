package logging_system;

import logging_system.logappender.LogAppender;
import lombok.*;

/**
 * Configuration class for the logging system.
 * This class holds the configuration settings that control how log messages
 * are processed, formatted, and output.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogConfig {
    /** The minimum log level that should be processed */
    private LogLevel logLevel;
    
    /** The formatter used to convert log messages to strings */
    private LogFormatter logFormatter;
    
    /** The appender used to output the log messages */
    private LogAppender logAppender;
    
    /**
     * Creates a new LogConfig with default values:
     * LogLevel.INFO, DefaultLogFormatter, and ConsoleLogAppender.
     *
     * @return a new LogConfig with default values
     */
    public static LogConfig getDefault() {
        return new LogConfig(
            LogLevel.INFO,
            new DefaultLogFormatter(),
            new logging_system.logappender.ConsoleLogAppender()
        );
    }
}
