package adapter_design_pattern.audioPlayer.Adaptee;

public class VlcPlayer {

    public void playVLC(String fileName) {
        System.out.println("Playing VLC.." + fileName);
    }

}
