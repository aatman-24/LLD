package adapter_design_pattern.audioPlayer.Adaptee;

// Adaptee class with an incompatible interface
public class Mp4Player {
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
