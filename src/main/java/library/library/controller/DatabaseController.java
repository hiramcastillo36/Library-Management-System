package library.library.controller;

import library.library.LibraryApplication;

import java.sql.*;
import java.util.logging.Logger;

public class DatabaseController {
    private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());

    private static Connection connection = null;
    private static final String driver = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:" +
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

    public static void getAutores() throws SQLException {
        String query = "SELECT * FROM " + "Autores";
        PreparedStatement statement = getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("ISBN"));
        }
    }
}
