package data.controller;

/**
 * The necessary imports for the databaseController.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import data.model.QueryInfo;

/**
 * The setup for the database Controller so that the program can connect to and
 * communicate with the database.
 * 
 * @author ssla9721
 *
 */
public class DatabaseController
{
	private String connectString;
	private Connection databaseConnection;
	private DatabaseAppController baseController;
	private String query;
	private long queryTime;

	/**
	 * The constructor for the DatabaseController, initializing objects so that
	 * the proper methods can be called and the proper address is available to
	 * connect to the database.
	 * 
	 * @param databaseAppController
	 */
	public DatabaseController(DatabaseAppController databaseAppController)
	{
		this.baseController = databaseAppController;
		checkDriver();
		this.connectString = "jdbc:mysql://localhost";
		setupConnection();
	}

	/**
	 * The method to try and connect to the database driver, and will return an
	 * error if it doesn't work.
	 */
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

	/**
	 * To try and close the connection to the database. If it fails it will
	 * return an error.
	 */
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

	/**
	 * This method will attempt to setup the connection to the database, and if
	 * it fails will return an error.
	 */
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

	/**
	 * Checks the database for tables and checks to see what is in them.
	 * 
	 * @return Returns the results of what is inside of the tables.
	 */
	public String[][] testResult()
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
				results[answers.getRow() - 1][1] = answers.getString(1);
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

	/**
	 * The display of the tables, showing the tables and returning the results
	 * of the query.
	 * 
	 * @return returning the results of the query.
	 */
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

	/**
	 * Inserts the query of yoyo into the database.
	 * 
	 * @return returns the result of the yoyo statement and if it fails returns
	 *         an error.
	 */
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

	/**
	 * If there is an error it will display it as a JOptionPane.
	 * 
	 * @param currentException
	 */
	public void displayErrors(Exception currentException)
	{
		JOptionPane.showMessageDialog(baseController.getAppFrame(), "SQL State" + ((SQLException) currentException).getSQLState());
		JOptionPane.showMessageDialog(baseController.getAppFrame(), "SQL Error Code" + ((SQLException) currentException).getErrorCode());
	}

	/**
	 * Tests the results of the tables to see if the information
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

	/**
	 * Checks to make sure that the language sent to the database is all in
	 * uppercase, and will return true or false.
	 * 
	 * @return
	 */
	private boolean checkQueryForDataViolation()
	{
		if (query.toUpperCase().contains(" DROP ") || query.toUpperCase().contains(" TRUNCATE ") || query.toUpperCase().contains(" SET ") || query.toUpperCase().contains(" ALTER "))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Uses a 2D String array to put the query into the string and checks if
	 * there is an error.
	 * 
	 * @param query
	 * @return
	 */
	public String[][] selectQueryResults(String query)
	{
		String[][] results;
		this.query = query;
		try
		{
			if (checkQueryForDataViolation())
			{
				throw new SQLException("No Data Violation", "Don't mess with the data", Integer.MIN_VALUE);
			}

			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			int columnCount = answers.getMetaData().getColumnCount();

			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();

			results = new String[numberOfRows][columnCount];

			while (answers.next())
			{
				for (int col = 0; col < columnCount; col++)
				{
					results[answers.getRow() - 1][col] = answers.getString(col + 1);
				}
			}
		}
		catch (SQLException currentException)
		{
			results = new String[][] { { "Query unseuccessful" }, { "Way to go" }, { currentException.getMessage() } };
			displayErrors(currentException);
		}
		return results;
	}

	/**
	 * uses the system time to keep track of the query and how long it takes to
	 * execute.
	 * 
	 * @param query
	 */
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

	public String[] getDatabaseColumnNames(String selectedTable)
	{
		return ;

	}

	/**
	 * the getter for the connect String.
	 * 
	 * @return returns the connect String
	 */
	public String getConnectString()
	{
		return connectString;
	}

	/**
	 * The getter for the databaseConnection.
	 * 
	 * @return returns the databaseConnection.
	 */
	public Connection getDatabaseConnection()
	{
		return databaseConnection;
	}

	/**
	 * The getter for the baseController which is the databaseController class.
	 * 
	 * @return
	 */
	public DatabaseAppController getBaseController()
	{
		return baseController;
	}

	/**
	 * The getter for the query, that returns the string of the query.
	 * 
	 * @return the string query.
	 */
	public String getQuery()
	{
		return query;
	}

	/**
	 * The setter for the connect String to set the string to something else.
	 * 
	 * @param connectString
	 */
	public void setConnectString(String connectString)
	{
		this.connectString = connectString;
	}

	/**
	 * the setter for the databaseConnection to change the
	 * 
	 * @param databaseConnection
	 */
	public void setDatabaseConnection(Connection databaseConnection)
	{
		this.databaseConnection = databaseConnection;
	}

	/**
	 * The setter for the baseController
	 * 
	 * @param baseController
	 */
	public void setBaseController(DatabaseAppController baseController)
	{
		this.baseController = baseController;
	}

	/**
	 * The setQuery method to change the string of the query.
	 * 
	 * @param query
	 */
	public void setQuery(String query)
	{
		this.query = query;
	}

	public Object[][] getMyYoyos()
	{
		return null;
	}

}
