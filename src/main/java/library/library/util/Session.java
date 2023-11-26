package library.library.util;

import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private Account currentUser = null;

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
                    System.out.println("Signed in");
                    currentUser = new Account(email, rs.getString("tipo_usuario"));
                    System.out.println(currentUser.getAccountType());
                    return 1;
                } else {
                    System.out.println("Wrong password");
                    return 0;
                }
            } else {
                System.out.println("Email doesn't exist");
                return -1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return -2;
    }

    public int signUp(String email, String password) {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Cuenta WHERE correo_electronico = '" + email + "'");
            if (rs.next()) {
                System.out.println("Email already exists");
                return 0;
            } else {
                String hashPassword = PasswordHash.createHash(password);
                String insert = "INSERT INTO Cuenta(correo_electronico, contraseña, tipo_usuario) VALUES('" +
                                email + "', '" + hashPassword + "', 'Usuario')";
                DatabaseController.executeInsert(insert);
                System.out.println("Signed up");
                currentUser = new Account(email, "Usuario");
                return 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        currentUser = null;
        LibraryApplication.changeScene("interface");
    }

    public static void main(String[] args) {
        Session sessions = new Session();
        sessions.signUp("hashpassword", "test");
        sessions.signIn("hashpassword", "test");
        ResultSet rs = null;

        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Cuenta");
            while (rs.next()) {
                System.out.println(rs.getString("correo_electronico"));
                System.out.println(rs.getString("contraseña"));
                System.out.println(rs.getString("tipo_usuario"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
