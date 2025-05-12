package car_rental_system;

public interface Payment {
    Transaction pay(Integer amount);
    Integer refund(Transaction transaction);
}
