package com.goit.java5.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
	public void initDb() {

		try (InputStream inputStream = DatabaseInitService.class.getClassLoader().getResourceAsStream("hibernate.properties")) {
			Properties property = new Properties();
			property.load(inputStream);

			Flyway flyway = Flyway
					.configure()
					.dataSource(property.getProperty("hibernate.connection.url"), property.getProperty("hibernate.connection.username"), property.getProperty("hibernate.connection.password"))
					.load();

			flyway.migrate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}