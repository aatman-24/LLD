package bridge_design_pattern;

public class ConfigurableRemoteControl extends RemoteControl{

    // Allowed to have any configured device for this remote.
    public ConfigurableRemoteControl(Device device) {
        super(device);
    }

    @Override
    public void turnOn() {
        this.device.turnOn();
    }

    @Override
    public void increaseVolume() {
        this.device.increaseVolume();
    }

    @Override
    public void decreaseVolume() {
        this.device.decreaseVolume();
    }
}
