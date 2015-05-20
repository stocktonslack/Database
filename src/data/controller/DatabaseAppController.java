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

/**
 * The databaseAppController class, to
 * 
 * @author ssla9721
 *
 */
public class DatabaseAppController {
	private DatabaseController baseController;
	private JButton queryButton;
	private ArrayList<String> queryList;
	private SpringLayout baseLayout;
	private JFrame appFrame;

	public DatabaseAppController() {
		this.baseController = new DatabaseController(this);
		queryButton = new JButton("Submit query");
		baseLayout = new SpringLayout();
		setupPanel(table);
	}

	private void setupPanel(String selectedTable) {
		this.setupLayout(baseLayout);
		this.add(queryButton);
	}

	private void setupLayout() {
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 161,
				SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, queryButton, -10,
				SpringLayout.SOUTH, this);
	}

	private void loadTimingInformation() {
		try {
			File loadFile = new File("asaasda.save");
			if (loadFile.exists()) {
				queryList.clear();
				Scanner textScanner = new Scanner(loadFile);
				while (textScanner.hasNext()) {
					String query = textScanner.nextLine();
					long tempTime = Long.parseLong(readFileScanner.nextLine);

					queryList.add(newQueryInfo(query, textScanner.nextLong()));
				}
				textScanner.close();
				JOptionPane
						.showMessageDialog(
								getAppFrame(),
								queryList.size()
										+ " QueryInfo objects were loaded into the application");
			} else {
				JOptionPane.showMessageDialog(getAppFrame(),
						"File not present. No QuerInfo objects loaded");
			}
		} catch (IOException currentError) {
			baseController.displayErrors(currentError);
		}
	}

	public void saveTimingInformation() {
		int saveNum = 0;
		String fileName = "save" + saveNum;
		String content = "";
		try {
			File saveFile = new File(fileName + ".txt");
			saveNum++;
			if (!saveFile.exists()) {
				saveFile.createNewFile();
				JOptionPane.showMessageDialog((getAppFrame()), "File saved");

			}

			FileWriter fw = new FileWriter(saveFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (IOException currentError) {
			baseController.displayErrors(currentError);
		}
	}

	public DatabaseAppController getBaseController() {
		return baseController;
	}

	public JButton getQueryButton() {
		return queryButton;
	}

	public ArrayList<String> getQueryList() {
		return queryList;
	}

	public SpringLayout getBaseLayout() {
		return baseLayout;
	}

	public JFrame getAppFrame() {
		return appFrame;
	}

	public void setBaseController(DatabaseAppController baseController) {
		this.baseController = baseController;
	}

	public void setQueryButton(JButton queryButton) {
		this.queryButton = queryButton;
	}

	public void setQueryList(ArrayList<String> queryList) {
		this.queryList = queryList;
	}

	public void setBaseLayout(SpringLayout baseLayout) {
		this.baseLayout = baseLayout;
	}

	public void setAppFrame(JFrame appFrame) {
		this.appFrame = appFrame;
	}
}
