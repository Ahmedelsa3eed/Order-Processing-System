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
        System.out.println(query);
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

    public List<Book> getBooksFromTo(int from, int to)  {
        String query = "SELECT * FROM books limit " + (to - from) + " offset " + from;
        System.out.println(query);
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
                + ", books.isbn = "
                + book.getISBN().toString()
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
            instance.commitTransaction();
            if (check == 1){
                return HttpStatus.OK;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            instance.rollbackTransaction();
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public Book getBookByISBN(Long ISBN) {
        String query = "SELECT * from books as b WHERE b.isbn = '" + ISBN + "'";
        System.out.println(query);
        try {
            ResultSet resultSet = instance.executeQuery(query);
            if (resultSet.next()){
                return new Book(resultSet.getLong("ISBN"), resultSet.getString("title"),
                        resultSet.getLong("publisher_id"), resultSet.getInt("publication_year"),
                        resultSet.getInt("price"), resultSet.getString("category"),
                        resultSet.getInt("quantity"), resultSet.getInt("threshold"));
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> findBooksByAttribute(String criteria, String searchInput, int from, int to) {
        String query = "SELECT * FROM books as b where b." + criteria + "= '" + searchInput + "' limit " + (to - from) + " offset " + from;
        System.out.println(query);
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

    public List<Book> findBooksByPublisherName(String name, int from, int to) {
        String query = "SELECT * FROM books as b where b.publisher_id in ( select p.publisher_id from publishers as p where p.name = '" + name + "' ) limit " + (to - from) + " offset " + from;
        System.out.println(query);
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

    public List<Book> findBooksByAuthorName(String first_name, String last_name, int from, int to) {
        String query = "SELECT * FROM books as b where b.isbn in ( select ba.isbn from book_authors as ba join authors as a on ba.author_id = a.author_id where a.first_name = '" + first_name + "' or a.last_name = '" + last_name + "' ) limit " + (to - from) + " offset " + from;
        System.out.println(query);
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

    public HttpStatus addBookAuthor(Long isbn, Long author_id) {
        String query = "INSERT INTO book_authors VALUES (" + author_id.toString() + ", " + isbn.toString() + ")";
        System.out.println(query);
        try {
            instance.executeUpdate(query);
            instance.commitTransaction();
            return HttpStatus.OK;
        }
        catch (Exception e){
            e.printStackTrace();
            instance.rollbackTransaction();
        }
        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus deleteBookAuthor(Long isbn, Long author_id) {
        String query = "DELETE from book_authors as ba WHERE ba.isbn = " + isbn + " and ba.author_id = " + author_id;
        System.out.println(query);
        try {
            int check = instance.executeUpdate(query);
            instance.commitTransaction();
            if (check == 1) {
                return HttpStatus.OK;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            instance.rollbackTransaction();
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

}
