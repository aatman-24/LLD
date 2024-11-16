package command_design_pattern;

public class AirConditionerReceiver {

    boolean isOn;
    int temperature;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void turnOnAC() {
        isOn = true;
        System.out.println("AC is On");
    }

    public void turnOffAC() {
        isOn = false;
        System.out.println("AC is Off");
    }

}
