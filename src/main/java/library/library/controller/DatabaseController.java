package library.library.controller;

import library.library.LibraryApplication;
import java.sql.*;
import java.util.logging.Logger;

public class DatabaseController {
    private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());

    private static Connection connection = null;
    private static final String driver = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite::resource:" +
            LibraryApplication.class.getResource("database/Biblioteca_12.db");

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            LOGGER.info("Database connection is null, creating connection...");
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(URL);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Failed to connect to database");
            }
        }
        return connection;
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        return statement.executeQuery();
    }

    public static ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }



    public static void executeInsert(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }
}
