package services;

import interfaces.UserService;
import launch.DataBaseConnection;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;



@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    DataBaseConnection connection = new DataBaseConnection();


    @Override
    public User findUserByEmail(String email){

        User user = connection.findUserByEmail(email);

        return user;
    }

    @Override
    public void saveUser(User user) {

        if (user != null) {

            if (connection != null) {

                boolean userExist = connection.checkUserByEmail(user.getEmail());

                if (!userExist) {

                    connection.insertIntoTable(user.getName(), user.getLastName(), user.getEmail(), user.getEmail());

                } else {

                    System.out.println("User already exists !");
                }

            }
        }

    }

    public void deleteUser(User user) {

        if (user == null ){

            System.out.println("no user to delete");
        }

        if (connection.checkUserByEmail(user.getEmail()))
        {
            connection.deleteUser(user.getEmail());

        }  else {

            System.out.println("User inexistent");
        }

    }

}
