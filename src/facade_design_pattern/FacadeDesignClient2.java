package 
facade_design_pattern;

public class FacadeDesignClient2 {

    public static void run() {

        DVDPlayer dvdPlayer = new DVDPlayer();
        Lights lights = new Lights();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();

       HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
       homeTheaterFacade.watchMovie("spider-man");
       homeTheaterFacade.endMovie();

    }

}
