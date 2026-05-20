import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class ConfigReader {

    private Properties properties;

    public ConfigReader() {

        properties = new Properties();

        try {

            FileInputStream file =
                new FileInputStream("config.properties");

            properties.load(file);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String getProperty(String key) {

        return properties.getProperty(key);
    }
}