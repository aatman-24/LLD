#### Design Problem: Logging Framework

---

[Problems](https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/logging-framework.md)

#### Requirements

```tex
- The logging framework should support different log levels, such as DEBUG, INFO, WARNING, ERROR, and FATAL.
- It should allow logging messages with a timestamp, log level, and message content.
- The framework should support multiple output destinations, such as console, file, and database.
- It should provide a configuration mechanism to set the log level and output destination.
- The logging framework should be thread-safe to handle concurrent logging from multiple threads.
- It should be extensible to accommodate new log levels and output destinations in the future.
```

### Solution

<u>Identify Flow</u>

> We obtain an instance of a logger and use it within our class. Log statements are added wherever necessary, based on a specified log level. If the given level has a higher priority than the current log level, the log message is appended. Additionally, we should be able to select the type of log appender (e.g., console, file) and customize the log format when writing to the target resource. This configuration can be managed using getters and setters.

<u>Entity LookUp</u>

```tex
Logger
LogAppender
LogConfig
LogFormatter
LogMessage
LogLevel - ENUM
```

<u>Relationship Lookup</u>

```tex
Logger has a LogConfig, which keep track of Logger configuration(logLevel, logAppender)
```

<u>Applying SOLID Principle | Design  Patterns</u>

```tex
Logger => Singleton

Design Pattern Bridge: We have two independent attribute/factor logLevel and logDestination, we cannot create 
subclasses for each combination. 

So separate out the logAppender from main class, and attach to main class 
with "has-a" relationship, so we can change that by runtime. And we keep logLevel
within the Logger class. 

ILogDestination which we renamed to "LogAppender"

LogAppender
    ConsoleAppender
    DatabaseAppender
    FileAppender
```

<u>Class Diagram</u>

```tex
Logger
+ logConfig: LogConfig
+ logAppender: LogAppender
--
- Logger(): void, by default set LogConfig
+ getInstance(): Logger
+ setlogAppender(LogAppender logAppender): void
+ setLogLevel(LogLevel logLevel): void
- log(Loglevel loglevel, String content): void
+ debug(String content): void
+ info(String content): void
+ warn(String content): void
+ error(String content): void
+ fatal(String content): void

LogAppender
+ append(LogMessage logMessage): void

ConsoleAppender
+ append(LogMessage logMessage): void

FileAppender
+ FileAppender(String filePath): void
+ append(LogMessage logMessage): void

DatabaseAppender
+ DatabaseAppender(String jdbcUrl, String username, String password)
+ append(LogMessage logMessage): void

LogConfig
+ logLevel: LogLevel // current level of logger
+ logAppender: LogAppender

LogMessage
+ logLevel: LogLevel
+  content: String

LogLevel
+ DEBUG
+ INFO
+ WARNING
+ ERROR
+ FATAL
```

#### Source Code

```java
package logging_system;

/**
 * Defines the severity levels for log messages.
 * The levels are ordered from least to most severe:
 * DEBUG < INFO < WARN < ERROR
 */
public enum LogLevel {
    /**
     * Detailed information, typically of interest only when diagnosing problems.
     */
    DEBUG,
    
    /**
     * Confirmation that things are working as expected.
     */
    INFO,
    
    /**
     * An indication that something unexpected happened, but the application is still working.
     */
    WARN,
    
    /**
     * An error event that might still allow the application to continue running.
     */
    ERROR
}



/**
 * Represents a log message with its associated metadata.
 * This class encapsulates all the information needed to log a message,
 * including the log level, message content, and timestamp.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
public class LogMessage {
    /** The severity level of the log message */
    private LogLevel logLevel;
    
    /** The actual message content */
    private String message;
    
    /** The timestamp when the log message was created */
    private LocalDateTime localDateTime;

    /**
     * Creates a new log message with the specified log level and message.
     * The timestamp is automatically set to the current time.
     *
     * @param logLevel the severity level of the log message
     * @param message the message content
     */
    public LogMessage(LogLevel logLevel, String message) {
        this.logLevel = logLevel;
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}



/**
 * Abstract base class for all log formatters.
 * Implementations of this class are responsible for formatting log messages
 * according to specific formatting rules.
 */
public abstract class LogFormatter {

    /**
     * Formats a log message into a string representation.
     *
     * @param logMessage the log message to format
     * @return a formatted string representation of the log message
     */
    public abstract String format(LogMessage logMessage);
}

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

/**
 * Main logger class that provides methods to log messages at different severity levels.
 * This class follows the Singleton pattern to ensure consistent logging configuration
 * throughout the application.
 */
public class Logger {
    /** Singleton instance of the logger */
    private static final Logger logger = null;
    
    /** Configuration for this logger instance */
    private LogConfig logConfig;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes with default configuration.
     */
    private Logger() {
        this.logConfig = LogConfig.getDefault();
    }

    /**
     * Updates the logger configuration.
     *
     * @param logConfig the new log configuration to use
     */
    public void setLogConfig(LogConfig logConfig) {
        if (logConfig == null) {
            throw new IllegalArgumentException("LogConfig cannot be null");
        }
        this.logConfig = logConfig;
    }

    /**
     * Internal method to process and output log messages based on the current configuration.
     *
     * @param logMessage the message to log
     */
    private void log(LogMessage logMessage) {
        if (logMessage == null) {
            return;
        }
        if (logMessage.getLogLevel().ordinal() >= logConfig.getLogLevel().ordinal()) {
            logConfig.getLogAppender().append(logMessage);
        }
    }

    /**
     * Logs a message at DEBUG level.
     *
     * @param content the message to log
     */
    public void debug(String content) {
        log(new LogMessage(LogLevel.DEBUG, content));
    }

    /**
     * Logs a message at INFO level.
     *
     * @param content the message to log
     */
    public void info(String content) {
        log(new LogMessage(LogLevel.INFO, content));
    }

    /**
     * Logs a message at WARN level.
     *
     * @param content the message to log
     */
    public void warn(String content) {
        log(new LogMessage(LogLevel.WARN, content));
    }

    /**
     * Logs a message at ERROR level.
     *
     * @param content the message to log
     */
    public void error(String content) {
        log(new LogMessage(LogLevel.ERROR, content));
    }

    /**
     * Returns the singleton instance of the Logger.
     *
     * @return the logger instance
     */
    public static Logger getInstance() {
        if (logger == null) {
            return new Logger();
        }
        return logger;
    }
}


```
