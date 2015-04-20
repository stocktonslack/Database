package data.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.controller.DatabaseController;

public class DatabasePanel extends JPanel
{
	private DatabaseController baseController;
	private JTable table;
	private SpringLayout baseLayout;
	private JLabel titleLabel;
	private JScrollPane tablePane;
	private JTextArea displayArea;
	private Component displayPane;
	private JTable resultsTable;
	private JButton queryButton;
	
	
	
	public DatabasePanel(DatabaseController baseController)
	{
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		queryButton = new JButton("test query");
		
		displayPane = new JScrollPane();
		
		setupDisplayPane();
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	
	private void setupDisplayPane()
	{
		displayArea.setWrapStyleWord(true);
		displayArea.setLineWrap(true);
		displayArea.setEditable(false);
		displayArea.setBackground(Color.CYAN);
	}
	
	private void setupTable()
	{
		String[] colNames = { "Coffees Col 1", "Coffees Col 2"};
		DefaultTableModel coffeeModel = new DefaultTableModel(baseController.getMyYoyos(), colNames);
		
		yoyoTable = new JTable(yoyoModel);
		
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.PINK);
		this.setSize(1000, 1000);
		this.setLayout(baseLayout);
		this.add(displayPane);
		this.add(queryButton);

		displayArea = new JTextArea(10,30);
		displayArea.setLineWrap(true);
		add(displayArea);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 195, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, queryButton, -295, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, displayArea, 125, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, displayArea, -718, SpringLayout.SOUTH, this);
	}
	
	private void setupListeners()
	{
		queryButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				JFrame popFrame = new JFrame();
				popFrame.setSize(250, 250);
				popFrame.setContentPane(new DynamicDataPanel(baseController, "books"));
				popFrame.setVisible(true);
			}
		});
		
		samplePassword.addKeyListener(new KeyListener()
		{
			private boolean is Pasting 
		});
	}
	
}
