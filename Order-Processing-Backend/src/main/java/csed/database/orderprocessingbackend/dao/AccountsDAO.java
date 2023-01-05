package csed.database.orderprocessingbackend.dao;

import csed.database.orderprocessingbackend.model.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountsDAO {
    private final DatabaseInstance DBInstance;

    public AccountsDAO() throws SQLException {
        this.DBInstance = DatabaseInstance.getInstance();
    }

    public List<User> getAll() throws SQLException {
        String userAccountsQuery
                = "SELECT user_id, first_name, last_name, address, phone_number, email, type FROM Users";
        return getUsersAccounts(userAccountsQuery);
    }

    public List<User> getFromTo(int from, int to) throws SQLException {
        String query = "SELECT * FROM users limit " + (to-from) + " offset " + from;
        return getUsersAccounts(query);
    }

    public List<User> searchAccounts(String searchString) throws SQLException {
        String matchingAccountsQuery = "SELECT user_id, first_name, last_name, address, phone_number, email, type " +
                "FROM Users " +
                "WHERE first_name LIKE '%" + searchString + "%' OR last_name LIKE '%" + searchString + "%' " +
                "OR email LIKE '%" + searchString + "%' OR type LIKE '%" + searchString + "%'";
        return getUsersAccounts(matchingAccountsQuery);
    }

    private List<User> getUsersAccounts(String matchingAccountsQuery) throws SQLException {
        List<User> usersAccounts = new ArrayList<>();
        ResultSet accountsResult = this.DBInstance.executeQuery(matchingAccountsQuery);
        while (accountsResult.next()) {
            User user = new User();
            user.setUser_id(accountsResult.getLong("user_id"));
            user.setFirst_name(accountsResult.getString("first_name"));
            user.setLast_name(accountsResult.getString("last_name"));
            user.setAddress(accountsResult.getString("address"));
            user.setPhone_number(accountsResult.getString("phone_number"));
            user.setEmail(accountsResult.getString("email"));
            user.setType(accountsResult.getString("type"));
            usersAccounts.add(user);
        }
        return usersAccounts;
    }

    public void changeUserType(Long userId, String newType) throws SQLException {
        String updateUserTypeQuery = "UPDATE Users SET type = '" + newType + "' WHERE user_id = " + userId;
        this.DBInstance.executeUpdate(updateUserTypeQuery);
    }

    public void deleteAccountById(Long userId) throws SQLException {
        String deleteAccountQuery = "DELETE FROM Users WHERE user_id = " + userId;
        this.DBInstance.executeUpdate(deleteAccountQuery);
    }
}
