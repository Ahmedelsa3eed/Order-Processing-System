package csed.database.orderprocessingbackend.service;


import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.Author;
import csed.database.orderprocessingbackend.model.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    private final DatabaseInstance instance;

    public AuthorService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public HttpStatus addAuthor(Author author) {
        String query = "INSERT INTO Authors (first_name, last_name, address, phone_number ) VALUES " + author.toString();
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

    public HttpStatus editAuthor(Author author){
        String query = "UPDATE authors SET authors.first_name = "
                + "'" + author.getFirst_name() + "' "
                + ", authors.last_name = "
                + "'" + author.getLast_name() + "' "
                + ", authors.phone_number = "
                + "'" + author.getPhone_number() + "' "
                + ", authors.address = "
                + "'" + author.getAddress() + "' "
                + "WHERE authors.author_id = "
                + author.getAuthor_id().toString()
                ;
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
    public List<Author> getAllAuthors()  {
        String query = "SELECT * FROM authors";
        try {
            List<Author> list = new ArrayList<>();
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()){
                return list;
            }
            do {
                Author author = new Author();
                author.setAddress(resultSet.getString("address"));
                author.setFirst_name(resultSet.getString("first_name"));
                author.setLast_name(resultSet.getString("last_name"));
                author.setPhone_number(resultSet.getString("phone_number"));
                author.setAuthor_id(resultSet.getLong("author_id"));
                list.add(author);
            }
            while(resultSet.next());
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public HttpStatus deleteAuthor(long authorID) {
        String query = "DELETE from authors as A WHERE A.author_id = '" + authorID + "'";
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

    public List<Author> getAuthorsByISBN(Long isbn) {
        String query = "SELECT * FROM book_authors NATURAL JOIN authors WHERE book_authors.isbn = " + isbn;
        try {
            ResultSet resultSet = instance.executeQuery(query);
            List<Author> list = new ArrayList<>();
            if (!resultSet.next()){
                return null;
            }
            do {
                Author author = new Author();
                author.setAddress(resultSet.getString("address"));
                author.setFirst_name(resultSet.getString("first_name"));
                author.setLast_name(resultSet.getString("last_name"));
                author.setPhone_number(resultSet.getString("phone_number"));
                author.setAuthor_id(resultSet.getLong("author_id"));
                list.add(author);
            }while(resultSet.next());

            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
