public class Quote 
{
	protected String quoteID;
	protected String initialPrice;
	protected String timeWindow;
	protected String status;
	protected String clientID;
	
	//Constructors
	public Quote() {}
	
	public Quote(String quoteID) 
	{
		this.quoteID = quoteID;
	}
	
	public Quote(String quoteID, String initialPrice, String timeWindow, String status, String clientID)
	{
		this(initialPrice, timeWindow, status, clientID);
		this.quoteID = quoteID;
		
	}
	
	public Quote(String initialPrice, String timeWindow, String status, String clientID)
	{
		this.initialPrice = initialPrice;
		this.timeWindow = timeWindow;
		this.status = status;
		this.clientID = clientID;
	}
	
	//Getter and setter methods
	public String getQuoteID() {
		return quoteID;
	}
	
	public void setQuoteID(String quoteID)
	{
		this.quoteID = quoteID;
	}
	
	public String getInitialPrice()
	{
		return initialPrice;
	}
	
	public void setInitialPrice(String initialPrice)
	{
		this.initialPrice = initialPrice;
	}
	
	public String getTimeWindow()
	{
		return timeWindow;
	}
	
	public void setTimeWindow(String timeWindow)
	{
		this.timeWindow = timeWindow;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getClientID()
	{
		return clientID;
	}
	
	public void setClientID(String clientID)
	{
		this.clientID = clientID;
	}
}