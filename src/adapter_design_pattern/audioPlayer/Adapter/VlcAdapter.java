package adapter_design_pattern.audioPlayer.Adapter;

import adapter_design_pattern.audioPlayer.Adaptee.VlcPlayer;
import adapter_design_pattern.audioPlayer.Client.MediaPlayer;

public class VlcAdapter implements MediaPlayer {

    private VlcPlayer vlcPlayer;

    public VlcAdapter() {
        this.vlcPlayer = new VlcPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVLC(fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
