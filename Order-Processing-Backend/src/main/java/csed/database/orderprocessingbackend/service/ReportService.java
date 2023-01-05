package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import csed.database.orderprocessingbackend.model.Book;
import csed.database.orderprocessingbackend.model.Sale;
import csed.database.orderprocessingbackend.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.sql.ResultSet;
import java.util.*;
import java.util.Map.Entry;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private final DatabaseInstance instance;

    public ReportService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    private String path = "D:\\";

    public String sales_report() {
        String query = "SELECT * FROM checkout WHERE order_date> now() - interval 1 month";
        try {
            List<Sale> list = new ArrayList<>();
            List<Long> ids = new ArrayList<>();
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()) {
                return "No Sales Last Month";
            }
            do {
                int temp = ids.lastIndexOf(resultSet.getLong("ISBN"));
                if (temp >= 0) {
                    Sale x = list.get(temp);
                    Integer y = x.getQuantity();
                    x.setQuantity(y + resultSet.getInt("quantity"));
                    x.setPrice(x.getPrice() / y * x.getQuantity());
                } else {
                    ids.add(resultSet.getLong("ISBN"));
                    query = "SELECT * FROM book WHERE ISBN == " + resultSet.getLong("ISBN");
                    ResultSet res = instance.executeQuery(query);
                    res.next();
                    list.add(new Sale(
                            resultSet.getLong("ISBN"), res.getString("title"), res.getString("category")
                            , resultSet.getInt("quantity"), res.getInt("price") * resultSet.getInt("quantity")
                    ));
                }
            }
            while (resultSet.next());
            this.path += "Salesments.html";
            //load file and compile it
            File file = ResourceUtils.getFile("classpath:Salesments.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "DB team");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, this.path);
            Path filePath = Path.of(this.path);
            return Files.readString(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String top_customers_report() {
        String query = "SELECT user_id,quantity FROM checkout WHERE order_date> now() - interval 3 month";
        try {
            HashMap<Long, Integer> list = new HashMap<>();
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()) {
                return "No Sales Last 3 Months";
            }
            do {
                if (list.containsKey(resultSet.getLong("user_id"))) {
                    list.replace(resultSet.getLong("user_id"), list.get(resultSet.getLong("user_id"))
                            , list.get(resultSet.getLong("user_id")) + resultSet.getInt("quantity"));
                } else {
                    list.put(resultSet.getLong("user_id"), resultSet.getInt("quantity"));
                }
            } while (resultSet.next());
            List<Entry<Long, Integer>> greatest = findGreatest(list, 5);
            List<User> result = new ArrayList<>();
            for (Entry<Long, Integer> entry : greatest) {
                query = "SELECT * FROM Users WHERE user_id == " + entry.getKey();
                resultSet = instance.executeQuery(query);
                resultSet.next();
                User user = new User(resultSet.getLong("user_id"), resultSet.getString("user_name"),
                        resultSet.getString("email"), "", resultSet.getString("address"),
                        resultSet.getString("first_name"), resultSet.getString("last_name"),
                        resultSet.getString("phone_number"), resultSet.getString("type"));
                result.add(user);
            }
            this.path += "customer.html";
            //load file and compile it
            File file = ResourceUtils.getFile("classpath:customer.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "DB team");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, this.path);
            Path filePath = Path.of(this.path);
            return Files.readString(filePath);

        } catch (SQLException | JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String top_books_report() {
        String query = "SELECT ISBN,quantity FROM checkout WHERE order_date> now() - interval 3 month";
        try {
            HashMap<Long, Integer> list = new HashMap<>();
            ResultSet resultSet = instance.executeQuery(query);
            if (!resultSet.next()) {
                return "No Sales Last 3 Months";
            }
            do {
                if (list.containsKey(resultSet.getLong("ISBN"))) {
                    list.replace(resultSet.getLong("ISBN"), list.get(resultSet.getLong("ISBN"))
                            , list.get(resultSet.getLong("ISBN")) + resultSet.getInt("quantity"));
                } else {
                    list.put(resultSet.getLong("ISBN"), resultSet.getInt("quantity"));
                }
            } while (resultSet.next());
            List<Entry<Long, Integer>> greatest = findGreatest(list, 10);
            List<Book> result = new ArrayList<>();
            for (Entry<Long, Integer> entry : greatest) {
                query = "SELECT * FROM books WHERE ISBN == " + entry.getKey();
                resultSet = instance.executeQuery(query);
                resultSet.next();
                Book book = new Book(resultSet.getLong("ISBN"), resultSet.getString("title"),
                        resultSet.getLong("publisher_id"), resultSet.getInt("publication_year"),
                        resultSet.getInt("price"), resultSet.getString("category"),
                        resultSet.getInt("quantity"), resultSet.getInt("threshold"));
                result.add(book);
            }
            this.path += "Books.html";
            //load file and compile it
            File file = ResourceUtils.getFile("classpath:Books.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "DB team");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, this.path);
            Path filePath = Path.of(this.path);
            return Files.readString(filePath);

        } catch (SQLException | JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static <K, V extends Comparable<? super V>> List<Entry<K, V>>
    findGreatest(Map<K, V> map, int n)
    {
        Comparator<? super Entry<K, V>> comparator =
                (Comparator<Entry<K, V>>) (e0, e1) -> {
                    V v0 = e0.getValue();
                    V v1 = e1.getValue();
                    return v0.compareTo(v1);
                };
        PriorityQueue<Entry<K, V>> highest =
                new PriorityQueue<>(n, comparator);
        for (Entry<K, V> entry : map.entrySet())
        {
            highest.offer(entry);
            while (highest.size() > n)
            {
                highest.poll();
            }
        }

        List<Entry<K, V>> result = new ArrayList<>();
        while (highest.size() > 0)
        {
            result.add(highest.poll());
        }
        return result;
    }
}
