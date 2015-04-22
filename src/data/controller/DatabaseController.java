package data.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import data.model.QueryInfo;

public class DatabaseController
{
	private String connectString;
	private Connection databaseConnection;
	private DatabaseController baseController;
	private String query;
	private long queryTime;

	public DatabaseController(DatabaseController baseController)
	{
		this.baseController = baseController;
		checkDriver();
		this.connectString = "jdbc:mysql://localhost";
		setupConnection();
	}

	public DatabaseController(DatabaseAppController databaseAppController)
	{

	}

	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception currentException)
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
		catch (SQLException currentException)
		{
			displayErrors(currentException);
		}
	}

	private void setupConnection()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectString);
		}
		catch (SQLException currentException)
		{
			displayErrors(currentException);
		}
	}

	/**
	 * The database is accessed and then the Query "SHOW TABLES" is run, this
	 * gives you the names of the tables. Then you get the number of rows in the
	 * tables and then create a new string array. The information is put into
	 * the new array. If there is an error or it does not work, then it will
	 * display the error.
	 * 
	 * @return Returns the results, either the error that you have, or the new
	 *         information in the database array and tables.
	 */
	public String[][] getMetaDataTitles()
	{
		String[][] results;
		String query = "SHOW TABLES";
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);

			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();

			results = new String[numberOfRows][1];

			while (answers.next())
			{
				results[answers.getRow() - 1][1] = answers.getString(1);
			}

			answers.close();
			firstStatement.close();
			endTime = System.currentTimeMillis();
		}
		catch (SQLException currentException)
		{
			endTime = System.currentTimeMillis();
			results = new String[][] { { "empty" } };
			displayErrors(currentException);
		}
		queryTime = endTime - startTime;
		return results;

	}

	public String[][] testResults()
	{
		String[][] results;
		String query = "SHOW TABLES";
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			
			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();
		
			results = new String [numberOfRows][1];
			
			while(answers.next())
			{
				results[answers.getRow() - 1][1] = answers.getString(1);
			}
			
			answers.close();
			firstStatement.close();
		}
		catch(SQLException currentException)
		{
			results = new String [][] {{"empty"}};
			displayErrors(currentException);
		}
		return results;
	}

	public String displayTables()
	{
		@SuppressWarnings("unused")
		String tableNames = "";
		String query = "SHOW TABLES";

		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);

			while (answers.next())
			{
				tableNames += answers.getString(1) + "\n";
			}
			answers.close();
			firstStatement.close();
		}

		catch (SQLException currentError)
		{
			displayErrors(currentError);
		}

		return connectString;
	}

	public int insertSample()
	{
		int rowsAffected = -1;
		String query = "INSERT INTO `yoyo";

		try
		{
			Statement insertStatement = databaseConnection.createStatement();
			rowsAffected = insertStatement.executeUpdate(query);
			insertStatement.close();
		}
		catch (SQLException currentError)
		{
			displayErrors(currentError);
		}

		return rowsAffected;
	}

	private void displayErrors(Exception currentException)
	{
		JOptionPane.showMessageDialog(baseController.getAppFrame(), "SQL State" + ((SQLException) currentException).getSQLState());
		JOptionPane.showMessageDialog(baseController.getAppFrame(), "SQL Error Code" + ((SQLException) currentException).getErrorCode());

	}

	/**
	 * 
	 * @return
	 */
	public Object[][] testResults()
	{
		String[][] results;
		String query = "SHOW TABLES";

		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);

			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();

			results = new String[numberOfRows][1];

			while (answers.next())
			{
				results[answers.getRow() - 1][0] = answers.getString(1);
			}

			answers.close();
			firstStatement.close();
		}
		catch (SQLException currentException)
		{
			results = new String[][] { { "empty" } };
			displayErrors(currentException);
		}

		return results;
	}

	public String [][] selectQueryResults(String query)
	{
		String [][] results;
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			
		}
	}

	public void submitUpdateQuery(String query)
	{
		this.query = query;
		long startTime = System.currentTimeMillis();
		long endTime = 0;
		try
		{
			Statement updateStatement = databaseConnection.createStatement();
			updateStatement.executeUpdate(query);
			endTime = System.currentTimeMillis();
		}
		catch (SQLException currentError)
		{
			endTime = System.currentTimeMillis();
			displayErrors(currentError);
		}
		baseController.getQueryList().add(new QueryInfo(query, endTime = startTime));
	}

	public String getConnectString()
	{
		return connectString;
	}

	public Connection getDatabaseConnection()
	{
		return databaseConnection;
	}

	public DatabaseController getBaseController()
	{
		return baseController;
	}

	public String getQuery()
	{
		return query;
	}

	public void setConnectString(String connectString)
	{
		this.connectString = connectString;
	}

	public void setDatabaseConnection(Connection databaseConnection)
	{
		this.databaseConnection = databaseConnection;
	}

	public void setBaseController(DatabaseController baseController)
	{
		this.baseController = baseController;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

}
