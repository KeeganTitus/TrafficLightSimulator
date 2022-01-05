

public class Control 
{	
	private int maxCars = 25;
	private final int crossTime = 1;
	private final int amberTime = 2;
	
	private static boolean start;
	private static int select;
	private static int p1Cars;
	private static int p2Cars;
	private static int p3Cars;
	private static int p4Cars;
	private static int seconds;
	private static boolean currentTimeFlag = true;
	private static long currentTime = 0;
	private static int p1p2GreenTime;
	private static boolean p1p2Green;
	private static boolean p1p2Amber;
	private static boolean allRed;
	private static int p3p4GreenTime;
	private static boolean p3p4Green;
	private static boolean p3p4Amber;
	
	public Control() 
	{
		// Nothing done in constructor
	}

	public void setTime(int secs)
	{
		seconds = secs;
	}
	
	public void setGreenTimeLights()
	{	
		SimPanel simPanel = new SimPanel();
		
//Determine p1p2GreenTime		
		if(p1Cars == p2Cars && p1Cars < maxCars && p1Cars !=0)
		{
			p1p2GreenTime = p1Cars;//*crossTime+3;
			//simPanel.setp1p2GreenTime(p1p2GreenTime);
		}
		else if(p1Cars > p2Cars && p1Cars < maxCars)
		{
			p1p2GreenTime = p1Cars;//*crossTime+3;
			//simPanel.setp1p2GreenTime(p1p2GreenTime);
		}
		else if(p1Cars < p2Cars && p2Cars < maxCars)
		{
			p1p2GreenTime = p2Cars;//*crossTime+3;
			//simPanel.setp1p2GreenTime(p1p2GreenTime);
		}
		else if(p1Cars == 0 && p2Cars == 0)
		{
			p1p2GreenTime = 5;
			//simPanel.setp1p2GreenTime(p1p2GreenTime);
		}
		else
		{
			p1p2GreenTime = 25;
			//simPanel.setp1p2GreenTime(p1p2GreenTime);
		}
		
//Determine p3p4GreenTime
		if(p3Cars == p4Cars && p3Cars < maxCars && p3Cars !=0)
		{
			p3p4GreenTime = p3Cars;//*crossTime+3;
			//simPanel.setp3p4GreenTime(p3p4GreenTime);
		}
		else if(p3Cars > p4Cars && p3Cars < maxCars)
		{
			p3p4GreenTime = p3Cars;//*crossTime+3;
			//simPanel.setp3p4GreenTime(p3p4GreenTime);
		}
		else if(p3Cars < p4Cars && p4Cars < maxCars)
		{
			p3p4GreenTime = p4Cars;//*crossTime+3;
			//simPanel.setp3p4GreenTime(p3p4GreenTime);
		}
		else if(p3Cars == 0 && p4Cars == 0)
		{
			p3p4GreenTime = 5;
			//simPanel.setp3p4GreenTime(p3p4GreenTime);
		}
		else
		{
			p3p4GreenTime = 25;
			//simPanel.setp3p4GreenTime(p3p4GreenTime);
		}
		
		simPanel.setp3p4WasGreen(false);
		
		//System.out.println("p1p2GreenTime = " + p1p2GreenTime+" "+p1Cars);
		//System.out.println("p3p4GreenTime = " + p3p4GreenTime);
		/*System.out.println("p3Cars = " + p3Cars);
		System.out.println("p4Cars = " + p4Cars);*/
	}

	public void updateLights(boolean firstRunFlag)
	{
		SimPanel simPanel = new SimPanel();
		simPanel.setSimEnd(false);
		
		if(firstRunFlag)
		{
			currentTime = 0;
			currentTimeFlag = true;
		}
		else if(!currentTimeFlag)
		{
			currentTime = seconds;
			currentTimeFlag = true;
		}
		
		if(!start)
		{
			allRed = true;
		}
		else if(seconds<currentTime+p1p2GreenTime)
		{
			p1p2Green = true;
		}
		else if(!p1p2Green && seconds<currentTime+p1p2GreenTime+amberTime)
		{
			p1p2Amber = true;
		}
		else if(!p1p2Green && !p1p2Amber && seconds<currentTime+p1p2GreenTime+amberTime*2)
		{
			allRed = true;
		}
		else if(!p1p2Green && !p1p2Amber && !allRed && seconds<currentTime+p1p2GreenTime+p3p4GreenTime+amberTime*2)
		{
			p3p4Green = true;
		}
		else if(!p1p2Green && !p1p2Amber && !allRed && !p3p4Green && seconds<currentTime+p1p2GreenTime+p3p4GreenTime+amberTime*3)
		{
			p3p4Amber = true;
		}
		else if(!p1p2Green && !p1p2Amber && !p3p4Green && !p3p4Amber && seconds<currentTime+p1p2GreenTime+p3p4GreenTime+amberTime*4)
		{
			allRed = true;
		}
		else
		{
			simPanel.setp3p4WasGreen(true);
			simPanel.setSimEnd(true);
		}
		
		if(p1p2Green)
		{
			simPanel.changeImageBR("redBR.jpg");
			simPanel.changeImageTL("redTL.jpg");
			simPanel.changeImageTR("greenTR.jpg");
			simPanel.changeImageBL("greenBL.jpg");
			//System.out.println("p1p2Green is: "+ p1p2Green);
		}
		else if(p1p2Amber)
		{
			simPanel.changeImageBR("redBR.jpg");
			simPanel.changeImageTL("redTL.jpg");
			simPanel.changeImageTR("amberTR.jpg");
			simPanel.changeImageBL("amberBL.jpg");
			//System.out.println("p1p2Amber is: "+ p1p2Amber);
		}
		else if(allRed)
		{
			simPanel.changeImageBR("redBR.jpg");
			simPanel.changeImageTL("redTL.jpg");
			simPanel.changeImageTR("redTR.jpg");
			simPanel.changeImageBL("redBL.jpg");
			//System.out.println("allRed is: "+ allRed);
		}
		else if(p3p4Green)
		{
			simPanel.changeImageBR("greenBR.jpg");
			simPanel.changeImageTL("greenTL.jpg");
			simPanel.changeImageTR("redTR.jpg");
			simPanel.changeImageBL("redBL.jpg");
			//System.out.println("p3p4Green is: "+ p3p4Green);
		}
		else if(p3p4Amber)
		{
			simPanel.changeImageBR("amberBR.jpg");
			simPanel.changeImageTL("amberTL.jpg");
			simPanel.changeImageTR("redTR.jpg");
			simPanel.changeImageBL("redBL.jpg");
			//System.out.println("p3p4Amber is: "+ p3p4Amber);
		}
		
		if(!p1p2Green && !p1p2Amber && !p3p4Green && !p3p4Amber && !allRed)
		{
			currentTimeFlag = false;
		}
		
		p1p2Green = false;
		p1p2Amber = false;
		allRed = false;
		p3p4Green = false;
		p3p4Amber = false;
		//System.out.println("currentTime is: "+ currentTime);
		//System.out.println("p1p2GreenTime is: "+ p1p2GreenTime);
		//System.out.println("p3p4GreenTime is: "+ p3p4GreenTime);
		//System.out.println("currentTimeFlag is: "+ currentTimeFlag);
		/*System.out.println("p1p2Green is: "+ p1p2Green);
		System.out.println("p1p2Amber is: "+ p1p2Amber);
		System.out.println("allRed is: "+ allRed);
		System.out.println("p3p4Green is: "+ p3p4Green);
		System.out.println("p3p4Amber is: "+ p3p4Amber);*/
	}
	
	public void existingSystem(boolean firstRunFlag)
	{
		SimPanel simPanel = new SimPanel();
		simPanel.setSimEnd(false);

		if(firstRunFlag)
		{
			currentTime = 0;
			currentTimeFlag = true;
		}
		else if(!currentTimeFlag)
		{
			currentTime = seconds;
			currentTimeFlag = true;
		}
		
		if(!start)
		{
			allRed = true;
		}
		else if(seconds<currentTime+maxCars)
		{
			p1p2Green = true;
			//System.out.println("p1p2Green is: "+ p1p2Green);
		}
		else if(!p1p2Green && seconds<currentTime+maxCars+amberTime)
		{
			p1p2Amber = true;
		}
		else if(!p1p2Green && !p1p2Amber && seconds<currentTime+maxCars+amberTime*2)
		{
			allRed = true;
		}
		else if(!p1p2Green && !p1p2Amber && !allRed && seconds<currentTime+maxCars+maxCars+amberTime*2)
		{
			p3p4Green = true;
		}
		else if(!p1p2Green && !p1p2Amber && !allRed && !p3p4Green && seconds<currentTime+maxCars+maxCars+amberTime*3)
		{
			p3p4Amber = true;
		}
		else if(!p1p2Green && !p1p2Amber && !p3p4Green && !p3p4Amber && seconds<currentTime+maxCars+maxCars+amberTime*4)
		{
			allRed = true;
		}
		else
		{
			simPanel.setp3p4WasGreen(true);
			simPanel.setSimEnd(true);
		}
		
		if(p1p2Green)
		{
			simPanel.changeImageBR("redBR.jpg");
			simPanel.changeImageTL("redTL.jpg");
			simPanel.changeImageTR("greenTR.jpg");
			simPanel.changeImageBL("greenBL.jpg");
			//System.out.println("p1p2Green is: "+ p1p2Green);
		}
		else if(p1p2Amber)
		{
			simPanel.changeImageBR("redBR.jpg");
			simPanel.changeImageTL("redTL.jpg");
			simPanel.changeImageTR("amberTR.jpg");
			simPanel.changeImageBL("amberBL.jpg");
			//System.out.println("p1p2Amber is: "+ p1p2Amber);
		}
		else if(allRed)
		{
			simPanel.changeImageBR("redBR.jpg");
			simPanel.changeImageTL("redTL.jpg");
			simPanel.changeImageTR("redTR.jpg");
			simPanel.changeImageBL("redBL.jpg");
			//System.out.println("allRed is: "+ allRed);
		}
		else if(p3p4Green)
		{
			simPanel.changeImageBR("greenBR.jpg");
			simPanel.changeImageTL("greenTL.jpg");
			simPanel.changeImageTR("redTR.jpg");
			simPanel.changeImageBL("redBL.jpg");
			//System.out.println("p3p4Green is: "+ p3p4Green);
		}
		else if(p3p4Amber)
		{
			simPanel.changeImageBR("amberBR.jpg");
			simPanel.changeImageTL("amberTL.jpg");
			simPanel.changeImageTR("redTR.jpg");
			simPanel.changeImageBL("redBL.jpg");
			//System.out.println("p3p4Amber is: "+ p3p4Amber);
		}
		
		if(!p1p2Green && !p1p2Amber && !p3p4Green && !p3p4Amber && !allRed)
		{
			currentTimeFlag = false;
		}
		
		p1p2Green = false;
		p1p2Amber = false;
		allRed = false;
		p3p4Green = false;
		p3p4Amber = false;
		//System.out.println("currentTime is: "+ currentTime);
		//System.out.println("p1p2GreenTime is: "+ p1p2GreenTime);
		//System.out.println("p3p4GreenTime is: "+ p3p4GreenTime);
		//System.out.println("currentTimeFlag is: "+ currentTimeFlag);
		/*System.out.println("p1p2Green is: "+ p1p2Green);
		System.out.println("p1p2Amber is: "+ p1p2Amber);
		System.out.println("allRed is: "+ allRed);
		System.out.println("p3p4Green is: "+ p3p4Green);
		System.out.println("p3p4Amber is: "+ p3p4Amber);*/
	}
	
	public void setSimStatus(boolean status, int system)
	{
		start = status;
		select = system;
	}
	
	public boolean getSimStatus()
	{	
		return start;
	}
	
	public int getSystemUsed()
	{	
		return select;
	}
	
	public void setP1Cars(int cars)
	{
		p1Cars = cars;
	}
	
	public int getP1Cars()
	{	
		return p1Cars;
	}
	
	public void setP2Cars(int cars)
	{
		p2Cars = cars;
	}
	
	public int getP2Cars()
	{	
		return p2Cars;
	}
	
	public void setP3Cars(int cars)
	{
		p3Cars = cars;
	}
	
	public int getP3Cars()
	{	
		return p3Cars;
	}
	
	public void setP4Cars(int cars)
	{
		p4Cars = cars;
	}
	
	public int getP4Cars()
	{	
		return p4Cars;
	}

}
