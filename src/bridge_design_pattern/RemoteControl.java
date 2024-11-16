package bridge_design_pattern;


abstract class RemoteControl {

    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    abstract public void turnOn();
    abstract public void increaseVolume();
    abstract public void decreaseVolume();
}
