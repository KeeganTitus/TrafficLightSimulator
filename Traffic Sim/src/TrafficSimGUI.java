import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrafficSimGUI 
{
	private static JFrame main_frame;
	private JPanel mainPanel;
	
	public TrafficSimGUI() 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public void createFrame() 
	{
		main_frame = new JFrame("Traffic Simulator");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//DISPOSE_ON_CLOSE);
		main_frame.setBounds(50, 10, 1200, 700);
		buildUserInterface();
	}
	
	public void buildUserInterface()
	{
		mainPanel = new JPanel();
		//mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setPreferredSize(new Dimension(1000, 700));
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints c = new GridBagConstraints();	
		
		SimPanel simPanel = new SimPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.7;	// Determines how much width the panel will use
		c.weighty = 0.5;	// Determines how much height the panel will use
		c.gridx = 0;
		c.gridy = 0;
		//simPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//simPanel.setLayout(new FlowLayout());
		//simPanel.setPreferredSize(new Dimension(600, 500));
		//simPanel.setOpaque(true);
		//simPanel.setBackground(Color.BLUE);
		mainPanel.add(simPanel, c);
		
		InfoPanel infoPanel = new InfoPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.03;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		//infoPanel.setBorder(new EmptyBorder(0, 0, 5, 5));
		//infoPanel.setLayout(new FlowLayout());
		infoPanel.setBackground(Color.GRAY);
		mainPanel.add(infoPanel, c);
		
		main_frame.setContentPane(mainPanel);
		main_frame.setVisible(true);
	}
}
