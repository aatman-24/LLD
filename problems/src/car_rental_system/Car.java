package car_rental_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Car {

    private String make;
    private String model;
    private Integer year;
    private String licensePlate;
    private Integer rentalPrice;
    List<Reservation> reservationList;

    public Car(String make, String model, Integer year, String licensePlate, Integer rentalPrice) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.rentalPrice = rentalPrice;
        this.reservationList = new ArrayList<>(10);
    }

    boolean isCarAvailable(LocalDateTime dateTime) {
        boolean foundReservation = reservationList.stream().anyMatch(reservation -> reservation.getStartDate().isBefore(dateTime) && reservation.getEndDate().isAfter(dateTime));
        return !foundReservation;
    }

    boolean isCarAvailableForDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        boolean foundReservation = reservationList.stream().anyMatch(reservation -> reservation.getStartDate().isBefore(endDate) && reservation.getEndDate().isAfter(startDate));
        return !foundReservation;
    }

    Reservation makeReservation(Customer customer, LocalDateTime startDate, LocalDateTime endDate, Integer rentalPrice) {
        Reservation reservation = new Reservation(this, customer, startDate, endDate, rentalPrice);
        reservationList.add(reservation);
        return reservation;
    }

    void removeReservation(Reservation reservation) {
        reservationList.remove(reservation);
    }

    // Getter-Setter
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Integer rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
