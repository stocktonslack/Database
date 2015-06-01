package data.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import data.controller.DatabaseAppController;
import data.controller.DatabaseController;

/**
 * The Frame for the GUI to hold the panel
 * @author ssla9721
 *
 */
public class DatabaseFrame extends JFrame
{
	/**
	 * Declare and create the panel to be held by the frame.
	 */
	private DatabasePanel basePanel;
	private DatabaseAppController baseController;
	/**
	 * Constructor for the Frame of the Database Frame
	 * @param baseController
	 */
	public DatabaseFrame(DatabaseController baseController)
	{
		basePanel = new DatabasePanel(baseController);

		setupFrame();
		setupListeners();
	}
	
	/**
	 * Setup the visual display of the Frame.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		
		this.setResizable(true);
		this.setVisible(true);
	}
	
	/**
	 * Setup the listeners to respond to clicks and other such events.
	 */
	private void setupListeners()
	{
		addWindowListener(new WindowListener()
		{

			/**
			 * A a necessary blank method in order to have the windowListener so that it will save the
			 * timing information for the baseController when it closes.
			 */
			@Override
			public void windowActivated(WindowEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			/**
			 * A a necessary blank method in order to have the windowListener so that it will save the
			 * timing information for the baseController when it closes.
			 */
			@Override
			public void windowClosed(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * This saves the timing information when the window is closed.
			 */
			@Override
			public void windowClosing(WindowEvent e)
			{
				baseController.saveTimingInformation();
				
			}

			/**
			 * A a necessary blank method in order to have the windowListener so that it will save the
			 * timing information for the baseController when it closes.
			 */
			@Override
			public void windowDeactivated(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			/**
			 * A a necessary method in order to have the windowListener so that it will save the
			 * timing information for the baseController when it closes.
			 */
			@Override
			public void windowDeiconified(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			/**
			 * A a necessary method in order to have the windowListener so that it will save the
			 * timing information for the baseController when it closes.
			 */
			@Override
			public void windowIconified(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			/**
			 * A a necessary method in order to have the windowListener so that it will save the
			 * timing information for the baseController when it closes.
			 */
			@Override
			public void windowOpened(WindowEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
