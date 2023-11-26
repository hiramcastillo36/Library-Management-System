package library.library.controller;

import library.library.LibraryApplication;
import library.library.models.Account;

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

    public static void getAccountData() throws SQLException{
        String query = "SELECT correo_electronico, contraseña, tipo_usuario FROM Cuenta";
        PreparedStatement statement = getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
            System.out.println(rs.getString("correo_electronico")) ;
            System.out.println(rs.getString("contraseña"));
            System.out.println(rs.getString("tipo_usuario"));

        }
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        return statement.executeQuery();
    }

    public static void executeUpdate(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.executeUpdate();
    }

    public static void executeInsert(String query) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.execute();
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
