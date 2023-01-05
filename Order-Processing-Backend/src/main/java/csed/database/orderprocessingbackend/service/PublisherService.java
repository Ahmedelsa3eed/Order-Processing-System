package csed.database.orderprocessingbackend.service;


import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherService {
    private final DatabaseInstance instance;

    public PublisherService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public HttpStatus addPublisher(Publisher publisher) {
        String query = "INSERT INTO Publishers (`name`, `address`, `phone_number`) VALUES " + publisher.toString();
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

    public HttpStatus editPublisher(Publisher publisher){
        String query = "UPDATE Publishers SET Publishers.name = "
                + "'" + publisher.getName() + "' "
                + ", Publishers.phone_number = "
                + "'" + publisher.getPhone_number() + "' "
                + ", Publishers.address = "
                + "'" + publisher.getAddress() + "' "
                + "WHERE Publishers.publisher_id = "
                + publisher.getPublisher_id().toString()
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
    public List<Publisher> getAllPublishers()  {
        String query = "SELECT * FROM publishers";
        try {
            List<Publisher> list = new ArrayList<>();
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()){
                return list;
            }
            do {
                Publisher p = new Publisher();
                p.setAddress(resultSet.getString("address"));
                p.setName(resultSet.getString("name"));
                p.setPhone_number(resultSet.getString("phone_number"));
                p.setPublisher_id(resultSet.getLong("publisher_id"));
                list.add(p);
            }
            while(resultSet.next());
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public HttpStatus deletePublisher(String name) {
        String query = "DELETE from Publishers as P WHERE P.name = '" + name + "'";
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


    public List<Publisher> getFromTo(int from, int to) {
        String query = "SELECT * FROM publishers limit " + (to-from) + " offset " + from;
        try{
            ResultSet resultSet = instance.executeQuery(query);
            List<Publisher> list = new ArrayList<>();
            if (!resultSet.next()){
                return list;
            }
            do {
                Publisher p = new Publisher();
                p.setAddress(resultSet.getString("address"));
                p.setName(resultSet.getString("name"));
                p.setPhone_number(resultSet.getString("phone_number"));
                p.setPublisher_id(resultSet.getLong("publisher_id"));
                list.add(p);
            }
            while(resultSet.next());
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Publisher getPublisherByISBN(Long ISBN) {
        String query = "SELECT * FROM Publishers as p JOIN books as b on p.publisher_id = b.publisher_id "
                + "WHERE b.ISBN = " + ISBN.toString();
        System.out.println(query);
        try{
            ResultSet resultSet = instance.executeQuery(query);
            if(!resultSet.next()){
                return null;
            }
            Publisher p = new Publisher();
            p.setAddress(resultSet.getString("address"));
            p.setName(resultSet.getString("name"));
            p.setPhone_number(resultSet.getString("phone_number"));
            p.setPublisher_id(resultSet.getLong("publisher_id"));
            return p;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
