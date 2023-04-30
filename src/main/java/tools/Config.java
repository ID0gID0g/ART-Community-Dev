package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String getConfig(String value){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            return properties.getProperty(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
