package gov.michigan.obra.automation.util;

import static gov.michigan.obra.automation.common.constant.Constants.EXECUTION_ENV_NAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Write {
	public static void setEnvironmentConfig(String propertyName, String value) {
		String projectDir = System.getProperty("user.dir");
		setPropertyValue((projectDir + "/src/test/resources/env/" + EXECUTION_ENV_NAME + ".properties"), propertyName, value);
	}

	private static void setPropertyValue(String filename, String propertyName, String value) {
		Properties properties = new Properties();
		try {
			File file = new File(filename);
			if (file.exists()) {
				try (InputStream inputStream = new FileInputStream(file)) {
					properties.load(inputStream);
				}
			}
			properties.setProperty(propertyName, value);
			try (OutputStream outputStream = new FileOutputStream(file)) {
				properties.store(outputStream, "Updated property: " + propertyName);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Failed to update property: " + propertyName, ex);
		}
	}
}
