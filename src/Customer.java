import java.util.ArrayList;

public class Customer extends User
{
	private double availableFunds;
	private ArrayList<AbstractContent> downloadedContents;
	private boolean isPremium = false;

	
	// Constructor type 1
	public Customer(String userID, String userName, String phoneNumber, double availableFunds) {
		super(userID, userName, phoneNumber);
		this.setAvailableFunds(availableFunds);
		this.downloadedContents = new ArrayList<AbstractContent>();
	}

	
	// Constructor type 2
	public Customer(String userID, String userName, String phoneNumber) {
		super(userID, userName, phoneNumber);
		this.setAvailableFunds(Globals.INITIAL_FUNDS);
		this.downloadedContents = new ArrayList<AbstractContent>();
	}

	
	@Override
	public String toString() {
		return "Customer [phoneNumber=" + phoneNumber + ", availableFunds=" + availableFunds + ", userID=" + userID
				+ ", userName=" + name + "]";
	}
	
	
	// Download the supplied list of contents
	public boolean download(AbstractContent [] contentsList) {
		
		if(checkBulkPurchasable(contentsList)) {
			for (AbstractContent content : contentsList) {
				this.download(content);
			}
		}else {
			System.err.println("Insufficient funds. Order can not be placed");
		}
		return true;
	}
	
	
	// Download single content, if customer is premium then apply discount as per Globals variable
	public boolean download(AbstractContent content) {
		double userAvailableFunds = this.getAvailableFunds();
		double contentPrice = content.getPrice();
		
		// 20% discount for premium customer
		if (this.isPremium()) {
			contentPrice = contentPrice * (1.00 - Globals.DISCNT_FOR_PREM_CUST);
		}
		
		if ( userAvailableFunds >= contentPrice ) {
			this.setAvailableFunds(userAvailableFunds - contentPrice);
			content.setNumberOfDownloads(content.getNumberOfDownloads() + 1);
			content.downloadedByList.add(this);
			this.downloadedContents.add(content);
			System.out.println("Downloaded : "+content.getName());
			
		} else {
			System.err.println("Insufficient funds. Order can not be placed");
			return false;
		}
		return true;
	}
	
	
	// Checks if user funds are more than the price of all items.
	public boolean checkBulkPurchasable(AbstractContent [] contentsList) {
		
		double userAvailableFunds = this.getAvailableFunds();
		
		for (int i = 0, listLen = contentsList.length; i < listLen ; i++) {
			
			double contentPrice = contentsList[i].getPrice();
			
			// 20% discount for premium customer
			if (this.isPremium()) {
				contentPrice = contentPrice * (1.00 - Globals.DISCNT_FOR_PREM_CUST);
			}
			
			if ( userAvailableFunds >= contentPrice ) {
				
				userAvailableFunds = userAvailableFunds - contentPrice;
			}else{
				
				return false;
			}
		}
		
		return true;
	}
	
	
	// Upgrades the customer to premium
	public boolean becomePremium() {
		
		// Check if the customer is already a premium customer, If yes then don't deduct the balance.
		if (! this.isPremium()) {
			if (this.availableFunds > Globals.PREMIUM_MEMBERSHIP_FEES) {
				this.setAvailableFunds(availableFunds - Globals.PREMIUM_MEMBERSHIP_FEES);
				
				this.setPremium(true);
				
				return true;
			}
		}
	
		return false;
	}
	
	
	// Shows the list of contents downloaded by users
	public void showDownloads() {
		int downloadedContentCount = this.downloadedContents.size();
		
		if(downloadedContentCount == 0)
		{
			System.out.println("There are no downloads by "+this.getName());
			return;
		}
		
		System.out.println(this.getName()+" downloaded "+downloadedContentCount+" contents.");
		
		System.out.format("%32s\n", "+--------------------------------+");
		System.out.format("|%-32s|\n", "Content Name");
		System.out.format("%32s\n", "+--------------------------------+");
		for (AbstractContent downloadedContent : this.downloadedContents) {
			System.out.format("|%-32s|\n", downloadedContent.getName());
			System.out.format("%32s\n", "+--------------------------------+");
		}
	}
	
	// Getter Setters Starts Here
	public double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(double availableFunds) {
		this.availableFunds = availableFunds;
	}

	public ArrayList<AbstractContent> getDownloadedContents() {
		return downloadedContents;
	}

	public void setDownloadedContents(ArrayList<AbstractContent> downloadedContents) {
		this.downloadedContents = downloadedContents;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}
	
	// Getter Setter Ends Here

}
