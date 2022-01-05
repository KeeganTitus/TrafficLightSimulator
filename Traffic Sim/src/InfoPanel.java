import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class InfoPanel extends JPanel implements ActionListener
{
	Timer tm = new Timer(500, this);
	
	private JButton simulateButton;
	private JButton existingButton;
	private JButton stopButton;
	private JButton upperExtremeButton;
	private JButton upperButton;
	private JButton goodButton;
	private JButton lowerExtremeButton;
	private JButton lowerButton;
	private JPanel infoDisplay;
	private JPanel spacer;
	private JPanel space;
	private JLabel previousRun;
	private JLabel simLabel;
	private JLabel boundsLabel;
	
	Control control = new Control();
	SimPanel simPanel = new SimPanel();
	
	public InfoPanel() 
	{
		//FlowLayout layout = new FlowLayout();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		simLabel = new JLabel("Simulate Using: ");
		
		spacer = new JPanel();
		spacer.setBackground(Color.GRAY);
		
		space = new JPanel();
		space.setBackground(Color.GRAY);
		
		boundsLabel = new JLabel("Data Bounds: ");
		
		simulateButton = new JButton("New System");	
		//simulateButton.setBounds(40, 50, 85, 25);
		simulateButton.setActionCommand("simulate");	// Set the action command that will trigger event
		
		existingButton = new JButton("Existing System");
		existingButton.setActionCommand("existing");
		
		stopButton = new JButton("Stop");
		//stopButton.setBounds(150, 50, 65, 25);
		stopButton.setActionCommand("stop");
		
		upperExtremeButton = new JButton("Extreme Upper");
		upperExtremeButton.setActionCommand("upperX");
		
		upperButton = new JButton("Upper Bound");
		upperButton.setActionCommand("upper");
		
		goodButton = new JButton("Good Data");
		goodButton.setActionCommand("good");
		
		lowerButton = new JButton("Lower Bound");
		lowerButton.setActionCommand("lower");
		
		lowerExtremeButton = new JButton("Extreme Lower");
		lowerExtremeButton.setActionCommand("lowerX");
		
		infoDisplay = new JPanel();
		infoDisplay.setBackground(Color.GRAY);
		
		int previousRunTime = simPanel.getPreviousRunTime();
		previousRun = new JLabel("Previous Run Time: "+ previousRunTime);
		infoDisplay.add(previousRun);
		
		UIActionListener uiActionListener= new UIActionListener();
		//acceptButton.addActionListener(uiActionListener);
		simulateButton.addActionListener(uiActionListener);
		existingButton.addActionListener(uiActionListener);
		stopButton.addActionListener(uiActionListener);
		upperExtremeButton.addActionListener(uiActionListener);
		upperButton.addActionListener(uiActionListener);
		goodButton.addActionListener(uiActionListener);
		lowerButton.addActionListener(uiActionListener);
		lowerExtremeButton.addActionListener(uiActionListener);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.01;	// Determines how much height the panel will use
		c.gridx = 0;		// Determines the column the panel will occupy
		c.gridy = 0;		// Determines the row the panel will occupy
		add(space, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.03;	// Determines how much height the panel will use
		c.gridx = 1;		// Determines the column the panel will occupy
		c.gridy = 1;		// Determines the row the panel will occupy
		add(simLabel, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.05;	// Determines how much height the panel will use
		c.gridx = 0;		// Determines the column the panel will occupy
		c.gridy = 2;		// Determines the row the panel will occupy
		add(simulateButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.05;	// Determines how much height the panel will use
		c.gridx = 1;		// Determines the column the panel will occupy
		c.gridy = 2;		// Determines the row the panel will occupy
		add(existingButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.05;	// Determines how much height the panel will use
		c.gridx = 2;		// Determines the column the panel will occupy
		c.gridy = 2;		// Determines the row the panel will occupy
		add(stopButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.02;	// Determines how much height the panel will use
		c.gridx = 0;		// Determines the column the panel will occupy
		c.gridy = 3;		// Determines the row the panel will occupy
		add(spacer, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.05;	// Determines how much height the panel will use
		c.gridx = 1;		// Determines the column the panel will occupy
		c.gridy = 4;		// Determines the row the panel will occupy
		add(boundsLabel, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.01;	// Determines how much height the panel will use
		c.gridx = 0;		// Determines the column the panel will occupy
		c.gridy = 5;		// Determines the row the panel will occupy
		add(upperExtremeButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.01;	// Determines how much height the panel will use
		c.gridx = 2;		// Determines the column the panel will occupy
		c.gridy = 5;		// Determines the row the panel will occupy
		add(upperButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.05;	// Determines how much height the panel will use
		c.gridx = 1;		// Determines the column the panel will occupy
		c.gridy = 6;		// Determines the row the panel will occupy
		add(goodButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.05;	// Determines how much height the panel will use
		c.gridx = 0;		// Determines the column the panel will occupy
		c.gridy = 7;		// Determines the row the panel will occupy
		add(lowerButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.05;	// Determines how much height the panel will use
		c.gridx = 2;		// Determines the column the panel will occupy
		c.gridy = 7;		// Determines the row the panel will occupy
		add(lowerExtremeButton, c);
		
		c.weightx = 0.1;	// Determines how much width the panel will use
		c.weighty = 0.5;	// Determines how much height the panel will use
		c.gridx = 0;		// Determines the column the panel will occupy
		c.gridy = 8;		// Determines the row the panel will occupy
		add(infoDisplay, c);
	}
	
	class UIActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionEvent) 
		{
			String action = actionEvent.getActionCommand();
			if (action.equals("simulate")) 
			{
				control.setSimStatus(true,1);
			}
			else if(action.equals("existing"))
			{
				control.setSimStatus(true,2);
			}
			else if(action.equals("stop"))
			{
				control.setSimStatus(false,1);
			}
			else if(action.equals("upperX"))
			{
				simPanel.setUpperX();
			}
			else if(action.equals("upper"))
			{
				simPanel.setUpper();
			}
			else if(action.equals("good"))
			{
				simPanel.setGood();
			}
			else if(action.equals("lower"))
			{
				simPanel.setLower();
			}
			else if(action.equals("lowerX"))
			{
				simPanel.setLowerX();
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int previousRunTime = simPanel.getPreviousRunTime();
		previousRun.setText("Previous Run Time: "+ previousRunTime);
		tm.start(); // trigger action
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		repaint();
	}
	
}
