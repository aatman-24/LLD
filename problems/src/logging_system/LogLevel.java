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
