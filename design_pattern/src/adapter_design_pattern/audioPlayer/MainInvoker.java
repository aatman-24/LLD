package adapter_design_pattern.audioPlayer;

import adapter_design_pattern.audioPlayer.Client.AudioPlayer;

public class MainInvoker {
    public static void main(String[] args) {
            AudioPlayer audioPlayer = new AudioPlayer();
            audioPlayer.play("mp3", "beyond_the_horizon.mp3");
            audioPlayer.play("mp4", "alone.mp4");
            audioPlayer.play("vlc", "far_far_away.vlc");
        }
}
