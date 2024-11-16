package chain_of_responsibility_design_pattern;

public class CResponsibiltyDesignClient {

    public static void run() {

        LogProcessor logObject = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));
        logObject.log(LogProcessor.ERROR, "exception happens");
        logObject.log(LogProcessor.DEBUG, "need to debug this ");
        logObject.log(LogProcessor.INFO, "just for info ");
    }
}
