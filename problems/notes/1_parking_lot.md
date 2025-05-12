#### Design Problem: Parking Lot

---

[Problems](https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/parking-lot.md)

#### Requirements

1. The parking lot should have multiple levels, each level with a certain number of parking spots.
2. The parking lot should support different types of vehicles, such as cars, motorcycles, and trucks.
3. Each parking spot should be able to accommodate a specific type of vehicle.
4. The system should assign a parking spot to a vehicle upon entry and release it when the vehicle exits.
5. The system should track the availability of parking spots and provide real-time information to customers.
6. The system should handle multiple entry and exit points and support concurrent access.


### Identify Flow:

User with Vehicle comes to parking. Interact with system, which behind a scene check on all the levels, and park the vehicle, same happend when unpark vehicle, customer provide vehicle(unique-factor) based on that, system identify the vehicle, and unpark it. 

Behind Scene: System lookup on all the levels, and if any parkingslot is empty and compitable with vehicle type, it park it there, same action happen when unpark. 

### Entity LookUp

Vehicle => Car, MotorCycle, Trucks
Customer (There is no need of this, so I removed it)

ParkingLot
ParkingLevel
ParkingSpot

Final: **Vehicle(Car, MotorCycle, Truck), ParkingLot, ParkingLevel, ParkingSpot**

### Relationship Lookup

ParkingLot(1) -> ParkingLevel(N)
ParkingLevel(1) -> ParkingSpot(N)
ParkingSpot(1) -> Vehicle(1)

### Applying SOLID Principle & Design Patterns

> - Vehicle, we consider it as Abstract class(can also take as interface as well), and that will be extend by the Sub-Type Vehicle. 
> 
> - We need single ParkingLot here. Make it Singleton Pattern. 

### Class Diagram

Class Diagram:

```tex
Vehicle
----
- plateNumber: String
- vehicleType: VehicleType
-- 
+ Vehicle(plateNumber: String, vehicleType: VehicleType)
+ getPlateNumber(): String
+ setPlateNumber(plateNumber: String): void
+ getVehicleType(): VehicleType
+ setVehicleType(vehicleType: VehicleType): void
+ toString(): String

VehicleType
----
-- 
+ MOTORCYCLE
+ CAR
+ TRUCK

Car
----
-- 
+ Car(plateNumber: String, vehicleType: VehicleType)
+ toString(): String

MotorCycle
----
-- 
+ MotorCycle(plateNumber: String, vehicleType: VehicleType)
+ toString(): String

Truck
----
-- 
+ Truck(plateNumber: String, vehicleType: VehicleType)
+ toString(): String

ParkingLot
----
- instance: ParkingLot
- levelList: List<ParkingLevel>
-- 
+ ParkingLot()
+ getInstance(): ParkingLot
+ addLevel(): void
+ park(vehicle: Vehicle): boolean
+ unpark(vehicle: Vehicle): boolean
+ printParkingLot(): void

ParkingLevel
----
- numOfSpots: int
- parkingSpotList: List<ParkingSpot>
-- 
+ ParkingLevel(numOfSpots: int)
+ park(vehicle: Vehicle): boolean
+ unpark(vehicle: Vehicle): boolean
+ getParkingSpotList(): List<ParkingSpot>
+ printLevel(): void

ParkingSpot
----
- spotNumber: int
- vehicle: Vehicle
- vehicleType: VehicleType
-- 
+ ParkingSpot(spotNumber: int, vehicleType: VehicleType)
+ parkVehicle(vehicle: Vehicle): boolean
+ unparkVehicle(vehicle: Vehicle): boolean
+ toString(): String
```

### Code

```java
public abstract class Vehicle {

    public String plateNumber;
    public VehicleType vehicleType;

    public Vehicle(String plateNumber, VehicleType vehicleType) {
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    abstract public String toString();
}
```

```java
public enum VehicleType {
    MOTORCYCLE,
    CAR,
    TRUCK
}
```

```java
public class Car extends Vehicle{
    public Car(String plateNumber, VehicleType vehicleType) {
        super(plateNumber, VehicleType.CAR);
    }

    @Override
    public String toString() {
        return "C";
    }
}
```

```java
public class MotorCycle extends Vehicle {
    public MotorCycle(String plateNumber, VehicleType vehicleType) {
        super(plateNumber, VehicleType.MOTORCYCLE);
    }

    @Override
    public String toString() {
        return "M";
    }
}
```

```java
public class Truck extends Vehicle {
    public Truck(String plateNumber, VehicleType vehicleType) {
        super(plateNumber, VehicleType.TRUCK);
    }

    @Override
    public String toString() {
        return "T";
    }
}
```

```java
// Singleton class
public class ParkingLot {

    public static final int INITIAL_LEVEL = 3;
    public static final int INITIAL_LEVEL_CAPACITY = 10;

    private static ParkingLot instance = null;
    List<ParkingLevel> levelList;

    private ParkingLot() {
        levelList = new ArrayList<>(INITIAL_LEVEL);
        for(int i = 0; i < INITIAL_LEVEL; i++) {
            levelList.add(new ParkingLevel(INITIAL_LEVEL_CAPACITY));
        }
    }

    // Single Locked check
//    synchronized public static ParkingLot getInstance() {
//        if (instance == null) {
//            instance = new ParkingLot();
//        }
//        return instance;
//    }

    // Double Locked check
    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    return instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    public void addLevel() {
        this.levelList.add(new ParkingLevel(INITIAL_LEVEL_CAPACITY));
    }

    // park
    public boolean park(Vehicle vehicle) {
        for(ParkingLevel parkingLevel : levelList) {
            if(parkingLevel.park(vehicle)) {
                System.out.println("vehicle parked successfully: " + vehicle.getPlateNumber());
                return true;
            }
        }
        System.out.println("Not able to park: " + vehicle.getPlateNumber());
        return false;
    }

    /**
     * Remove a vehicle from the parking lot.
     *
     * @param vehicle Vehicle to unpark.
     * @return true if the vehicle was found and unparked, false otherwise.
     */
    public boolean unpark(Vehicle vehicle) {
        for(ParkingLevel parkingLevel : levelList) {
            if(parkingLevel.unpark(vehicle)) {
                System.out.println("Un-parked vehicle: " + vehicle.getPlateNumber());
                return true;
            }
        }
        System.out.println("Vehicle not found: " + vehicle.getPlateNumber());
        return false;
    }

    /**
     * Print the current status of the parking lot.
     *
     * <p>This method iterates through all the levels in the parking lot and
     * prints the current status of each level.
     */
    public void printParkingLot() {
        for(ParkingLevel parkingLevel : levelList) {
            parkingLevel.printLevel();
        }
    }
}
```

```java
public class ParkingLevel {

    int numOfSpots;
    List<ParkingSpot> parkingSpotList;

    public ParkingLevel(int numOfSpots) {
        this.numOfSpots = numOfSpots;
        parkingSpotList = new ArrayList<>(numOfSpots);

        // Ratio: 50 - 40 - 10
        int numberOfCars = (int) (numOfSpots * 0.5);
        int numberOfMotorcycles = (int) (numOfSpots * 0.4);
        int numberOfTrucks = numOfSpots - numberOfCars - numberOfMotorcycles;

        // Set the ParkingSpot for this level.
        int[] vehicleCounts = {numberOfCars, numberOfMotorcycles, numberOfTrucks};
        VehicleType[] vehicleTypes = {VehicleType.CAR, VehicleType.MOTORCYCLE, VehicleType.TRUCK};
        for (int i = 0; i < vehicleCounts.length; i++) {
            for (int j = 0; j < vehicleCounts[i]; j++) {
                parkingSpotList.add(new ParkingSpot(j, vehicleTypes[i]));
            }
        }
    }

    boolean park(Vehicle vehicle) {
        for(ParkingSpot parkingSpot : parkingSpotList) {
            if(parkingSpot.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    boolean unpark(Vehicle vehicle) {
        for(ParkingSpot parkingSpot : parkingSpotList) {
            if(parkingSpot.unparkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public List<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

    public void printLevel() {
        System.out.print("\nLevel: ");
        for(ParkingSpot parkingSpot : parkingSpotList) {
            System.out.print("\t" + parkingSpot);
        }
    }
}
```

```java
public class ParkingSpot {

    public int spotNumber;
    public Vehicle vehicle;
    public VehicleType vehicleType;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    boolean parkVehicle(Vehicle vehicle) {
        if(!isAllocated() && this.vehicleType == vehicle.getVehicleType()) {
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    boolean unparkVehicle(Vehicle vehicle) {
        if(isAllocated() && this.vehicleType == vehicle.getVehicleType()) {
            this.vehicle = null;
            return true;
        }
        return false;
    }


    private boolean isAllocated() {
        return vehicle != null;
    }

    @Override
    public String toString() {
       if(isAllocated()) {
           return " | " + vehicle.toString();
       }
       return " | " + "E";
    }
}
```

SourceCode-ConcurrentVersion

```java
@Getter
@Setter
@AllArgsConstructor
public class Vehicle {
    private String vehicleNumber;
    private VehicleType vehicleType;
}


public enum VehicleType {
    BUS,
    CAR,
    BIKE
}

@Getter
@Setter
@NoArgsConstructor
public class ParkingSpot {

    private String spotNumber;
    private Vehicle vehicle;
    private boolean isOccupied;
    private VehicleType vehicleType;

    public ParkingSpot(String spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.vehicle = null;
        this.vehicleType = vehicleType;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public void bookSpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        isOccupied = true;
    }

    public void unbookSpot() {
        this.vehicle = null;
        isOccupied = false;
    }
}
```

```java
@Getter
@Setter
public class ParkingLevel {

    private final ConcurrentLinkedQueue<ParkingSpot> availableSpots;

    private int parkingLevelNumber;

    public ParkingLevel(int NO_OF_SPOT, int parkingLevelNumber) {
        this.parkingLevelNumber = parkingLevelNumber;
        availableSpots = new ConcurrentLinkedQueue<ParkingSpot>();
        initializeParkingLevel(NO_OF_SPOT);
    }

    private void initializeParkingLevel(int NO_OF_SPOT)  {
        int busSpots = (int) Math.floor(NO_OF_SPOT * 0.3);
        for(int i = 0; i < busSpots; i++) {
            availableSpots.add(new ParkingSpot("BUS-" + i, VehicleType.BUS));
        }

        int carSpots = (int) Math.floor(NO_OF_SPOT * 0.3);
        for(int i = 0; i < carSpots; i++) {
            availableSpots.add(new ParkingSpot("CAR-" + i, VehicleType.CAR));
        }

        int bikeSpots = NO_OF_SPOT - (busSpots + carSpots);
        for(int i = 0; i < bikeSpots; i++) {
            availableSpots.add(new ParkingSpot("BIKE-" + i, VehicleType.BIKE));
        }
    }


    public boolean isSpotAvailable(VehicleType vehicleType) {
        for(ParkingSpot spot: availableSpots) {
            if(spot.getVehicleType() == vehicleType && spot.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void park(Vehicle vehicle) {
        for(ParkingSpot spot: availableSpots) {
            if(spot.getVehicleType() == vehicle.getVehicleType() && spot.isAvailable()) {
                spot.bookSpot(vehicle);
                System.out.println("Parked: " + vehicle.getVehicleNumber());
                return;
            }
        }
    }

    public void unpark(Vehicle vehicle) {
        for(ParkingSpot spot: availableSpots) {
            if(spot.getVehicle() == vehicle) {
                spot.unbookSpot();
                System.out.println("Unparked: " + vehicle.getVehicleNumber());
                return;
            }
        }
    }
}



public class ParkingLot {

    private static ParkingLot parkingLot = null;
    private final int NO_OF_LEVELS;
    private final int NO_OF_SLOTS_PER_LEVEL;

    // {parking_level_number, ParkingLevel}
    private ConcurrentHashMap<Integer, ParkingLevel> levels;

    // {vehicle_number, ParkingLevel}
    private ConcurrentHashMap<String, ParkingLevel> parkedVehicles;

    private ParkingLot(int NO_OF_LEVELS, int NO_OF_SLOTS_PER_LEVEL) {
        this.NO_OF_LEVELS = NO_OF_LEVELS;
        this.NO_OF_SLOTS_PER_LEVEL = NO_OF_SLOTS_PER_LEVEL;
        parkedVehicles = new ConcurrentHashMap<>(20);
        initializeParkingLot();
    }

    public static ParkingLot getInstance(int NO_OF_LEVELS, int NO_OF_SLOTS_PER_LEVEL) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot(NO_OF_LEVELS, NO_OF_SLOTS_PER_LEVEL);
        }
        return parkingLot;
    }

    private void initializeParkingLot() {
        levels = new ConcurrentHashMap<Integer, ParkingLevel>(NO_OF_LEVELS);
        for (int i = 0; i < NO_OF_LEVELS; i++) {
            levels.put(i, new ParkingLevel(NO_OF_SLOTS_PER_LEVEL, i));
        }
    }

    // all public methods
    public boolean isSpotAvailable(Vehicle vehicle) {
        for(ParkingLevel parkingLevel: levels.values()) {
            if(parkingLevel.isSpotAvailable(vehicle.getVehicleType())) {
                return true;
            }
        }
        return false;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for(ParkingLevel parkingLevel: levels.values()) {
            if(parkingLevel.isSpotAvailable(vehicle.getVehicleType())) {
                parkingLevel.park(vehicle);
                parkedVehicles.put(vehicle.getVehicleNumber(), parkingLevel);
                return true;
            }
        }
        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        if(!parkedVehicles.containsKey(vehicle.getVehicleNumber())) {
            System.out.println("Vehicle not found with number: " + vehicle.getVehicleNumber());
            return false;
        }
        ParkingLevel parkingLevel = parkedVehicles.get(vehicle.getVehicleNumber());
        parkingLevel.unpark(vehicle);
        parkedVehicles.remove(vehicle.getVehicleNumber());
        return true;
    }
}
```

```java
public class Client {

    public static void main(String[] args) {

        ParkingLot parkingLot = ParkingLot.getInstance(3, 10);
        Vehicle car = new Vehicle("KA-01-HH-1234", VehicleType.CAR);
        Vehicle bus = new Vehicle("KA-01-HH-2334", VehicleType.BUS);
        if(parkingLot.isSpotAvailable(car)) {
            System.out.println("Yes.....spot available");
            parkingLot.parkVehicle(car);
        }
        parkingLot.unParkVehicle(car);

    }
}
```