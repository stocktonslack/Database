package data.controller;

/**
 * The necessary imports for the appController class.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import data.model.QueryInfo;

/**
 * The databaseAppController class, to
 * 
 * @author ssla9721
 *
 */
public class DatabaseAppController
{
	private DatabaseController baseController;
	private JButton queryButton;
	private ArrayList<QueryInfo> queryList;
	private SpringLayout baseLayout;
	private JFrame appFrame;

	public DatabaseAppController()
	{
		this.baseController = new DatabaseController(this);
		queryButton = new JButton("Submit query");
		queryList = new ArrayList<QueryInfo>();
		// setupPanel(table);
	}

	// private void setupPanel(String selectedTable) {
	// this.setupLayout(baseLayout);
	// this.add(queryButton);
	// }

	private void loadTimingInformation()
	{
		try
		{
			File loadFile = new File("asaasda.save");
			if (loadFile.exists())
			{
				queryList.clear();
				Scanner textScanner = new Scanner(loadFile);
				while (textScanner.hasNext())
				{
					String query = textScanner.nextLine();
					long tempTime = Long.parseLong(textScanner.nextLine());

					queryList.add(new QueryInfo(query, textScanner.nextLong()));
				}
				textScanner.close();
				JOptionPane.showMessageDialog(getAppFrame(), queryList.size() + " QueryInfo objects were loaded into the application");
			}
			else
			{
				JOptionPane.showMessageDialog(getAppFrame(), "File not present. No QuerInfo objects loaded");
			}
		}
		catch (IOException currentError)
		{
			baseController.displayErrors(currentError);
		}
	}

	public void saveTimingInformation()
	{
		int saveNum = 0;
		String fileName = "save" + saveNum;
		String content = "";
		try
		{
			File saveFile = new File(fileName + ".txt");
			saveNum++;
			if (!saveFile.exists())
			{
				saveFile.createNewFile();
				JOptionPane.showMessageDialog((getAppFrame()), "File saved");

			}

			FileWriter fw = new FileWriter(saveFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		}
		catch (IOException currentError)
		{
			baseController.displayErrors(currentError);
		}
	}

	public DatabaseController getBaseController()
	{
		return baseController;
	}

	public ArrayList<QueryInfo> getQueryList()
	{
		return queryList;
	}

	public JFrame getAppFrame()
	{
		return appFrame;
	}

}
