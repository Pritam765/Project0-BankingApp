package com.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
//import java.sql.Statement;

public class DBConnection {
	
	

	public static Connection getDBConnection() {
		
		Properties properties = new Properties();
		
		FileReader reader;
		try {
			reader = new FileReader("C:\\Users\\HP\\Downloads\\Project_0_Banking_App\\DB.properties");
			properties.load(reader);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		Connection conection = null;
		
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		
		
		driver = properties.getProperty("Driver");
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		
		try {
			Class.forName(driver);
//			System.out.println("Driver loaded sccesfully");

			conection = DriverManager.getConnection(url, username, password);
//			


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} 
		return conection;

	}

}
