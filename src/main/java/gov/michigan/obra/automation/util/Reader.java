package gov.michigan.obra.automation.util;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import static gov.michigan.obra.automation.common.constant.Constants.*;

public class Reader {
//    public static String getEnvironmentConfig(String propertyName) {
//        return getPropertyValue(("src/test/resources/env/" + EXECUTION_ENV_NAME + ".properties"), propertyName);
//    }
//
//    private static String getPropertyValue(String filename, String propertyName) {
//        String propertyValue = null;
//
//        Path filePath = Paths.get(System.getProperty("user.dir"), filename);
//
//        try(InputStream inputStream = Files.newInputStream(filePath)) {
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            propertyValue = properties.getProperty(propertyName);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        return propertyValue;
//    }
	
	public static String getEnvironmentConfig(String propertyName) {
        return getPropertyValue(Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "env", EXECUTION_ENV_NAME + ".properties"), propertyName);
    }

    private static String getPropertyValue(Path filePath, String propertyName) {
        Properties properties = new Properties();
        String propertyValue = null;

        try (InputStream inputStream = Files.newInputStream(filePath)) {
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException e) {
            System.err.println("Failed to read: " + filePath);
            e.printStackTrace();
        }

        return propertyValue;
    }
}
