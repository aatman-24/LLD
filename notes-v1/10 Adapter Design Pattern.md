Adapter Design Pattern (Structural Pattern)
---

> > It allows objects with incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by converting the interface of a class into another interface that clients expect.

### When to use?

Incompatible Interfaces: When you have two classes with incompatible interfaces but you need to use them together.
Reusing Legacy Code: When you want to reuse existing classes that do not have the required interface.
Integration of Systems: When integrating third-party libraries or systems that don’t match your current system’s interface.
Conversion from one class to another: When we want to convert one interface by encapsulating new logic. Ex: XML to JSON 

### Which problem it solves?

> The Adapter pattern solves the problem of interface incompatibility. 
> It allows classes to work together that couldn’t otherwise because of incompatible interfaces. 
> By providing a wrapper that translates one interface into another, it enables the integration of classes into existing systems without modifying their source code.

### Standard Template:

Target: Defines the domain-specific interface that the Client uses.
Adaptee: Contains some useful behavior, but its interface is incompatible with the Client. [The new requirement you want to integrate with a client]. 
Adapter: Implements the Target interface and translates the calls from the Client to the Adaptee.
Client: Uses the Target interface to interact with the Adaptee through the Adapter.

In short, Client make calls on Target(Adapter(Impl)), Adapter make calls to Adaptee to perform a certain task. 

// Target interface

    public interface Target {
        void request();
    }

// Adaptee class with an incompatible interface

    public class Adaptee {
        public void specificRequest() {
            System.out.println("Specific request");
        }
    }

// Adapter class that makes the Adaptee compatible with the Target interface

    public class Adapter implements Target {
        private Adaptee adaptee;
    
        public Adapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }
    
        @Override
        public void request() {
            adaptee.specificRequest();
        }
    }

// Client code

    public class Client {
        public static void main(String[] args) {
            Adaptee adaptee = new Adaptee();
            Target adapter = new Adapter(adaptee);
            adapter.request(); // This will call adaptee.specificRequest()
        }
    }

### Real Life Example:

> Suppose you have an existing audio player that plays MP3 files, but you now need to integrate a new library that plays MP4 files and another library which play VLC players. 
> The interfaces of the two media players are incompatible. We can use the Adapter pattern to make them work together.

// Adaptee (New Library for integration)

    public class Mp4Player {
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }
    
    public class VlcPlayer {
        public void playVLC(String fileName) {
            System.out.println("Playing VLC.." + fileName);
        } 
    }

// Client

    public interface MediaPlayer {
        void play(String audioType, String fileName);
    }
    
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
    
    
    public class MainInvoker {
        public static void main(String[] args) {
            AudioPlayer audioPlayer = new AudioPlayer();
            audioPlayer.play("mp3", "beyond_the_horizon.mp3");
            audioPlayer.play("mp4", "alone.mp4");
            audioPlayer.play("vlc", "far_far_away.vlc");
        }
    }

### Explanation:

Existing:

> AudioPlayer has a single responsibility of calling the play method of MediaPlayer interface. 
> Currently, MediaPlayer is having one behavior to play something and right now it can play Mp3 file.

New Requirement:

> Now we have two more formats to integrate Mp4Player & VLCPlayer.
> But the problem is that both libraries are already implemented we can't make any change into it. 

    Inside AudioPlayer class, we can do this as well ?
    
          // Mp4Adapter is providing support to play mp4 file formats
            else if (audioType.equalsIgnoreCase("mp4")) {
                Mp4Player mp4 = new Mp4Player();
                mp4.play(fileName);
            }

No, the problem is that AudioPlayer class has only one responsibility or behaviour to call play() method inside MediaPlayer. We can't call any other
class method directly. Open-closed principle is violated if we do that way. It means we need something where we can call the same logic but it implements this MediaPlayer interface.. right ? 
So that's what Adapter design do. 