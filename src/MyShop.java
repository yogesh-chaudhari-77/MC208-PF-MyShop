import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

public class MyShop {
	
	private ArrayList<AbstractContent> myShopContents;
	private ArrayList<User> users;
	
	
	public MyShop() {
		
		this.myShopContents = new ArrayList<AbstractContent>();
		this.users = new ArrayList<User>();
	}
	

	// Adds supplied content to the shopContents
	public void addContent(AbstractContent content) {
			this.myShopContents.add(content);
	}

	
	// Shows the list of the contents in MyShop
	public void showContent() {
		
		if (myShopContents == null || myShopContents.size() == 0) {
			System.err.println("No contents in shop.");
			return;
		}
		
		// Header
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%35s\n", "C-O-N-T-E-N-T-S");
			
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", "Sr", "ID", "Name", "Downloads", "Price");
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
				
		// Body
		for (int i = 0, listLen = myShopContents.size(); i < listLen; i++) {
			
			AbstractContent content = myShopContents.get(i);
			
			System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", i+1, content.getContentID(), content.getName(), content.numberOfDownloads, String.format("%.2f", content.getPrice()));
			System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		}
	}
		
	
	// Adds users to myshop
	public void addUser(User user) {
		this.users.add(user);
	}	
	
	
	// Shows the list of all users in myShop (Admins and Customers)
	public void showUser() {
		
		if (this.getUsers() == null || this.getUsers().size() == 0 ) {
			System.err.println("Shop has no users yet");
			return;
		}
		
		this.printAllCustomers();
		this.printAllAdmins();
	}
	
	
	// Prints list of admin users only
	public void printAllAdmins() {
		
		// Header
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%35s\n", "A-D-M-I-S");
		
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", "Sr", "ID", "Name", "Password", "Level");
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		
		// Body
		for (int i = 0, listLen = users.size(); i < listLen; i++) {
			User user = users.get(i);
			
			if(user instanceof Admin) {
				System.out.format("%-3s|%-10s|%-32s|%-10s|%-10d|\n", i+1, user.getUserID(), user.getName(), ((Admin) user).getPassword(), ((Admin) user).getLevel());
				System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
			}
		}
		
	}
	
	
	// Prints list of customer users only
	public void printAllCustomers() {
		
		// Header
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%40s\n", "C-U-S-T-O-M-E-R-S");
		
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", "Sr", "ID", "Name", "Phone", "Balance");
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		
		// Body
		for (int i = 0, listLen = users.size(); i < listLen; i++) {
			User user = users.get(i);
			
			if(user instanceof Customer) {
				System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", i+1, user.getUserID(), user.getName(), user.getPhoneNumber(), String.format("%.2f", ((Customer) user).getAvailableFunds()));
				System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
			}
		}
	}
	
	
	// Shows reviews of all products in myShop
	public void showAllReviews() {
		
		if (myShopContents == null || myShopContents.size() == 0) {
			System.err.println("No contents in shop.");
			return;
		}
		
		// Header
		System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
		System.out.format("%20s\n", "A-L-L P-R-O-D-U-C-T R-E-V-I-E-W-S");
		
		// Body
		for (int i = 0, listLen = myShopContents.size(); i < listLen; i++) {
			AbstractContent content = myShopContents.get(i);
			ArrayList<Comment> contentComments = content.getCommentsList();
			
			System.out.format("%3s%13s%38s%10s\n", "---+","-------------+","--------------------------------------+","----------+");
			System.out.format("%-3s|%-13s|%-38s|%-10s|\n", "Sr", "ID", "Name", "R.Count");
			System.out.format("%3s%13s%38s%10s\n", "---+","-------------+","--------------------------------------+","----------+");
			System.out.format("%-3s|%-13s|%-38s|%-10s|\n", 1, content.getContentID(), content.getName(), contentComments.size());
			System.out.format("%3s%13s%38s%10s\n", "---+","-------------+","--------------------------------------+","----------+");
		
			System.out.format("%-3s|%-13s|%-8s|%-40s|\n", "Sr", "User Name", "Ratings", "Comment");
			System.out.format("%3s%13s%8s%40s\n", "---+","-------------+","--------+","----------------------------------------+");
			
			if(contentComments.size() == 0) {
				System.out.format("%45s\n", "N-O R-E-V-I-E-W-S S-O F-A-R");
				System.out.format("%3s%10s%8s%40s\n", "---+","-------------+","--------+","----------------------------------------+");
			}
			
			for (int c = 0, comListLen = contentComments.size(); c < comListLen; c++) {
				Comment comment = contentComments.get(c);
				
				System.out.format("%-3s|%-13s|%-8s|%-40s|\n", c+1, comment.customerRef.getName(), comment.getRating(), comment.getComment());
				System.out.format("%3s%13s%8s%40s\n", "---+","-------------+","--------+","----------------------------------------+");
			}
		}
		
	}
	
	
	// Prints the name, id and number of downloads of all contents in MyShop
	public void showDownloads() {
		
		if (myShopContents == null || myShopContents.size() == 0) {
			System.err.println("No contents in shop.");
			return;
		}

		// Header
		System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
		System.out.format("%30s\n", "S-H-O-P C-O-N-T-E-N-T-S D-O-W-N-L-O-A-D-S");
		
		System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
		System.out.format("%-3s|%-10s|%-32s|%-10s|\n", "Sr", "ID", "Name", "Donwloads");
		System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
		
		// Body
		for (int i = 0, listLen = myShopContents.size(); i < listLen; i++) {
			AbstractContent content = myShopContents.get(i);
			
			System.out.format("%-3s|%-10s|%-32s|%-10d|\n", i+1, content.getContentID(), content.getName(), content.getNumberOfDownloads());
			System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
		}
	}
	
	
	// Alters the price of contents in bulk. Can be increased or reduced. Required admin level specified in Globals
	public void setPrice(boolean adminLoginStatus, int adminLevel, double pctFactor) {
		
		if(adminLoginStatus && adminLevel > Globals.BULK_PRICE_ALTER_LEVEL) {
			
			System.out.format("%-30s%-10s%-10s", "Content", "Old Price", "New Price");
			System.out.println("\n"+Globals.LINE_SEPERATOR+Globals.LINE_SEPERATOR);
			
			for (AbstractContent item : myShopContents) {
				double newPrice = item.getPrice() * (1.0 + pctFactor);
				item.setPrice(adminLoginStatus,  newPrice);
			}
		}else {
			System.err.println("Either admin is not logged in or admin doesn't have enough level to perform this operation.");
		}
	}
	
	
	// Returns the customer from supplied ID
	public User getCustomerFromCustomerID(String customerID) {
		
		for (int i = 0; i < this.users.size(); i++) {
			User tempCustRef = this.users.get(i);
			if (tempCustRef.getUserID() == customerID) 
				 return tempCustRef;
		}
		return null;
	}
	
	// Getters Setters Starts Here
	public ArrayList<AbstractContent> getMyShopContents() {
		return myShopContents;
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	// Getters Setters Ends Here
}