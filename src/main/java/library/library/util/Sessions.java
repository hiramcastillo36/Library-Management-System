package library.library.util;

import library.library.controller.DatabaseController;
import library.library.models.Account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sessions {
    private String email;
    private String password;

    private String currentUser = null;

    public void signIn(String email, String password) {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Cuenta WHERE correo_electronico = '" + email + "'");
            if (rs.next()) {
                String hashPassword = rs.getString("contraseña");
                if (PasswordHash.validatePassword(password, hashPassword)) {
                    this.email = email;
                    this.password = password;
                    this.currentUser = email;
                    System.out.println("Signed in");
                } else {
                    System.out.println("Wrong password");
                }
            } else {
                System.out.println("Email doesn't exist");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUp(String email, String password) {
        ResultSet rs = null;
        try {
            rs = DatabaseController.executeQuery("SELECT * FROM Cuenta WHERE correo_electronico = '" + email + "'");
            if (rs.next()) {
                System.out.println("Email already exists");
            } else {
                String hashPassword = PasswordHash.createHash(password);
                String insert = "INSERT INTO Cuenta(correo_electronico, contraseña, tipo_usuario) VALUES('" + email + "', '" + hashPassword + "', 'Usuario')";
                DatabaseController.executeInsert(insert);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public void signOut() {
        this.email = null;
        this.password = null;
    }

    public static void main(String[] args) {
        /*Sessions sessions = new Sessions();
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
        }*/
    }

}
