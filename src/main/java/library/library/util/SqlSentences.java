package library.library.util;
import library.library.LibraryApplication;

import java.io.InputStream;
import java.util.Properties;
public class SqlSentences {
    private static final String FILENAME = "database/SQL.properties";
    private Properties properties;

    public SqlSentences() {
        properties = new Properties();
        InputStream inputStream = LibraryApplication.class.getResourceAsStream(FILENAME);
        try {
            properties.load(inputStream);
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSentence (String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        SqlSentences sqlQueries = new SqlSentences();
        String selectAllUsersQuery = sqlQueries.getSentence("sessions.get.account.by.email");
        System.out.println("Select All Users Query: " + selectAllUsersQuery);
    }

}
