public class Tree
{
	protected String treeID;
	protected String size;
	protected String height;
	protected String location;
	protected String proximityToHouse;
	protected String clientID;
	protected String quoteID;
	protected String totalTreesCut;
	protected String totalAmountPaid;
	protected String totalBalance;
	
	//Constructors
	public Tree() {}
	
	public Tree(String treeID)
	{
		this.treeID = treeID;
	}
	
	public Tree(String clientID, String totalTreesCut, String totalAmountPaid, String totalBalance)
	{
		this.clientID = clientID;
		this.totalTreesCut = totalTreesCut;
		this.totalAmountPaid = totalAmountPaid;
		this.totalBalance = totalBalance;
	}
	
	public Tree(String treeID, String size, String height, String location, String proximityToHouse, String clientID, String quoteID)
	{
		this(size, height, location, proximityToHouse, clientID, quoteID);
		this.treeID = treeID;
	}
	
	public Tree(String size, String height, String location, String proximityToHouse, String clientID, String quoteID)
	{
		this.size = size;
		this.height = height;
		this.location = location;
		this.proximityToHouse = proximityToHouse;
		this.clientID = clientID;
		this.quoteID = quoteID;
	}
	
	//Getter and setter methods
	public String getTreeID()
	{
		return treeID;
	}
	
	public void setTreeID(String treeID)
	{
		this.treeID = treeID;
	}
	
	public String getSize()
	{
		return size;
	}
	
	public void setSize(String size)
	{
		this.size = size;
	}
	
	public String getHeight()
	{
		return height;
	}
	
	public void setHeight(String height)
	{
		this.height = height;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public String getProximityToHouse()
	{
		return proximityToHouse;
	}
	
	public void setProximityToHouse(String proximityToHouse)
	{
		this.proximityToHouse = proximityToHouse;
	}
	
	public String getClientID()
	{
		return clientID;
	}
	
	public void setClientID(String clientID)
	{
		this.clientID = clientID;
	}
	
	public String getQuoteID()
	{
		return quoteID;
	}
	
	public void setQuoteID(String quoteID)
	{
		this.quoteID = quoteID;
	}
	
	public String getTotalTreesCut()
	{
		return totalTreesCut;
	}
	
	public void setTotalTreesCut(String totalTreesCut)
	{
		this.totalTreesCut = totalTreesCut;
	}
	
	public String getTotalAmountPaid()
	{
		return totalAmountPaid;
	}
	
	public void setTotalAmountPaid(String totalAmountPaid)
	{
		this.totalAmountPaid = totalAmountPaid;
	}
	
	public String getTotalBalance()
	{
		return totalBalance;
	}
	
	public void setTotalBalance(String totalBalance)
	{
		this.totalBalance = totalBalance;
	}
}