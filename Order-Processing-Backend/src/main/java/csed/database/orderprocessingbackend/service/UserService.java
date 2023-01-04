package csed.database.orderprocessingbackend.service;



import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.User;
import csed.database.orderprocessingbackend.model.registration.RegistrationRequest;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


@Service
public class UserService {
    private final DatabaseInstance instance;

    public UserService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }
    public void addUser(RegistrationRequest user) {
        String query = "INSERT INTO Users (user_name, email, first_name, last_name, password, phone_number, address, type) VALUES ('" + user.getUserName() + "', '" + user.getEmail() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getPassword() + "', '" + user.getPhoneNumber() + "', '" + user.getAddress() + "', '" + "customer" + "')";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Optional<User> getUserById(Long id) {
        String query = "SELECT * FROM Users as u WHERE u.user_id = '" + id + "'";
        System.out.println(query);
        try {
            ResultSet resultSet = instance.executeQuery(query);
            if (resultSet.next() == false) {
                return Optional.empty();
            }
            Long user_id = resultSet.getLong("user_id");
            String user_name = resultSet.getString("user_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String address = resultSet.getString("address");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String phone_number = resultSet.getString("phone_number");
            String type = resultSet.getString("type");
            return Optional.of(new User(user_id, user_name, email, password, address, first_name, last_name, phone_number, type));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    // get user by username
    public Optional<User> getUserByUsername(String userName) {
        String query = "SELECT * FROM Users as u WHERE u.user_name = '" + userName + "'";
        System.out.println(query);
        try {
            ResultSet resultSet = instance.executeQuery(query);
            if (resultSet.next() == false) {
                return Optional.empty();
            }
            Long user_id = resultSet.getLong("user_id");
            String user_name = resultSet.getString("user_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String address = resultSet.getString("address");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String phone_number = resultSet.getString("phone_number");
            String type = resultSet.getString("type");
            return Optional.of(new User(user_id, user_name, email, password, address, first_name, last_name, phone_number, type));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Optional<User> getUserByEmail(String email) {
        String query = "SELECT * FROM Users as u WHERE u.email = '" + email + "'";
        System.out.println(query);
        try {
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()) {
                return Optional.empty();
            }
            Long returnedUserID = resultSet.getLong("user_id");
            String returnedUserName = resultSet.getString("user_name");
            String returnedEmail = resultSet.getString("email");
            String returnedFirstName = resultSet.getString("first_name");
            String returnedLastName = resultSet.getString("last_name");
            String returnedPassword = resultSet.getString("password");
            String returnedPhoneNumber = resultSet.getString("phone_number");
            String returnedAddress = resultSet.getString("address");
            String returnedType = resultSet.getString("type");
            return Optional.of(new User(returnedUserID, returnedUserName, returnedEmail, returnedFirstName, returnedLastName, returnedPassword, returnedPhoneNumber, returnedAddress, returnedType));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void updateUserFirstName(String firstName, Long UserId) {
        String query = "UPDATE Users SET first_name = '" + firstName + "' WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update user name
    public void updateUsername(String username, Long UserId) {
        String query = "UPDATE Users SET user_name = '" + username + "' WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateUserLastName(String lastName, Long UserId) {
        String query = "UPDATE Users SET last_name = '" + lastName + "' WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void updateUserPassword(String password, Long UserId) {
        String query = "UPDATE Users SET password = '" + password + "' WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateUserPhoneNumber(String phoneNumber, Long UserId) {
        String query = "UPDATE Users SET phone_number = '" + phoneNumber + "' WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void updateUserAddress(String address, Long UserId) {
        String query = "UPDATE Users SET address = '" + address + "' WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateUserType(String type, Long UserId) {
        String query = "UPDATE Users SET type = '" + type + "' WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long UserId) {
        String query = "DELETE FROM Users WHERE user_id = '" + UserId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateUserEmail(String email, Long userId) {
        String query = "UPDATE Users SET email = '" + email + "' WHERE user_id = '" + userId + "'";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}