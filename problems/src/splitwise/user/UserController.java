package splitwise.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserController {

    private List<User> userList;

    public UserController() {
        this.userList = new ArrayList<>(10);
    }

    public void addUser(User user) {
        this.userList.add(user);
    }

    public User getUserById(String id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
