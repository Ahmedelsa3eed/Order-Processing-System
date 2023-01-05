package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.AccountsDAO;
import csed.database.orderprocessingbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService {
    private final AccountsDAO accountsDAO;

    @Autowired
    public AccountService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public List<User> getAllAccounts() {
        List<User> accounts;
        try {
            accounts = this.accountsDAO.getAll();
        }
        catch (SQLException e) {
            e.printStackTrace();
            accounts = Collections.emptyList();
        }
        return accounts;
    }

    public List<User> getFromTo(int from, int to){
        List<User> accounts;

        try{
            accounts = accountsDAO.getFromTo(from, to);
            return accounts;
        }
        catch (Exception e){
            e.printStackTrace();
            accounts = Collections.emptyList();
        }
        return accounts;
    }

    public List<User> searchAccounts(String searchString) {
        List<User> accounts;
        try {
            accounts = this.accountsDAO.searchAccounts(searchString);
        }
        catch (SQLException e) {
            e.printStackTrace();
            accounts = Collections.emptyList();
        }
        return accounts;
    }

    public Boolean changeUserType(Long userId, String newType) {
        try {
            this.accountsDAO.changeUserType(userId, newType);
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deleteAccountById(Long userId) {
        try {
            this.accountsDAO.deleteAccountById(userId);
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
