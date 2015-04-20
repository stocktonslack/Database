package data.view;

import javax.swing.*;

import data.controller.DatabaseAppController;

public class DynamicDataPanel extends JPanel
{
	private DatabaseAppController baseController;
	private JButton queryButton;
	private SpringLayout baseLayout;
	
	public DynamicDataPanel(DatabaseAppController baseController, String table)
	{
		this.baseController = baseController;
		queryButton = new JButton("Submit query");
		baseLayout = new SpringLayout();
		setupPanel(table);
	}
	
	private void setupPanel(String selectedTable)
	{
		this.setLayout(baseLayout);
		this.add(queryButton);
		int spacing = 50;
		
		String [] columns = baseController.getBaseController().getDatabaseColumnNames(selectedTable);
		
		for(int spot = 0; spot < columns.length; spot++)
		{
			JLabel columnLabel = new JLabel(columns[spot]);
			JTextField columnField = new JTextField(20);
			
			this.add(columnLabel)
		}
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 161, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, queryButton, -10, SpringLayout.SOUTH, this);
	}
}
