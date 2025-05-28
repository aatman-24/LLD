package logging_system.logappender;

import logging_system.LogMessage;

/**
 * Interface for appending log messages to various output destinations.
 * Implementations of this interface handle the actual writing of log messages
 * to different targets like console, files, or remote services.
 */
public interface LogAppender {

    /**
     * Appends a log message to the output destination.
     *
     * @param logMessage the log message to append
     */
    void append(LogMessage logMessage);
}
