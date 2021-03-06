package db_connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Paths.get("C:/Users/User/Desktop/SoftServe/Quiz/src/main/resources/db.properties").toAbsolutePath().toString()));

            DRIVER = properties.getProperty("driver");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            DRIVER = "org.postgresql.Driver";
            URL = "jdbc:postgresql://localhost:5432/testing";
            USERNAME = "postgres";
            PASSWORD = "admin";
            e.printStackTrace();
        }

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Not connected");
        }
    }

    public static void close(AutoCloseable... streams) {
        try {
            for (AutoCloseable stream : streams) {
                if (stream != null)
                    stream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


