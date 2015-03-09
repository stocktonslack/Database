package data.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data.controller.DatabaseController;

public class DatabasePanel extends JPanel
{
	private DatabaseController baseController;
	private JTable table;
	private Springlayout baseLayout;
	private JLabel titleLabel;
	private JScrollPane tablePane;
	private JTextArea displayArea;
	private Component displayPane;
	private JTable resultsTable;
	private JButton queryButton;
	
	
	
	public DatabasePanel(DataBaseController baseController)
	
	private void setupDisplayPane()
	{
		displayArea.setWrapStyleWord(true);
		displayArea.setLineWrap(true);
		displayArea.setEditable(false);
		displayArea.setBackground(Color.GREEN);
	}
	
	private void setupTable()
	{
	
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.PINK);
		this.setSize(1000, 1000);
		this.setLayout(baseLayout);
		this.add(displayPane);
		this.add(queryButton);
	}
	
	private void setupListeners()
	{
		queryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String[][] temp = baseController.getDataController().getMetaDataTitles();
				for(String [] current : temp)
				{
					displayArea.setText(displayArea.getText() + "Rows Affected:" + current + "\n");
				}
			}
		});
	}
	
}
