package bridge_design_pattern;

public class BridgeDesignClient {

    public static void run() {

        ConfigurableRemoteControl tvRemote = new ConfigurableRemoteControl(new TvDevice());
        tvRemote.turnOn();
        tvRemote.increaseVolume();

        ConfigurableRemoteControl radioRemote = new ConfigurableRemoteControl(new RadioDevice());
        radioRemote.turnOn();
        radioRemote.increaseVolume();

    }
}
