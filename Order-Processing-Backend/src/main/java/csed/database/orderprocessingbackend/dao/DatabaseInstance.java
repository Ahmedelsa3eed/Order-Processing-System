package csed.database.orderprocessingbackend.dao;

import java.sql.*;


public class DatabaseInstance {

    private static DatabaseInstance instance = null;
    private static Connection connection = null;


    private static String user_name = "root";

    private static String pass = "7122001";

    private static String url = "jdbc:mysql://localhost:3306/orderprocessing";



    private DatabaseInstance() throws SQLException {
        connection = DriverManager.getConnection(url, user_name, pass);
    }

    public static DatabaseInstance getInstance() throws SQLException {
        if (instance == null){
            instance = new DatabaseInstance();
        }
        return instance;
    }


    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }



}