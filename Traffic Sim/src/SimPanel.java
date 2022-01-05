import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


@SuppressWarnings("serial")
public class SimPanel extends JPanel implements ActionListener
{
	Timer tm = new Timer(5, this);
	Control control = new Control();
	private int y = 0, velY = 5;
	private int x = 0, velX = 5;
	private int xOver = 0;
	private int yOver = 0;

	private BufferedImage background;
	private static BufferedImage robotTL;
	private static BufferedImage robotBR;
	private static BufferedImage robotTR;
	private static BufferedImage robotBL;
	private static String imageTL = "redTL.jpg";
	private static String imageBR = "redBR.jpg";
	private static String imageTR = "redTR.jpg";
	private static String imageBL = "redBL.jpg";
	private int w,h;
	private static boolean firstRunFlag;
	boolean statusFlag = false;
	private boolean status;
	private JTextField p1TextField;
	private JTextField p2TextField;
	private JTextField p3TextField;
	private JTextField p4TextField;
	private JLabel simTimeLabel;
	private static String p1cars;
	private static String p2cars;
	private static String p3cars;
	private static String p4cars;
	private static int intP1cars;
	private static int intP2cars;
	private static int intP3cars;
	private static int intP4cars;
	private static int p1MaxCars;
	private static int p2MaxCars;
	private static int p3MaxCars;
	private static int p4MaxCars;
	private static int carsOverP1 = 0;
	private static int carsOverP2 = 0;
	private static int carsOverP3 = 0;
	private static int carsOverP4 = 0;
	private static int seconds = 0;
	private static int secondsCount = 0;
	private static int previousRunTime = 0;
	private static int[] p1XValues;
	private static int[] p2XValues;
	private static int[] p3YValues;
	private static int[] p4YValues;
	//private static long startTime = 0;
	private static boolean xRunFlag;
	private static boolean yRunFlag;
	private static boolean nowRedFlag;
	private static boolean p3p4WasGreen;
	private static boolean upperXFlag;
	private static boolean upperFlag;
	private static boolean goodFlag;
	private static boolean lowerFlag;
	private static boolean lowerXFlag;
	private static boolean noCars;
	private static boolean simEnd;
	
	public SimPanel()
	{
		//reads the image
		try 
		{
			background = ImageIO.read(getClass().getResource("Intersection.jpg"));
			w = background.getWidth();
			h = background.getHeight();
					
			robotTL = ImageIO.read(getClass().getResource(imageTL));
			robotBR = ImageIO.read(getClass().getResource(imageBR));
			robotTR = ImageIO.read(getClass().getResource(imageTR));
			robotBL = ImageIO.read(getClass().getResource(imageBL));
			
			setLayout(null);
			
			// Create text fields that record number of cars
			p1TextField = new JTextField("Number of cars");
			p1TextField.setBounds(180, 310, 100, 20);
			add(p1TextField);
			
			p2TextField = new JTextField("Number of cars");
			p2TextField.setBounds(650, 490, 100, 20);
			add(p2TextField);
			
			p3TextField = new JTextField("Number of cars");
			p3TextField.setBounds(550, 210, 100, 20);
			add(p3TextField);
			
			p4TextField = new JTextField("Number of cars");
			p4TextField.setBounds(280, 585, 100, 20);
			add(p4TextField);
			
			simTimeLabel = new JLabel("Time Elapsed: " + seconds);
			simTimeLabel.setBounds(15, 15, 200, 50);
			add(simTimeLabel);
			
			//System.out.println("imageTL: "+imageTL);
			
		}
		catch (IOException ioe) 
		{
			System.out.println("Could not read in the pic");
			//System.exit(0);
		}
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension(w,h);
	}
	//this will draw the image
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//System.out.println("imageTL: "+imageTL);
		g.drawImage(background,0,0,this);
		g.drawImage(robotTL,310,205,this);
		g.drawImage(robotBR,550,490,this);
		g.drawImage(robotTR,545,260,this);
		g.drawImage(robotBL,260,490,this);
		
		g.setColor(Color.GREEN);
		//g.fillRect(480, y-61, 50, 60);
		
		p1XValues = new int[p1MaxCars+1];
		p2XValues = new int[p2MaxCars+1];
		p3YValues = new int[p3MaxCars+1];
		p4YValues = new int[p4MaxCars+1];
		
//Paint the cars at Point 1 in the intersection 
		// record the x values for cars at point 1 
		for(int i=p1MaxCars;i>=1;i--)
		{
			p1XValues[p1MaxCars-i] = 395-80*i;
		}
		//add the cars to the panel
		for(int i=0;i<=intP1cars-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(x+p1XValues[i], 350, 60, 50);
		}
		//remove the cars after they cross the intersection
		if(x%80 == 40 && intP1cars != 0 && x != 0)
		{
			intP1cars = intP1cars-1;
			p1cars = Integer.toString(intP1cars);
			carsOverP1 = carsOverP1 + 1;
			//System.out.println(intP1cars);
		}
		//add the cars that have crossed the intersection
		for(int i=0;i<=carsOverP1-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(xOver+315-80*i, 350, 60, 50);
		}
		
// Paint the cars at Point 2 in the intersection 
		// record the x values for cars at point 2 
		for(int i=p2MaxCars;i>=1;i--)
		{
			p2XValues[p2MaxCars-i] = 485+80*i;
			//System.out.println("DID IT!");
		}
		//add the cars to the panel
		for(int i=0;i<=intP2cars-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(x*-1+p2XValues[i], 425, 60, 50);
		}
		//remove the cars after they cross the intersection
		if(x%80 == 40 && intP2cars != 0 && x != 0)
		{
			intP2cars = intP2cars-1;
			p2cars = Integer.toString(intP2cars);
			carsOverP2 = carsOverP2 + 1;
			//System.out.println(intP1cars);
		}
		//add the cars that have crossed the intersection
		for(int i=0;i<=carsOverP2-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(xOver*-1+565+80*i, 425, 60, 50);
		}
		
// Paint the cars at Point 3 in the intersection 
		// record the x values for cars at point 3
		for(int i=p3MaxCars;i>=1;i--)
		{
			p3YValues[p3MaxCars-i] = 340-80*i;
		}
		//add the cars to the panel
		for(int i=0;i<=intP3cars-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(480, y+p3YValues[i], 50, 60);
		}
		//remove the cars after they cross the intersection
		if(y%80 == 40 && intP3cars != 0 && y != 0)
		{
			intP3cars = intP3cars-1;
			p3cars = Integer.toString(intP3cars);
			carsOverP3 = carsOverP3 + 1;
			//System.out.println(intP1cars);
		}
		//add the cars that have crossed the intersection
		for(int i=0;i<=carsOverP3-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(480, yOver+260-80*i, 50, 60);
		}
		
// Paint the cars at Point 4 in the intersection 
		// record the x values for cars at point 4
		for(int i=p4MaxCars;i>=1;i--)
		{
			p4YValues[p4MaxCars-i] = 425+80*i;
		}
		//add the cars to the panel
		for(int i=0;i<=intP4cars-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(405, y*-1+p4YValues[i], 50, 60);
		}
		//remove the cars after they cross the intersection
		if(y%80 == 40 && intP4cars != 0 && y != 0)
		{
			intP4cars = intP4cars-1;
			p4cars = Integer.toString(intP4cars);
			carsOverP4 = carsOverP4 + 1;
			//System.out.println(intP1cars);
		}
		//add the cars that have crossed the intersection
		for(int i=0;i<=carsOverP4-1;i++)
		{
			//g.setColor(Color.GREEN);
			g.fillRect(405, yOver*-1+505+80*i, 50, 60);
		}
		
		int system = control.getSystemUsed();
//Change the lights
		if(system == 1)
		{
			control.updateLights(firstRunFlag);
		}
		else
		{
			control.existingSystem(firstRunFlag);
		}
		/*if(y == 350)
		{	
			//changeImageTL("redTL.jpg"); 
			changeImageBR("redBR.jpg");
			changeImageTL("redTL.jpg");
			changeImageTR("amberTR.jpg");
			changeImageBL("amberBL.jpg");
		}
		if(y < 10)
		{	
			changeImageTL("greenTL.jpg");
		}*/
		
		tm.start();
		//System.out.println(tm);
	}

@Override
	public void actionPerformed(ActionEvent e) 
	{
		//System.out.println(h);
		//System.out.println(y);
		status = control.getSimStatus();
		if(status)	// True when simulator is running
		{
			if(imageTR.equals("redTR.jpg") && imageBR.equals("redBR.jpg"))
			{
				nowRedFlag = true;
			}
			
			if(firstRunFlag || nowRedFlag)
			{
				p1cars = p1TextField.getText();
				p2cars = p2TextField.getText();
				p3cars = p3TextField.getText();
				p4cars = p4TextField.getText();
				try 
				{
					p1MaxCars = Integer.parseInt(p1cars);
					p2MaxCars = Integer.parseInt(p2cars);
					p3MaxCars = Integer.parseInt(p3cars);
					p4MaxCars = Integer.parseInt(p4cars);
					if(p1MaxCars<=0 && p2MaxCars<=0 && p3MaxCars<=0 && p4MaxCars<=0 && firstRunFlag)
					{
						throw new NumberFormatException();
					}
				} 
				catch (NumberFormatException numberE) 
				{
					JOptionPane.showMessageDialog(null, "Please input a number greater than 0 in the textfield that reads \"Number of Cars\"");
				    control.setSimStatus(false,1);  
				}
				
				control.setP1Cars(p1MaxCars);
				control.setP2Cars(p2MaxCars);
				control.setP3Cars(p3MaxCars);
				control.setP4Cars(p4MaxCars);
			}
			if(!firstRunFlag)
			{
				p1TextField.setEditable(false);
				p1TextField.setSize(30, 20);
				p1TextField.setText(p1cars);
				
				p2TextField.setEditable(false);
				p2TextField.setText(p2cars);
			
				p3TextField.setEditable(false);
				p3TextField.setText(p3cars);
			
				p4TextField.setEditable(false);
				p4TextField.setText(p4cars);
			}
			
			// Get the string value for number of cars
			p1cars = p1TextField.getText();
			p2cars = p2TextField.getText();
			p3cars = p3TextField.getText();
			p4cars = p4TextField.getText();
			// Convert the number of cars to a intager value
			
			try 
			{
				intP1cars = Integer.parseInt(p1cars);
				intP2cars = Integer.parseInt(p2cars);
				intP3cars = Integer.parseInt(p3cars);
				intP4cars = Integer.parseInt(p4cars);
			}
			catch (NumberFormatException numberE) 
			{
				control.setSimStatus(false,1);
			}
			
			if(imageTR.equals("redTR.jpg") && imageBR.equals("redBR.jpg") &&  p3p4WasGreen || firstRunFlag)
			{
				//System.out.println("IT HAPPENED "+p3p4WasGreen+ " " +firstRunFlag);
				control.setGreenTimeLights();
				p3p4WasGreen = false;
			}
			
			//simulate time
			if(secondsCount%20 == 0)//(currentTime >= 1)//(secondsCount%155 == 0)
			{
				//startTime = System.nanoTime();
				seconds += 1;
				simTimeLabel.setText("Time Elapsed: "+seconds);
				control.setTime(seconds);
			}
			
			//simulate the cars movement
			if(!imageBR.equals("redBR.jpg"))
			{
				y = y + velY;
				yRunFlag = true;
			}
			else if(imageBR.equals("redBR.jpg"))
			{
				y = 0;
			}
			if(!yRunFlag)
			{
				yOver = 0;
			}
			yOver = yOver + velY;
			//remove cars after they leave the screen
			if(carsOverP3 > carsOverP4)
			{
				if(yOver+260-80*carsOverP3 > 590)
				{
					carsOverP3 = 0;
					carsOverP4 = 0;
					yRunFlag = false;
				}
			}
			else
			{
				if(yOver*-1+505+80*carsOverP4 < 0)
				{
					carsOverP3 = 0;
					carsOverP4 = 0;
					yRunFlag = false;
				}
			}
			
			//simulate the cars movement
			if(!imageTR.equals("redTR.jpg"))
			{
				x = x + velX;
				xRunFlag = true;
			}
			else if(imageTR.equals("redTR.jpg"))
			{
				x = 0;
			}
			if(!xRunFlag)
			{
				xOver = 0;
			}
			xOver = xOver + velX;
			//remove cars after they leave the screen
			if(carsOverP2 > carsOverP1)
			{
				if(xOver*-1+565+80*carsOverP2 < 0)
				{
					carsOverP1 = 0;
					carsOverP2 = 0;
					xRunFlag = false;
				}
			}
			else
			{
				if(xOver+315-80*carsOverP1 > 1000)//950)
				{
					carsOverP1 = 0;
					carsOverP2 = 0;
					xRunFlag = false;
				}
			}
//Stop Sim
			if(intP1cars == intP2cars && intP2cars == intP3cars && intP3cars == intP4cars && intP4cars==0)
			{
				noCars = true;
			}
			if(imageTR.equals("redTR.jpg") && imageBR.equals("redBR.jpg") && noCars && simEnd)// && !xRunFlag && !yRunFlag)
			{
				control.setSimStatus(false,1);
				noCars = false;
				JOptionPane.showMessageDialog(null, "Simulation completed after "+seconds+" seconds");
				previousRunTime = seconds;
			}
			
			secondsCount += 1;
			firstRunFlag = false;
			nowRedFlag = false;
		}
		else
		{
			firstRunFlag = true;
			noCars = false;
			simEnd = false;
			y=0;
			x=0;
			xOver=0;
			yOver=0;
			carsOverP1=0;
			carsOverP2=0;
			carsOverP3=0;
			carsOverP4=0;
			seconds=0;
			secondsCount=0;
			control.setTime(seconds);
			intP1cars=0;
			intP2cars=0;
			intP3cars=0;
			intP4cars=0;
			
			p1TextField.setEditable(true);
			p2TextField.setEditable(true);
			p3TextField.setEditable(true);
			p4TextField.setEditable(true);
			
			if(upperXFlag)
			{
				p1TextField.setText("a");
				p2TextField.setText("b");
				p3TextField.setText("c");
				p4TextField.setText("d");
				upperXFlag = false;
			}
			else if(upperFlag)
			{
				p1TextField.setText("500");
				p2TextField.setText("500");
				p3TextField.setText("500");
				p4TextField.setText("500");
				upperFlag = false;
			}
			else if(goodFlag)
			{
				p1TextField.setText("40");
				p2TextField.setText("40");
				p3TextField.setText("40");
				p4TextField.setText("40");
				goodFlag = false;
			}
			else if(lowerFlag)
			{
				p1TextField.setText("10");
				p2TextField.setText("5");
				p3TextField.setText("4");
				p4TextField.setText("4");
				lowerFlag = false;
			}
			else if(lowerXFlag)
			{
				p1TextField.setText("0");
				p2TextField.setText("0");
				p3TextField.setText("0");
				p4TextField.setText("0");
				lowerXFlag = false;
			}
		}
		repaint();
		
	}

	public void changeImageTL(String string)
	{
		try 
		{
			imageTL = string;
			robotTL = ImageIO.read(getClass().getResource(imageTL));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeImageTR(String string)
	{
		try 
		{
			imageTR = string;
			robotTR = ImageIO.read(getClass().getResource(imageTR));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeImageBL(String string)
	{
		try 
		{
			imageBL = string;
			robotBL = ImageIO.read(getClass().getResource(imageBL));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeImageBR(String string)
	{
		try 
		{
			imageBR = string;
			robotBR = ImageIO.read(getClass().getResource(imageBR));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setp3p4WasGreen(boolean flag)
	{
		p3p4WasGreen = flag;
	}
	
	public void setUpperX()
	{
		//JOptionPane.showMessageDialog(null, "UPPER_X");
		upperXFlag = true;
	}
	
	public void setUpper()
	{
		//JOptionPane.showMessageDialog(null, "UPPER");
		upperFlag = true;
	}
	
	public void setGood()
	{
		//JOptionPane.showMessageDialog(null, "GOOD");
		goodFlag = true;
	}
	
	public void setLower()
	{
		//JOptionPane.showMessageDialog(null, "LOWER");
		lowerFlag = true;
	}
	
	public void setLowerX()
	{
		//JOptionPane.showMessageDialog(null, "LOWER_X");
		lowerXFlag = true;
	}
	public int getPreviousRunTime()
	{
		return previousRunTime;
	}
	
	public void setSimEnd(boolean flag)
	{
		simEnd = flag;
	}
}
