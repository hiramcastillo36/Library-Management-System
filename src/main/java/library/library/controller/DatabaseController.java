package library.library.controller;

import library.library.LibraryApplication;

import java.sql.*;
import java.util.logging.Logger;

/**
 * Controller class for managing database operations.
 * Provides methods for database connection, executing queries, and checking the existence of ISBN and title in the database.
 */
public class DatabaseController {
    private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());

    private static Connection connection = null;
    private static final String driver = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite::resource:" +
            LibraryApplication.class.getResource("database/Biblioteca_12.db");

    /**
     * Establishes a connection to the database.
     *
     * @return The established database connection.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            LOGGER.info("Database connection is null, creating connection...");
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(URL);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Failed to connect to the database");
            }
        }
        return connection;
    }

    /**
     * Retrieves data from the "Autores" table and prints the ISBN.
     *
     * @throws SQLException If a database access error occurs.
     */
    public static void getAutores() throws SQLException {
        String query = "SELECT * FROM Autores";
        PreparedStatement statement = getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("ISBN"));
        }
    }

    /**
     * Retrieves account data from the "Cuenta" table and prints the email, password, and account type.
     *
     * @throws SQLException If a database access error occurs.
     */
    public static void getAccountData() throws SQLException {
        String query = "SELECT correo_electronico, contraseña, tipo_usuario FROM Cuenta";
        PreparedStatement statement = getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("correo_electronico"));
            System.out.println(rs.getString("contraseña"));
            System.out.println(rs.getString("tipo_usuario"));
        }
    }

    /**
     * Checks if a given ISBN exists in the "Libro" table.
     *
     * @param isbn The ISBN to check for existence.
     * @return True if the ISBN exists, false otherwise.
     */
    public static boolean isISBNExists(String isbn) {
        try {
            String query = "SELECT COUNT(*) AS count FROM Libro WHERE ISBN = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, isbn);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Exception handling, adjust as needed
        }
        return false;
    }

    /**
     * Checks if a given title exists in the "Libro" table.
     *
     * @param title The title to check for existence.
     * @return True if the title exists, false otherwise.
     */
    public static boolean isTitleExists(String title) {
        try {
            String query = "SELECT COUNT(*) AS count FROM Libro WHERE Titulo = ?";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Exception handling, adjust as needed
        }
        return false;
    }

    /**
     * Executes a query and returns the result set.
     *
     * @param query The SQL query to execute.
     * @return The result set obtained by executing the query.
     * @throws SQLException If a database access error occurs.
     */
    public static ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        return statement.executeQuery();
    }

    /**
     * Executes a prepared statement query and returns the result set.
     *
     * @param statement The prepared statement to execute.
     * @return The result set obtained by executing the prepared statement.
     * @throws SQLException If a database access error occurs.
     */
    public static ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    /**
     * Executes a prepared statement insert query.
     *
     * @param statement The prepared statement to execute.
     * @throws SQLException If a database access error occurs.
     */
    public static void executeInsert(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }
}