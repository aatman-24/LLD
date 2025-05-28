package logging_system;

import lombok.*;
import java.time.LocalDateTime;

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
