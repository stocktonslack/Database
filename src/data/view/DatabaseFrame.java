package data.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import data.controller.DatabaseController;

public class DatabaseFrame extends JFrame
{
	private DatabasePanel basePanel;
	
	public DatabaseFrame(DatabaseController baseController)
	{
		basePanel = new DatabasePanel(baseController);
		setupFrame();
		setupListeners();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		
		this.setResizable(true);
		this.setVisible(true);
	}
	
	private void setupListeners()
	{
		addWindowListener(new WindowListener()
		{

			@Override
			public void windowActivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e)
			{
				baseController.saveTimingInformation();
				
			}

			@Override
			public void windowDeactivated(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
