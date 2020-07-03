
public class Application extends AbstractContent {
	
	private String operatingSysType;
	
	public Application(String appID, String appName, double price, String operatingSysType) {
		
		super(appID, appName, Globals.DEFAULT_NUM_DOWNLOADS, price);
		this.operatingSysType = operatingSysType;
	}
	
	// Overloaded constructor for initialization without price
	public Application(String appID, String appName, String operatingSysType) {
		
		super(appID, appName, Globals.DEFAULT_NUM_DOWNLOADS, Globals.DEFAULT_PRICE);
		this.operatingSysType = operatingSysType;
	}
	
	
	// Getters Setters Starts Here
	public String getOperatingSysType() {
		return operatingSysType;
	}

	public void setOperatingSysType(String operatingSysType) {
		this.operatingSysType = operatingSysType;
	}
	// Getters Setters Ends Here

	@Override
	public String toString() {
		
		return "Application [operatingSysType=" + operatingSysType + "]"+ "AbstractContent [contentID=" + contentID + ", name=" + name + ", numberOfDownloads=" + numberOfDownloads
				+ ", price=" + price + "]";
	}
		
}
