package parkinglot;

import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.ConcurrentLinkedQueue;

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
