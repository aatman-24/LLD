package bridge_design_pattern;

public class TvDevice implements Device{

    @Override
    public void turnOn() {
        System.out.println("Tv is turned on!");
    }

    @Override
    public void increaseVolume() {
        System.out.println("Volume is up for tv");
    }

    @Override
    public void decreaseVolume() {
        System.out.println("Volume is down for tv");
    }
}
