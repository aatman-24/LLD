package logging_system;

import logging_system.logappender.ConsoleLogAppender;

/**
 * Driver class to demonstrate the usage of the logging system.
 * This class shows how to configure the logger and log messages at different levels.
 */
public class Driver {
    
    /**
     * Main method to demonstrate logging functionality.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Get the logger instance
        Logger logger = Logger.getInstance();

        // Log messages with default configuration (INFO level and above)
        logger.info("This is an information message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        // Debug message won't be shown with default INFO level
        logger.debug("This debug message won't be shown with default configuration");

        // Change configuration to show DEBUG level messages
        LogConfig config = new LogConfig(
            LogLevel.DEBUG, 
            new DefaultLogFormatter(), 
            new ConsoleLogAppender()
        );
        logger.setLogConfig(config);

        // Now debug messages will be shown
        logger.debug("This is a debug message");
        logger.info("This is another information message with debug level enabled");
    }
}
