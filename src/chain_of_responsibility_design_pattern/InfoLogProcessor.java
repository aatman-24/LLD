package chain_of_responsibility_design_pattern;

public class InfoLogProcessor extends LogProcessor{

    InfoLogProcessor(LogProcessor nexLogProcessor){
        super(nexLogProcessor);
    }

    public void log(int logLevel,String message){

        // Only React on this
        if(logLevel == INFO) {
            System.out.println("INFO: " + message);
        } else{
            // pass to next processor/handler.
            super.log(logLevel, message);
        }

    }
}
