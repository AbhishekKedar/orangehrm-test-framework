package com.orangehrm.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static final Properties properties = new Properties();

	static {

		try {

			FileInputStream file = new FileInputStream("src/test/resources/config.properties");

			properties.load(file);

			file.close();

		} catch (IOException e) {

			throw new RuntimeException("Failed to load config.properties file", e);
		}
	}

	public static String getProperty(String key) {

		return properties.getProperty(key);
	}

	public static int getIntProperty(String key) {

		return Integer.parseInt(properties.getProperty(key));
		
	}

}
