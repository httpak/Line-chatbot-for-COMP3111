package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class SQLDatabaseEngine extends DatabaseEngine {
	@Override
	String search(String text) throws Exception {
		//Write your code here
		return null;
	}
	
	
	private Connection getConnection() throws URISyntaxException, SQLException {
		try{
			Connection connection;
			URI dbUri = new URI(System.getenv("DATABASE_URL"));
	
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	
			log.info("Username: {} Password: {}", username, password);
			log.info ("dbUrl: {}", dbUrl);
		
			connection = DriverManager.getConnection(dbUrl, username, password);
			PreparedStatement stmt = connection.prepareStatement("SELECT keyword, responce FROM lab3 where name like concat('%', ?, '%')");
			stmt.setString(1, inputName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}
			rs.close();
			stmt.close();
			connection.close();
			return connection;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
