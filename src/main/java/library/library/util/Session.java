package library.library.util;

import library.library.LibraryApplication;
import library.library.controller.DatabaseController;
import library.library.models.Account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private Account currentUser = null;
    private SqlSentences sqlSentences = new SqlSentences();

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
                String insert = sqlSentences.getSentence("sessions.insert.account");
                PreparedStatement statement = DatabaseController.getConnection().prepareStatement(insert);
                statement.setString(1, email);
                statement.setString(2, hashPassword);
                statement.setString(3, "Usuario");
                DatabaseController.executeInsert(statement);
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

}
