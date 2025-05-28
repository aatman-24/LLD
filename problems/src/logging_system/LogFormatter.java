package logging_system;

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
