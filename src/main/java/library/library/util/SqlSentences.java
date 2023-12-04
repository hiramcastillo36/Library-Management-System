package library.library.util;
import library.library.LibraryApplication;

import java.io.InputStream;
import java.util.Properties;
public class SqlSentences {
    private static final String FILENAME = "database/SQL.properties";
    private static Properties properties;

    public SqlSentences() {
        properties = new Properties();
        InputStream inputStream = LibraryApplication.class.getResourceAsStream(FILENAME);
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSentence (String key) {
        return properties.getProperty(key);
    }


}
