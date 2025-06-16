package car_rental_system_latest;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String contactNum;

    // we can put additional fields here
//    private String drivingLicenseNum;
}
