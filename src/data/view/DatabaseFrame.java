package data.view;

import javax.swing.JFrame;

import data.controller.DatabaseController;

public class DatabaseFrame extends JFrame
{
	private DatabasePanel basePanel;
	
	public DatabaseFrame(DatabaseController baseController)
	{
		basePanel = new DatabasePanel(baseController);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		
		this.setResizable(true);
		this.setVisible(true);
	}
	
}
