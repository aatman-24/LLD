package car_rental_system_latest;

import lombok.Data;

@Data
public class Vehicle {

    private VehicleType vehicleType;
    private Integer vehicleNo;
    private Integer ratePerHour;

// Added required fields as of now, but we can use it.
//    private String make;
//    private String model;
//    private Integer seatingCapcity;
}
