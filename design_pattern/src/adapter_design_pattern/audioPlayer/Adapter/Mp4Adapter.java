package adapter_design_pattern.audioPlayer.Adapter;

import
adapter_design_pattern.audioPlayer.Adaptee.Mp4Player;
import
adapter_design_pattern.audioPlayer.Client.MediaPlayer;

// Adapter class that makes the Mp4Player compatible with the MediaPlayer interface
public class Mp4Adapter implements MediaPlayer {

    public Mp4Player mp4Player;

    public Mp4Adapter() {
        this.mp4Player = new Mp4Player();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player.playMp4(fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
