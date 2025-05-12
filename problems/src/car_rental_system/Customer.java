package car_rental_system;

public class Customer {

    private String name;
    private String contactNum;
    private String drivingLicenseNum;

    public Customer(String name, String contactNum, String drivingLicenseNum) {
        this.name = name;
        this.contactNum = contactNum;
        this.drivingLicenseNum = drivingLicenseNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getDrivingLicenseNum() {
        return drivingLicenseNum;
    }

    public void setDrivingLicenseNum(String drivingLicenseNum) {
        this.drivingLicenseNum = drivingLicenseNum;
    }
}
