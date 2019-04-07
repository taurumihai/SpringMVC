package interfaces;



import model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    void deleteUser(User user);

}
