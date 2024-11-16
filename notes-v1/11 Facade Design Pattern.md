### Facade Design Pattern
>> The Facade Design Pattern provides a simplified interface to a complex subsystem. It is used to hide the complexities of the system and provide an easy-to-use interface.

---

### When to use?

> When you want to provide a simple interface to a complex subsystem.
> When there are many dependencies between clients and the implementation classes of an abstraction. You can create Facade Layer where you have all dependency and client have single dependency on this layer. 
> When you want to layer your subsystems. They called it "Facade Layer".

### Which problem it solves?
> The Facade pattern solves the problem of reducing complexity and dependencies in client code by providing a simplified interface to a complex subsystem. This makes the subsystem easier to use and understand while hiding its complexities from the client.

### Standard Template:

// Subsystem classes
    
    class SubsystemA {
        public void operationA() {
            System.out.println("SubsystemA: operationA");
        }
    }
    
    class SubsystemB {
        public void operationB() {
            System.out.println("SubsystemB: operationB");
        }
    }
    
    class SubsystemC {
        public void operationC() {
            System.out.println("SubsystemC: operationC");
        }
    }

// Facade class
    
    public class Facade {
        private SubsystemA subsystemA;
        private SubsystemB subsystemB;
        private SubsystemC subsystemC;
    
        public Facade() {
            this.subsystemA = new SubsystemA();
            this.subsystemB = new SubsystemB();
            this.subsystemC = new SubsystemC();
        }
    
        public void simpleOperation() {
            subsystemA.operationA();
            subsystemB.operationB();
            subsystemC.operationC();
        }
    }

---

### Real life Example with Code: HomeTheaterSystem
Consider a home theater system that has various components like a DVD player, projector, sound system, and lights. 

SubSystem:


    class SoundSystem {
       
         public void on() {
            System.out.println("Sound System is ON");
        }
    
        public void setVolume(int level) {
            System.out.println("Setting volume to " + level);
        }
    
        public void off() {
            System.out.println("Sound System is OFF");
        }
    }

    class Projector {
        
        public void on() {
            System.out.println("Projector is ON");
        }
    
        public void off() {
            System.out.println("Projector is OFF");
        }
    
        public void wideScreenMode() {
            System.out.println("Projector in widescreen mode");
        }
    }

    class Lights {
        public void dim(int level) {
            System.out.println("Dimming lights to " + level + "%");
        }
    
        public void on() {
            System.out.println("Lights are ON");
        }
    }

    class DVDPlayer {

        public void on() {
            System.out.println("DVD Player is ON");
        }
    
        public void play(String movie) {
            System.out.println("Playing movie: " + movie);
        }
    
        public void off() {
            System.out.println("DVD Player is OFF");
        }
}


// WithoutFacade Layer Client needs to make all the calls. What if there are more than one clients ?

    public class FacadeDesignClient {
    
        public static void run() {
    
           // Client want to watch movie it needs to perform all these steps. 
           // What if we provide single method to do all these steps ? and also remove all these dependency and replace with one dependency ?
    
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

FACADE Layer:

    public class HomeTheaterFacade {
    
        private DVDPlayer dvdPlayer;
        private Projector projector;
        private SoundSystem soundSystem;
        private Lights lights;
    
        public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem, Lights lights) {
            this.dvdPlayer = dvdPlayer;
            this.projector = projector;
            this.soundSystem = soundSystem;
            this.lights = lights;
        }
    
        public void watchMovie(String movie) {
            System.out.println("Get ready to watch a movie...");
            lights.dim(10);
            projector.on();
            projector.wideScreenMode();
            soundSystem.on();
            soundSystem.setVolume(5);
            dvdPlayer.on();
            dvdPlayer.play(movie);
        }
    
        public void endMovie() {
            System.out.println("Shutting down the home theater...");
            lights.on();
            projector.off();
            soundSystem.off();
            dvdPlayer.off();
        }
    
    }

Client Call:

    public class FacadeDesignClient2 {
    
        public static void run() {
    
            DVDPlayer dvdPlayer = new DVDPlayer();
            Lights lights = new Lights();
            Projector projector = new Projector();
            SoundSystem soundSystem = new SoundSystem();
    
            HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(dvdPlayer, projector, soundSystem, lights);
            homeTheaterFacade.watchMovie("spider-man");
            homeTheaterFacade.endMovie();
    
        }
    
    }

// Also we can create those classes inside facade layer, but it become tight coupling... let's see

    public HomeTheaterFacade() {
        this.dvdPlayer = new DVDPlayer();
        this.projector = new Projector();
        this.soundSystem = new SoundSystem();
        this.lights = new Lights();
    }


>> The Facade pattern can be used to provide a simple interface to control the entire system

---

Benefits:

Simplified Interface: Provides a simple interface to a complex subsystem, making it easier for clients to use.
Decoupling: Reduces the dependencies between the client and the subsystem classes.
Improved Maintenance: Makes the system easier to maintain by localizing changes to the Facade and subsystem classes.
Encapsulation: Hides the complexities of the subsystem from the client.