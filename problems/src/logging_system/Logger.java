package logging_system;

import logging_system.logappender.ConsoleLogAppender;

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
