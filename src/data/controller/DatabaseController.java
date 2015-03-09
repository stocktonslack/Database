package data.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController
{
	private String connectString;
	private Connection databaseConnection;
	private DatabaseController baseController;
	private String query;
	
	public DatabaseController(DatabaseController baseController)
	{
		this.baseController = baseController;
		checkDriver();
		this.connectionString = "jdbc:mysql://localhost";
		setupConnection();
	}
	
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception currentException)
		{
			displayErrors(currentException);
			System.exit(1);
		}
	}
	
	
	public void closeConnection()
	{
		try
		{
			databaseConnection.close();
		}
		catch(SQLException currentException)
		{
			displayErrors(currentException);
		}
	}
	
	private void setupConneciton()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectString);
		}
		catch(SQLException currentException)
		{
			displayErrors(currentException);
		}
	}
	
	public String[][] getMetaDataTitles()
	{
		String[][] results;
		String query = "SHOW TABLES";
		
		try
	}
	
}
