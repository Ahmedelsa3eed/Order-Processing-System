package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.User;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class LoginService {
    private final DatabaseInstance instance;


    public LoginService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public String logIn(String email, String password) {
        String query = "SELECT email, type, user_id, password FROM Users as u WHERE u.email = '" + email + "'";
        System.out.println(query);
        try {
            ResultSet resultSet = instance.executeQuery(query);
            if (resultSet.next() == false) {
                return "Email doesn't exist";
            }
            String returnedMail = resultSet.getString("email");
            String returnedPass = resultSet.getString("password");
            if (returnedMail.equals(email) && returnedPass.equals(password)) {
                ActiveUserService activeUserService = ActiveUserService.getInstance();
                activeUserService.login(returnedMail, resultSet.getString("type"), resultSet.getLong("user_id"));
                return "Login Successfully";
            }
            return "Wrong Password";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error executing the query";
        }
    }

    public User getUser(String sessionID) {
        String email = ActiveUserService.getInstance().getEmailFromSessionId(sessionID);
        if (email == null) {return null;}
        String query = "SELECT * from Users as u WHERE u.email = '" + email + "'";
        System.out.println(query);
        try {
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()) { return null;}
            return new User(resultSet.getLong("user_id"), resultSet.getString("user_name"),
                    resultSet.getString("email"), "", resultSet.getString("address"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getString("phone_number"), resultSet.getString("type"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void logout(String sessionId) {
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.logout(sessionId);
    }

}
