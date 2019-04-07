package launch;

import model.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {


    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:WebApp2;create=true";


    Connection conn;

    public  DataBaseConnection(){

        try {

            this.conn = DriverManager.getConnection(JDBC_URL);
            if (this.conn != null) {

                System.out.print("Connected to DB!");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void createTable(){

        try{

            conn.createStatement().execute("CREATE TABLE USERS (Name VARCHAR(50), Last_Name VARCHAR(50), Email VARCHAR(50),Password VARCHAR(30))");

        } catch (SQLException ex) {

            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertIntoTable(String name, String lastName, String Email, String password)
    {

        try{

            conn.createStatement().execute("INSERT INTO USERS VALUES('" + name + "','" + lastName + "','" + Email + "','" + password + "')");

        }catch (SQLException ex){

            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printAll(){

        try{

            Statement statement = this.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");


            while (resultSet.next()) {

                System.out.println(resultSet.getString("Name") + resultSet.getString("Last_Name") +
                        resultSet.getString("Email") +resultSet.getString("Password"));
            }

        } catch ( SQLException  ex) {

            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUserByEmail(String email){

        try {


            PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                System.out.println(resultSet.getString("Name") + resultSet.getString("Last_Name"));
                return true;
            }


        } catch (SQLException e) {

            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    public void deleteUser(String email) {

        String sqlDeleteStatement = "DELETE FROM USERS WHERE EMAIL = ?";

        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sqlDeleteStatement)) {

            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            System.out.println("Deleting user with email " + email);


        } catch (SQLException ex) {

            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public User findUserByEmail(String email){

        try {
            User user = new User();
            PreparedStatement preparedStatement = this.conn.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                System.out.println(resultSet.getString("Name") + resultSet.getString("Last_Name"));
                user.setEmail(email);
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("Last_Name"));
                user.setPassword(resultSet.getString("Password"));

                return user;
            }


        } catch (SQLException e) {

            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    public boolean checkUserByUserName(String userName, String password){

        String sqlDeleteStatement = "SELECT * FROM USERS WHERE NAME = ? AND PASSWORD = ?";

        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sqlDeleteStatement)) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return true;
            }


        } catch (SQLException ex) {

            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);

        }

        return false;
    }
}
