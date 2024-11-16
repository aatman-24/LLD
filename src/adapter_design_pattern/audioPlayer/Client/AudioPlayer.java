package adapter_design_pattern.audioPlayer.Client;

import adapter_design_pattern.audioPlayer.Adapter.Mp4Adapter;
import adapter_design_pattern.audioPlayer.Adapter.VlcAdapter;

public class AudioPlayer implements MediaPlayer {

    private MediaPlayer mediaPlayer;

    @Override
    public void play(String audioType, String fileName) {

        // Inbuilt support to play mp3 music files
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }

        // Mp4Adapter is providing support to play mp4 file formats
        else if (audioType.equalsIgnoreCase("mp4")) {
            mediaPlayer = new Mp4Adapter();
            mediaPlayer.play(audioType, fileName);
        }

        // VlcPlayer is providing support to play vlc file formats
        else if (audioType.equalsIgnoreCase("vlc")) {
            mediaPlayer = new VlcAdapter();
            mediaPlayer.play(audioType, fileName);
        }

        else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }

    }
}
