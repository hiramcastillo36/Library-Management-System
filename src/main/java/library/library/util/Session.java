package library.library.util;

import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manages user sessions, including sign-in, sign-up, and sign-out operations.
 */
public class Session {

    private Account currentUser = null;

    /**
     * Attempts to sign in a user with the provided email and password.
     *
     * @param email    The email of the user.
     * @param password The password for the user.
     * @return -1 if the email doesn't exist, 0 if the password is incorrect, 1 if signed in successfully.
     */
    public int signIn(String email, String password) {
        // -1: Email doesn't exist
        // 0: Wrong password
        // 1: Signed in
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Cuenta WHERE correo_electronico = '" + email + "'");
            if (rs.next()) {
                String hashPassword = rs.getString("contraseña");
                if (PasswordHash.validatePassword(password, hashPassword)) {
                    currentUser = new Account(email, rs.getString("tipo_usuario"));
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return -2;
    }

    /**
     * Attempts to sign up a new user with the provided information.
     *
     * @param clave           The unique identifier for the user.
     * @param password        The password for the user.
     * @param nombre          The first name of the user.
     * @param apellidoPaterno The last name of the user.
     * @param apellidoMaterno The second last name of the user.
     * @return 0 if the account already exists, 1 if signed up successfully.
     */
    public int signUp(String clave, String password,
                      String nombre, String apellidoPaterno,
                      String apellidoMaterno) {
        ResultSet rs = null;
        String email = clave + "@alumnos.uaslp.mx";
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Cuenta WHERE correo_electronico = '" + email + "'");
            if (rs.next()) {
                return 0;
            } else {
                String hashPassword = PasswordHash.createHash(password);
                String insert = "INSERT INTO Cuenta(correo_electronico, contraseña, tipo_usuario) VALUES(?,?,?)";
                String insertStudent = "INSERT INTO Estudiantes(Clave_Usuario, Nombre_Usuario, Apellido_Paterno, Apellido_Materno, correo_electronico) VALUES(?,?,?,?,?)";
                PreparedStatement statementStudent = DatabaseController.getConnection().prepareStatement(insertStudent);
                PreparedStatement statement = DatabaseController.getConnection().prepareStatement(insert);
                statement.setString(1, email);
                statement.setString(2, hashPassword);
                statement.setString(3, "Estudiante");
                statementStudent.setString(1, clave);
                statementStudent.setString(2, nombre);
                statementStudent.setString(3, apellidoPaterno);
                statementStudent.setString(4, apellidoMaterno);
                statementStudent.setString(5, email);
                DatabaseController.executeInsert(statementStudent);
                DatabaseController.executeInsert(statement);
                currentUser = new Account(email, "Estudiante");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    /**
     * Gets the currently signed-in user.
     *
     * @return The currently signed-in user, or null if no user is signed in.
     */
    public Account getCurrentUser() {
        return currentUser;
    }

    /**
     * Signs out the current user and changes the scene to the main interface.
     */
    public void signOut() {
        currentUser = null;
        LibraryApplication.changeScene("interface");
    }
}