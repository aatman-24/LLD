package parkinglot;

import java.util.concurrent.ConcurrentHashMap;

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
