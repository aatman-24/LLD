package car_rental_system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {

    private static RentalSystem instance = null;
    private List<Car> cars;
    private List<Customer> customers;
    private Payment payment;

    private RentalSystem() {
        cars = new ArrayList<>(10);
        customers = new ArrayList<>(10);
        payment = new CreditCardPayment();
    }

    public static RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    // Customer Related methods
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }


    // Cars Related methods
    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public List<Car> getAvailableCarsForDate(LocalDateTime dateTime) {
       return cars.stream().filter(car -> car.isCarAvailable(dateTime)).toList();
    }

    public List<Car> searchByCarModel(String carModel) {
        return cars.stream().filter(car -> car.getModel().equals(carModel)).toList();
    }

    public List<Car> searchByCarType(Integer minPrice, Integer maxPrice) {
        return cars.stream().filter(car -> car.getRentalPrice() >= minPrice && car.getRentalPrice() <= maxPrice).toList();
    }


    // Reservation related methods
    Reservation reserveCar(Car car, Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        if(!car.isCarAvailableForDateRange(startDate, endDate)) {
            System.out.println("Car is not available for this date range");
            return null;
        }
        int totalDays = Util.calculateDays(startDate, endDate);
        Integer rentalPrice = car.getRentalPrice() * totalDays;
        Transaction transaction = payment.pay(rentalPrice);
        if(transaction == null) {
            System.out.println("Payment failed");
            return null;
        }
        Reservation reservation = car.makeReservation(customer, startDate, endDate, rentalPrice);
        reservation.setTransaction(transaction);
        return reservation;
    }

    void cancelReservation(Reservation reservation) {
        Transaction transaction = reservation.getTransaction();
        Integer amount = payment.refund(transaction);
        System.out.println("Refunded $" + amount);
        reservation.getCar().removeReservation(reservation);
    }

    // Same way as above, but need to handle many cases.
    // First find the Reservation, and extend the date range, pay additional day money only.
    // we need to store mulitple transactions which again makes it more complex.
//    Transaction modifyReservation(Reservation reservation, LocalDateTime startDate, LocalDateTime endDate) {
//        if(!reservation.getCar().isCarAvailableForDateRange(startDate, endDate)) {
//            System.out.println("Car is not available for this date range");
//            return null;
//    }

}
