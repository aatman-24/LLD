package chain_of_responsibility_design_pattern;

public abstract class LogProcessor {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    LogProcessor nextLoggerProcessor;

    // Used Constructor to set the next processor we can use the setter as well.
    LogProcessor(LogProcessor loggerProcessor) {
        this.nextLoggerProcessor = loggerProcessor;
    }

    public void log(int logLevel, String message) {
        if (nextLoggerProcessor != null) {
            nextLoggerProcessor.log(logLevel, message);
        }
    }

}
