package data.controller;

import javax.swing.*;

import data.model.Database;
import data.view.DatabaseFrame;

public class DatabaseAppController
{
	private DatabaseFrame appFrame;
	private DatabaseController dataController;
	private ArrayList<QueryInfo> queryList;
	private JBUtton queryBUtton;
	private SpringLayout baseLayout;
	
	public DynamicDataPanel(DatabaseAppController baseController, String table)
	{
		this.baseController = baseController;
		queryButton = new JButton ("Submit query");
		baseLayout = new SpringLayout();
		setupPanel(table);
//		dataController = new DatabaseController (this);
//		queryList = new ArrayList<QueryInfo>();
//		appFrame = new DataFrame(this);
	}
	
	public DataFrame getAppFrame()
	{
		return appFrame;
	}
	
	public DatabaseController()
	{
		
	}
	
}
