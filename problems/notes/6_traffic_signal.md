## Design Problems:

---

### Requirement

```tex
The traffic signal system should control the flow of traffic at an intersection with multiple roads.
The system should support different types of signals, such as red, yellow, and green.
The duration of each signal should be configurable and adjustable based on traffic conditions.
The system should handle the transition between signals smoothly, ensuring safe and efficient traffic flow.
The system should be able to detect and handle emergency situations, such as an ambulance or fire truck approaching the intersection.
The system should be scalable and extensible to support additional features and functionality.
```

### <u>Identify Flow</u>

```tex
Intersection is formed with many roads. 
Each road have a TrafficLight. 
Based on current signal of TrafficLight, road can be blocked/unblocked. 
Road can have a diff TrafficLights(red, yellow, green). 
There can be a single signal at a time, which is repeating at fixed interval from (red -> green -> yellow -> red). 
```

### <u>Entity LookUp</u>

```tex
TrafficSignal (Red, Yellow, Green)
Road
TrafficLight
TrafficController
```

#### <u>Relationship Lookup</u>

```tex
TrafficController -> Road (1 -> N)
Road -> TrafficLight (1 -> N) (Like, red, yellow, green)
Road -> TrafficScheduler ("has-a") (Each road has a TrafficScheduler, kind of configuration) 
```

#### <u>Applying SOLID Principle | Design Patterns</u>

```tex
Observer(Road) -> Observable(TrafficLight)

    Because, To change signal light from, RED -> GREEN..we have to let "Road" class
    to know that, so we can make functional call to switch light. 

TrafficController is singleton, which do the require management. 
```

#### <u>Class Diagram(Before)</u>

![](../../assets/2024-11-11-19-42-32-traffic_signal.drawio.png) 

#### Source code:

[solution-code-link](https://github.com/ashishps1/awesome-low-level-design/blob/main/solutions/java/src/trafficsignalsystem)

#### My code:

```java
public enum TrafficSignal {
    RED,
    YELLOW,
    GREEN
}


public abstract class TrafficLight {
    private TrafficSignal trafficSignal;
    private boolean isOn = false;
    protected Road road;

    public TrafficLight(Road road, TrafficSignal trafficSignal) {
        this.road = road;
        this.trafficSignal = trafficSignal;
    }

    public TrafficSignal getTrafficSignal() {
        return trafficSignal;
    }

    public void setTrafficSignal(TrafficSignal trafficSignal) {
        this.trafficSignal = trafficSignal;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    abstract void changeToNextSignal();

    @Override
    public String toString() {
        return "[trafficSignal=" + trafficSignal + " ]" + "[Road = " + road.getName() + "]";
    }
}


public class GreenTrafficLight extends TrafficLight{

    public GreenTrafficLight(Road road) {
        super(road, TrafficSignal.GREEN);
    }

    @Override
    void changeToNextSignal() {
        this.road.turnSignalYellow();
    }
}


public class RedTrafficLight extends  TrafficLight{

    public RedTrafficLight(Road road) {
        super(road, TrafficSignal.RED);
    }

    @Override
    void changeToNextSignal() {
        this.road.turnSignalGreen();
    }
}


public class YellowTrafficLight extends  TrafficLight{
    public YellowTrafficLight(Road road) {
        super(road, TrafficSignal.YELLOW);
    }

    @Override
    void changeToNextSignal() {
        this.road.turnSignalRed();
    }
}
```

```java
public class Road {
    private TrafficLight curTrafficLight;
    private Map<TrafficSignal, TrafficLight> trafficLights;
    private TrafficScheduler trafficScheduler;
    private int id;
    private String name;

    public Road(int id, String name) {
        this.id = id;
        this.name = name;
        trafficScheduler = new TrafficScheduler();
        initializeTrafficLights();
        curTrafficLight = trafficLights.get(TrafficSignal.RED);
    }

    private void initializeTrafficLights() {
        trafficLights = new HashMap<>(3);
        trafficLights.put(TrafficSignal.RED, new RedTrafficLight(this));
        trafficLights.put(TrafficSignal.GREEN, new GreenTrafficLight(this));
        trafficLights.put(TrafficSignal.YELLOW, new YellowTrafficLight(this));
    }

    public void activateSignal() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Current Signal: " + curTrafficLight);
            curTrafficLight.changeToNextSignal();
        }, 0, trafficScheduler.getScheduleTime(), TimeUnit.SECONDS);
        curTrafficLight.changeToNextSignal();
    }

    public void turnSignalRed() {
        curTrafficLight.setOn(false);
        curTrafficLight = trafficLights.get(TrafficSignal.RED);
        curTrafficLight.setOn(true);
    }

    public void turnSignalGreen() {
        curTrafficLight.setOn(false);
        curTrafficLight = trafficLights.get(TrafficSignal.GREEN);
        curTrafficLight.setOn(true);
    }

    public void turnSignalYellow() {
        curTrafficLight.setOn(false);
        curTrafficLight = trafficLights.get(TrafficSignal.YELLOW);
        curTrafficLight.setOn(true);
    }

    public void hasEmergencyVehicle() {
        turnSignalGreen();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TrafficLight getCurTrafficLight() {
        return curTrafficLight;
    }

    public Map<TrafficSignal, TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    public void setScheduleTime(int scheduleTime) {
        trafficScheduler.setScheduleTime(scheduleTime);
        activateSignal();
    }
}
```

```java
public class TrafficController {

    private static TrafficController instance;
    List<Road> roads;

    private TrafficController() {
        roads = new ArrayList<>(4);
    }

    public static TrafficController getInstance() {
        if (instance == null) {
            instance = new TrafficController();
        }
        return instance;
    }

    public Road addRoad(int id, String name) {
        Road road = new Road(id, name);
        roads.add(road);
        return road;
    }

    public Road getRoad(int id) {
        for (Road road : roads) {
            if (road.getId() == id) {
                return road;
            }
        }
        return null;
    }

    void changeDurationTime(Road road, int newDurationTime) {
        road.setScheduleTime(newDurationTime);
    }

    void hasEmergencyVehicle(Road road) {
        road.hasEmergencyVehicle();
    }

    void activateTrafficLight(Road road) {
        road.activateSignal();
    }
}
```

```java
public class Client {

    public static void main(String[] args) {

        TrafficController trafficController = TrafficController.getInstance();
        Road schoolRoad = trafficController.addRoad(1, "schoolRoad");
        trafficController.activateTrafficLight(schoolRoad);

        // After 5s, an emergency vehicle got on road
        try {
            Thread.sleep(5000); // 2000 milliseconds = 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        trafficController.hasEmergencyVehicle(schoolRoad);

        // After 5s
        try {
            Thread.sleep(5000); // 2000 milliseconds = 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // changing the schedule time
        trafficController.changeDurationTime(schoolRoad, 1);
    }
}
```
