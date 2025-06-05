package atm_system_right_solution;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Account {

    private Long accountNumber;
    private Integer balance;
    private static Long counter = 0L;

    public Account() {
        accountNumber = ++counter;
        balance = 10000;
    }
}
