package data.controller;

import data.model.Database;
import data.view.DatabaseFrame;

public class DatabaseAppController
{
	private DatabaseFrame appFrame;
	private DatabaseController dataController;
	private ArrayList<QueryInfo> queryList;
	
	public DatabaseAppController()
	{
		dataController = new DatabaseController (this);
		queryList = new ArrayList<QueryInfo>();
		appFrame = new DataFrame(this);
	}
	
	public DataFrame getAppFrame()
	{
		return appFrame;
	}
	
	public DatabaseC
	
}
