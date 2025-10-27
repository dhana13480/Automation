package gov.michigan.obra.automation.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static gov.michigan.obra.automation.common.constant.Constants.*;

public class Reader {
    public static String getEnvironmentConfig(String propertyName) {
        return getPropertyValue(("env/" + EXECUTION_ENV_NAME + ".properties"), propertyName);
    }

    private static String getPropertyValue(String filename, String propertyName) {
        String propertyValue = null;

        try (InputStream inputStream = Reader.class.getClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return propertyValue;
    }
}
