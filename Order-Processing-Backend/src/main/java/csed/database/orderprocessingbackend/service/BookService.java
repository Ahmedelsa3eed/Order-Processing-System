package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.Book;
import csed.database.orderprocessingbackend.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BookService {

    private final DatabaseInstance instance;

    public BookService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public HttpStatus addBook(Book book) {
        String query = "INSERT INTO books VALUES (" + book.toString() + ")";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
            return HttpStatus.OK;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return HttpStatus.BAD_REQUEST;
    }

    public int deleteBook(Long bookISBN) {
        return 0;
    }

    public int editBook(Book book) {
        return 0;
    }

}
