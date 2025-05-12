package bridge_design_pattern;

public class RadioDevice implements Device{

    @Override
    public void turnOn() {
        System.out.println("Radio is turned on!");
    }

    @Override
    public void increaseVolume() {
        System.out.println("Volume is up for radio");
    }

    @Override
    public void decreaseVolume() {
        System.out.println("Volume is down for radio");
    }
}
