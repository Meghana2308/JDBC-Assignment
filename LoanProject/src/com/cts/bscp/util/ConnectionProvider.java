package com.cts.bscp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

	private static ConnectionProvider instance;
	
	//private String driverName;
	private String dbUrl;
	private String userName;
	private String password;
	
	
	public static final String JDBC_PROPS_FILE_NAME = "./res/jdbc.properties";
	
	private ConnectionProvider() throws FileNotFoundException, IOException {
		Properties jdbcProps = new Properties();
		jdbcProps.load(new FileInputStream(JDBC_PROPS_FILE_NAME));
	
		//driverName = jdbcProps.getProperty("db.driver");
		dbUrl = jdbcProps.getProperty("db.url");
		userName = jdbcProps.getProperty("db.unm");
		password = jdbcProps.getProperty("db.pwd");
		
		//Class.forName(driverName);
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(dbUrl,userName,password);
	}
	
	public static ConnectionProvider getInstance() throws FileNotFoundException, ClassNotFoundException, IOException{
		if(instance==null)
			instance=new ConnectionProvider();
		return instance;
	}
}