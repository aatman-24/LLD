package parkinglot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Vehicle {
    private String vehicleNumber;
    private VehicleType vehicleType;
}
