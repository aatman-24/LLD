package atm_system_right_solution;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    private String userName;
    private Account account;
    private Card card;

    public User(String userName) {
        this.userName = userName;
        this.account = new Account();
        this.card = new Card(this.account, 4444);
    }
}
