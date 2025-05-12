package 
facade_design_pattern;

import 
state_design_pattern.VendingMachine;

public class FacadeDesignClient {

    public static void run() {

       // Client want to watch movie it needs to perform all these steps. What if we provide single method to do it ? and also remove all
//        these dependency and replace with one dependency ?

        DVDPlayer dvdPlayer = new DVDPlayer();
        Lights lights = new Lights();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();

        dvdPlayer.on();
        dvdPlayer.play("Spider-man");


        projector.on();
        projector.wideScreenMode();

        lights.on();
        lights.dim(10);

        soundSystem.on();
        soundSystem.setVolume(5);


        // and similar stop for endMovie.
    }
}
