package managers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileManager {

    private Properties getData(String fileName) {
        Properties prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("resources/" + fileName + ".properties");

        if (inputStream == null) {
            throw new RuntimeException("Unable to find properties file: " + fileName + ".properties");
        }

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String getProperty(String fileName, String key) {
        return getData(fileName).getProperty(key);
    }
}
