package splitwise.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import splitwise.balance_sheet.UserBalanceSheet;

import java.util.Locale;
import java.util.Objects;

@Data
public class User {

    private String id;
    private String name;
    private UserBalanceSheet userBalanceSheet;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.userBalanceSheet = new UserBalanceSheet(id.toLowerCase(Locale.ROOT) + "_" + "bs");
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // or another unique identifier
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.getId());
    }

}
