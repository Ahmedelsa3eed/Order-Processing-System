package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.User;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginService {
    private final DatabaseInstance instance;


    public LoginService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public String logIn(String email, String password) {
        String query = "SELECT email, password FROM users as u WHERE u.email = '" + email + "'";
        System.out.println(query);
        try {
            ResultSet resultSet = instance.executeQuery(query);
            if (resultSet.next() == false) {
                return "Email doesn't exist";
            }
            String returnedMail = resultSet.getString("email");
            String returnedPass = resultSet.getString("password");
            if (returnedMail.equals(email) && returnedPass.equals(password)) {
                return "Login Successfully";
            }
            return "Wrong Password";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error executing the query";
        }
    }

    public User getUser(String sessionID) {
        try {
            //String email = ActiveUserService.getInstance().getEmailFromSessionId(UUID.fromString(sessionID));
            //return queries.getUser(email).orElseThrow(RuntimeException::new);
            return null;
        }
        catch (RuntimeException e){
            return null;
        }
    }

}
