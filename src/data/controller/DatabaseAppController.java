package data.controller;

import javax.swing.*;

public class DatabaseAppController
{
	private DatabaseAppController baseController;
	private JButton queryButton;
	private SpringLayout baseLayout;
	
	public DatabaseAppController(DatabaseAppController baseController, String table)
	{
		this.baseController = baseController;
		queryButton = new JButton ("Submit query");
		baseLayout = new SpringLayout();
		setupPanel(table);
	}
	
	private void setupPanel(String selectedTable)
	{
		this.setLayout(baseLayout);
		this.add(queryButton);
	}
	
	private void setupLayout();
	{
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 161, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, queryButton, -10, SpringLayout.SOUTH, this);
	}
}
