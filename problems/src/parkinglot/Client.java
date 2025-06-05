package parkinglot;

import java.util.HashMap;

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