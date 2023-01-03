package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final DatabaseInstance instance;

    public BookService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public List<Book> getAllBooks()  {
        String query = "SELECT * FROM books";
        try {
            List<Book> list = new ArrayList<>();
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()){
                return list;
            }
            do {
                Book book = new Book(resultSet.getLong("ISBN"), resultSet.getString("title"),
                        resultSet.getLong("publisher_id"), resultSet.getInt("publication_year"),
                        resultSet.getInt("price"), resultSet.getString("category"),
                        resultSet.getInt("quantity"), resultSet.getInt("threshold"));
                list.add(book);
            }
            while(resultSet.next());
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
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

    public HttpStatus deleteBook(Long ISBN) {
        String query = "DELETE from books as b WHERE b.isbn = '" + ISBN + "'";
        System.out.println(query);
        try {
            int check = instance.executeUpdate(query);
            if (check == 1) {
                return HttpStatus.OK;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public HttpStatus editBook(Book book) {
        String query = "UPDATE books SET books.title = "
                + "'" + book.getTitle() + "'"
                + ", books.publisher_id = "
                + book.getPublisher_id().toString()
                + ", books.publication_year = "
                + book.getPublication_year().toString()
                + ", books.price = "
                + book.getPrice().toString()
                + ", books.category = "
                + "'" + book.getCategory() + "'"
                + ", books.quantity = "
                + book.getQuantity().toString()
                + ", books.threshold = "
                + book.getThreshold().toString()
                + " WHERE books.isbn = "
                + book.getISBN().toString();
        System.out.println(query);
        try {
            int check = instance.executeUpdate(query);
            if (check == 1){
                return HttpStatus.OK;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

}
