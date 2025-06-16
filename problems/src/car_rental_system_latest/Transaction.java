package car_rental_system_latest;

import car_rental_system_latest.reservations.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private TransactionStatus transactionStatus;
    private Reservation reservation;
}
