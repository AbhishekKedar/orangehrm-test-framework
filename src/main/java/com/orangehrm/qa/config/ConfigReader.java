package com.orangehrm.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class ConfigReader {

	private static final Properties properties = new Properties();

	static {

		try (FileInputStream file = new FileInputStream("src/test/resources/config.properties")) {

			properties.load(file);

		} catch (IOException e) {

			throw new RuntimeException("Failed to load config.properties file", e);
		}
	}

	public static String getProperty(String key) {

		String systemProperty = System.getProperty(key);

		if (systemProperty != null && !systemProperty.isBlank()) {

			return systemProperty;
		}

		String environmentKey = convertToEnvironmentKey(key);

		String environmentValue = System.getenv(environmentKey);

		if (environmentValue != null && !environmentValue.isBlank()) {

			return environmentValue;
		}

		String configValue = properties.getProperty(key);

		if (configValue == null || configValue.isBlank()) {

			throw new IllegalArgumentException("Configuration value not found for key: " + key);
		}

		return configValue;
	}

	public static int getIntProperty(String key) {

		return Integer.parseInt(getProperty(key));
	}

	private static String convertToEnvironmentKey(String key) {

		return key.replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase(Locale.ROOT);
	}
}