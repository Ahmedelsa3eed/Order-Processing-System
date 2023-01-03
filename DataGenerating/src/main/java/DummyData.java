import java.sql.*;
import java.util.Random;


public class DummyData {
    static Random random = new Random();
    static String[] categories = {"Science", "Art", "Religion", "History", "Geography"};
    static String[] userTypes = {"customer", "manager"};
    // Import required packages
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "ROOT";

    static Connection conn = null;
    static PreparedStatement pstmt = null;

    public static void insertIntoUsersPublishersAuthors() throws ClassNotFoundException, SQLException {

        Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Execute a query to insert 10 rows into the table
        System.out.println("Inserting 5000 rows into user table...");
        String sql = "INSERT INTO Users (user_name, first_name, last_name, address, phone_number, email, password, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= 5000; i++) {
            pstmt.setString(1, "user_name" + i);
            pstmt.setString(2, "first_name" + i);
            pstmt.setString(3, "last_name" + i);
            pstmt.setString(4, "address" + i);
            pstmt.setString(5, "phone_number" + i);
            pstmt.setString(6, "email" + i + "@gmail.com");
            pstmt.setString(7, "password" + i);
            int r = random.nextInt(2);
            pstmt.setString(8, userTypes[r]);
            pstmt.executeUpdate();
        }
        System.out.println("Inserted users records into the table...");


        System.out.println("inserting 1000 rows into publisher table");
        sql = "INSERT INTO Publishers (name, address, phone_number) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= 1000; i++) {
            pstmt.setString(1, "Publisher " + i);
            pstmt.setString(2, "Address " + i);
            pstmt.setString(3, "Phone " + i);
            pstmt.executeUpdate();
        }
        System.out.println("Inserted publishers records into the table...");

        // Insert 10 rows into the Authors table
        System.out.println("inserting 500000 rows into authors table");
        sql = "INSERT INTO Authors (first_name, last_name, address, phone_number) VALUES (?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= 500000; i++) {
            System.out.println(i);
            pstmt.setString(1, "First " + i);
            pstmt.setString(2, "Last " + i);
            pstmt.setString(3, "Address " + i);
            pstmt.setString(4, "Phone " + i);
            pstmt.executeUpdate();
        }

        System.out.println("Inserted authors records into the table...");

        pstmt.close();
        conn.close();

    }

    public static void insertIntoBooks() throws ClassNotFoundException, SQLException {

        Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Execute a query to insert 10 rows into the table
        System.out.println("Inserting 10 rows into the table...");
        String sql = "INSERT INTO Books (ISBN,title, price, publisher_id, publication_year, quantity, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= 1000000; i++) {
            pstmt.setLong(1, 123456789L + i);
            pstmt.setString(2, "Book " + i);
            pstmt.setDouble(3, (random.nextFloat(100)+1) * 7);
            pstmt.setInt(4, random.nextInt(1000) + 1);
            pstmt.setInt(5, 1950+random.nextInt(73));
            pstmt.setInt(6, (random.nextInt(50)+1) * 10);
            pstmt.setString(7, categories[(int) (Math.random() * categories.length)]);
            pstmt.executeUpdate();
        }
        System.out.println("Inserted books records into the table...");

        pstmt.close();
        conn.close();

    }

    public static void insertIntoBooksAuthors() throws ClassNotFoundException, SQLException {

        //Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Execute a query to insert 10 rows into the table
        System.out.println("Inserting 10 rows into the table...");
        String sql = "INSERT INTO Book_Authors (ISBN, author_id) VALUES (?, ?)";
        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= 1000000; i++) {
            pstmt.setLong(1, 123456789L + i);
            int r = random.nextInt(3) + 1;
            for(int j = 0; j < r; j++)
            pstmt.setInt(2, random.nextInt(500000)+1);
            pstmt.executeUpdate();
        }
        System.out.println("Inserted book_authors records into the table...");

        pstmt.close();
        conn.close();

    }

    public static void insertIntoCart() throws ClassNotFoundException, SQLException {

        Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Execute a query to insert 10 rows into the table
        System.out.println("Inserting 10 rows into the table...");
        String sql = "INSERT INTO Cart (user_id, ISBN, quantity) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= 100; i++) {
            pstmt.setInt(1, i);
            pstmt.setLong(2, 123456789L + random.nextInt(100) +1);
            pstmt.setInt(3, random.nextInt(5)+1);
            pstmt.executeUpdate();
        }
        System.out.println("Inserted cart records into the table...");

        pstmt.close();
        conn.close();

    }

    public static void insertIntoOrders() throws ClassNotFoundException, SQLException {

        Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // Execute a query to insert 10 rows into the table
        System.out.println("Inserting 10 rows into the table...");
        String sql = "INSERT INTO Orders (ISBN, quantity) VALUES (?, ?)";
        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= 10; i++) {
            pstmt.setLong(1, 123456789L + random.nextInt(100)+1);
            pstmt.setInt(2, random.nextInt(100) + 200);
            pstmt.executeUpdate();
        }
        System.out.println("Inserted orders records into the table...");

        pstmt.close();
        conn.close();

    }

    public static void main(String[] args) {
        // JDBC driver name and database URL
        try {
            // Register JDBC driver
            insertIntoUsersPublishersAuthors();
            insertIntoBooks();
            insertIntoBooksAuthors();
            insertIntoCart();
            insertIntoOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}


