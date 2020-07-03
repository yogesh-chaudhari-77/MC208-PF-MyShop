
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.text.AbstractDocument.Content;


/*  
 * My name : Yogeshwar Girish Chaudhari
 * 
 * My student ID : S3828116
 * 
 * The highest level I've completed is: HD 
 */

/*
 * References : 
 				Abstract class
  				https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
 				
 				Formatter
 				https://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html
 				
 				System Properties
 				https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
 */

public class MyShopMain {
	
	private static boolean quit = false;
	private MyShop shop;
	
	private ScannerUtil scannerUtil = ScannerUtil.createInstance().consoleReader();
	
	
	public MyShopMain() {
		this.shop = new MyShop();
	}
	
	
    public static void main(String[] args) {
        
    	MyShopMain driver = new MyShopMain();
        
    	// Prepares the system with provided test data
    	driver.shop = driver.prepareSystemWithTestData();
    	
        driver.run();
    	
        driver.clearConsole();
        
        // Closing open resources
        driver.scannerUtil.closeReader();
        
        System.exit(0);
    }
    
    
    // Show menu -> Get user Choice -> Perform Operation -> repeate
    public void run() {

    	do {
    		this.showMenu();
        	int userChoice = this.getChoice();
        	this.executeOperation(userChoice);
        	
    	}while(quit != true);
    	
    }
    
    
    // Prints Menu
    public void showMenu() {
    	System.out.println("\t\t\t Menu \t\t\t");
    	System.out.println(Globals.LINE_SEPERATOR);
    	System.out.println("1.  List All Contents 			\n"
    					 + "2.  List All Users				\n"
    					 + "3.  Download Content/s			\n"
    					 + "4.  Show Shop Reviews (All Product Reviews)	\n"
    					 + "5.  Upgrade To Premium 			\n"
    					 + "6.  Show All Comments			\n"
    					 //---------------------------------------//
						 + "7.  Add Book					\n"
						 + "8.  Add Magazine				\n"
    					 + "9.  Add Application				\n"
    					 + "10. Add Customer				\n"
    					 + "11. Add Admin					\n"
    					 + "12. Add Review					\n"
    					 + "13. Show a Product Review				\n"
    					 //------------Admin Functions-----------//
    					 + "20. Reset Price 				\n"
    					 + "21. Bulk Price Alteration		\n"
    					 //------------Shop Functions------------//
    					 + "30. All Downloads in Shop		\n"
    					 + "31. All Downloads By a User		\n"
    					 + "32. All Downloads for a Product	\n"
    					 // -----------User Functions------------//
    					 + "40. Change Name					\n"
    					 // ---------------- END ----------------//
    					 + "50. Quit						\n"
    					);
    }
    

    // Get user choice in terms of operation. It will 
    public int getChoice() {

    	System.out.print("Please Enter Your Choice : ");
    	return scannerUtil.readInt("Please Enter Choice Again :");

    }

    
    // Mapping of operation with numbers using switch statement
	public void executeOperation(int userChoice) {
		
		switch(userChoice) {
		case Globals._LIST_ALL_CONTENTS :	// 1 
			this.shop.showContent();
			break;
			
		case Globals._LIST_ALL_USERS :		// 2
			this.shop.showUser();
			break;

		case Globals._DOWNLOAD_CONTENT_S : 	// 3
			if(this.downloadContent()) {
				System.out.println("Task Ended");
			}
			break;
		
		case Globals._SHOW_SHOP_REVIEWS : 	// 4
			this.showShopReviews();
			break;
			
		case Globals._UPGRADE_TO_PREMIUM : 	// 5
			if(this.upgradeToPremium()) {
				System.out.println("Customer has been successfully upgraded to premium");
			}else {
				System.out.println("Either customer is already a premium or he/she does't have sufficient balance.\nPremium Fees : "+Globals.PREMIUM_MEMBERSHIP_FEES);
			}
			break;
			
		case Globals._SHOW_ALL_COMMENTS :	// 6
			this.shop.showAllReviews();
			break;
			
		case Globals._CREATE_APP :
			if(this.createApplication()) {
				System.out.print(Globals.LINE_SEPERATOR+"\n"+this.shop.getMyShopContents().get(this.shop.getMyShopContents().size() - 1).toString()+"\n");
				System.out.println("Application Add Status : Success\n"+Globals.LINE_SEPERATOR);
			}
			break;
		
		case Globals._CREATE_MAGZINE :
			if(this.createMagazine()) {
				System.out.print(Globals.LINE_SEPERATOR+"\n"+this.shop.getMyShopContents().get(this.shop.getMyShopContents().size() - 1).toString()+"\n");
				System.out.println("Magzine Add Status : Success\n"+Globals.LINE_SEPERATOR);
			}
			break;
		
		case Globals._CREATE_BOOK :
			if(this.createBook()) {
				System.out.print(Globals.LINE_SEPERATOR+"\n"+this.shop.getMyShopContents().get(this.shop.getMyShopContents().size() - 1).toString()+"\n");
				System.out.println("Book Add Status : Success\n"+Globals.LINE_SEPERATOR);
			}
			break;
	
		case Globals._CREATE_CUSTOMER :
			if(this.addCustomer()) {
				System.out.print(Globals.LINE_SEPERATOR+"\n"+this.shop.getUsers().get(this.shop.getUsers().size() - 1).toString()+"\n");
				System.out.println("Customer Add Status : Success\n");
			}
			break;
			
		case Globals._CREATE_ADMIN :
			if(this.addAdmin()) {
				System.out.print(Globals.LINE_SEPERATOR+"\n"+this.shop.getUsers().get(this.shop.getUsers().size() - 1).toString()+"\n");
				System.out.println("Customer Admin Status : Success\n"+Globals.LINE_SEPERATOR);
			}
			break;
			
		case Globals._ADD_REVIEW :
			
			Comment newComment = this.addReview();
			
			if(newComment != null) {
				System.out.println(Globals.LINE_SEPERATOR+"\n"+newComment.toString()+"\n");
				System.out.println("Add Review Status : Success\n"+Globals.LINE_SEPERATOR);
			}else {
				System.out.println("Problem occured while adding comment");
			}
			break;
			
			
		case Globals._SHOW_PRODUCT_REVIEW :		// 13
			this.showProductReview();
			break;
		
		// Admins Functions 
		case Globals._RESET_PRICE:
			this.resetPrice();
			break;
			
		case Globals._BULK_PRICE_ALTERATION :
			this.bulkPriceAlteration();
			this.shop.showContent();
			break;
			
		case Globals._ALL_DOWNLOADS_SHOP :  	// 30
			this.shop.showContent();
			break;
		
		case Globals._ALL_DOWNLOADS_BY_USER : 	// 31
			this.showAllDownloadsByUser();
			break;
			
		case Globals._PRODUCT_DOWNLOADED_BY : 	// 32
			this.showProductDownloadedByList();
			break;
			
		case Globals._CHANGE_NAME : 			// 40
				this.changeNameUser();
			break;
			
		case Globals._QUIT : 					// 50
			quit = true;
			break;
			
		default :
			System.err.println("Please select from above options");
		}
	}
	
	
	// 31 - Shows the list of users who downloaded the selected product
	public void showProductDownloadedByList() {
		AbstractContent contentRef = this.printContentsAndReturnSelection();
		
		if(contentRef != null) {
			contentRef.showDownloads();
		}else {
			System.err.println("Some error occured");
		}
	}
	
	
	// Creates new content: Application and adds it to MyShop
	public boolean createApplication() {
		
		double appPrice = Globals.DEFAULT_PRICE;
		
		System.out.println("Creating New Application");
		System.out.println(Globals.LINE_SEPERATOR);
		
		System.out.print("Do you have price of application ? [Y/N] : ");
		String confirmation = scannerUtil.readYesNo("Do you have price of application ? [Y/N] : ");
		
		System.out.println("Please Enter Application Details : \n"+Globals.LINE_SEPERATOR+"\n");
		
		System.out.print("App ID : ");
		String appId = scannerUtil.readString();
		
		System.out.print("App Name : ");
		String appName = scannerUtil.readString();
		
		if(confirmation.equalsIgnoreCase("Y")) {
			do {
				System.out.print("Price : ");
				appPrice = scannerUtil.readDouble("Price :");
				
				if(appPrice < 0) 
					System.out.println("Application Can't Be Less Than 0");
			}while(appPrice < 0);
		}
		
		System.out.print("Operating System : ");
		String operatingSysType = scannerUtil.readString();
		
		if(confirmation.equalsIgnoreCase("Y")) {
			this.shop.addContent(new Application(appId, appName, appPrice, operatingSysType));
		}else {
			this.shop.addContent(new Application(appId, appName, operatingSysType));
		}
		
		return true;
	}
	
	
	// Creates new publication Book and adds it to MyShop
	public boolean createBook() {
        
		double bookPrice = Globals.DEFAULT_PRICE;
		ArrayList<String> authors = new ArrayList<String>();
		
		System.out.println("Add Publications : Book");
		System.out.println(Globals.LINE_SEPERATOR);
		
		System.out.println("Please Enter Book Details : \n"+Globals.LINE_SEPERATOR+"\n");
		
		System.out.print("Book ID : ");
		String bookID = scannerUtil.readString();
		
		System.out.print("Name : ");
		String bookName = scannerUtil.readString();
		
		do {
			System.out.print("Price : ");
			bookPrice = scannerUtil.readDouble("Price : ");
			
			if(bookPrice < 0) 
				System.out.println("Book Price Can't Be Less Than 0");
		}while(bookPrice < 0);
		
		System.out.print("Publisher : ");
		String bookPublisher = scannerUtil.readString();
		
		System.out.print("Number Of Pages : ");
		int numberOfPages = scannerUtil.readInt("Number of Pages : ");
		
		// Accepting authors starts 
		byte i = 1;
		String moreAuthors = "N";
		do {
			System.out.print("Author-"+i+" Name : ");
			String authorName = scannerUtil.readString();
			authors.add(authorName);
			System.out.print("Add one more author ? [Y/N] :");
			i++;
			moreAuthors = scannerUtil.readYesNo("Add one more author ? [Y/N] :");
			
		}while(moreAuthors.equalsIgnoreCase("Y"));
		// Accepting authors ends
		
		// Convert the arraylist into array and then pass
		this.shop.addContent(new Book(bookID, bookName, bookPrice, bookPublisher, numberOfPages, authors.toArray(new String [authors.size()])));
		
		return true;
	}
	
	
	// Creates new publication Magazine and adds it to MyShop
	public boolean createMagazine() {
		
		double magzPrice = Globals.DEFAULT_PRICE;
		
		System.out.println("Add Publications : Magzine");
		System.out.println(Globals.LINE_SEPERATOR);
		
		System.out.println("Please Enter Magzine Details : \n"+Globals.LINE_SEPERATOR+"\n");
		
		System.out.print("Magzine ID : ");
		String magzId = scannerUtil.readString();
		
		System.out.print("Name : ");
		String magzName = scannerUtil.readString();
		
		do {
			System.out.print("Price : ");
			magzPrice = scannerUtil.readDouble("Price : ");
			
			if(magzPrice < 0) 
				System.out.println("Magazine Price Can't Be Less Than Zero");
		}while(magzPrice < 0);
		
		
		System.out.print("Publisher : ");
		String magzPublisher = scannerUtil.readString();
		
		System.out.print("Number Of Pages : ");
		int numberOfPages = scannerUtil.readInt("Number of Pages : ");
		
		System.out.print("Volume : ");
		int volume = scannerUtil.readInt("Volume : ");
		
		this.shop.addContent(new Magazine(magzId, magzName, magzPrice, magzPublisher, numberOfPages, volume));
		
		return true;
	}
	
	// Creates new Customer and adds to MyShop
	public boolean addCustomer() {
		
		System.out.println("Add User : Customer");
		System.out.println(Globals.LINE_SEPERATOR);
		
		System.out.print("Customer ID : ");
		String customerID = scannerUtil.readString();
		
		System.out.print("Name : ");
		String customerName = scannerUtil.readString();
		
		System.out.print("Phone : ");
		String phone = scannerUtil.readString("[0-9]+", "Invalid Phone Number", "Phone : ");	// Exact pattern unknown
		
		System.out.print("Do you have a initial funds ? [Y/N] : ");
		String fundsYesNo = scannerUtil.readYesNo("Do you have a initial funds ? [Y/N] : ");
		
		double initialFunds = 0;
		
		// For existing customers, initial funds are required and will be overriden. 
		// For new customers initial funds are specified in Globals 
		if(fundsYesNo.equalsIgnoreCase("Y")) {
			do {
				System.out.print("Initial Funds : ");
				initialFunds = scannerUtil.readDouble("Initial Funds : ");
				
				if(initialFunds < 0) {
					System.out.println("Initial Funds Can't Be Less Than 0.");
				}
			} while(initialFunds < 0);
		}
		
		if(fundsYesNo.equalsIgnoreCase("Y")) {
			this.shop.addUser(new Customer(customerID, customerName, phone, initialFunds));
		}else {
			this.shop.addUser(new Customer(customerID, customerName, phone));
		}
		
		return true;
	}
	
	
	// Creates new Admin and adds to MyShop
	public boolean addAdmin() {
		
		System.out.println("Add User : Admin");
		System.out.println(Globals.LINE_SEPERATOR);
		
		System.out.print("Admin ID : ");
		String adminID = scannerUtil.readString();
		
		System.out.print("Name : ");
		String adminName = scannerUtil.readString();
		
		System.out.print("Phone : ");
		String password = scannerUtil.readString("[0-9]+", "Invalid Phone Number", "Phone : ");	// Exact pattern unknown
		
		System.out.print("Level : ");
		int level = scannerUtil.readInt("Level :");
		
		this.shop.addUser(new Admin(adminID, adminName, password, level));
		
		return true;
	}
	
	
	public Comment addReview() {
		
		int ch = 0;
		
		// Sub Menu for Adding Reviews
		do {
			
			System.out.println("1 - Only Comment");
			System.out.println("2 - Only Rating");
			System.out.println("3 - Comment + Rating ");
			System.out.print("Enter Choice : ");
			
			ch = scannerUtil.readInt("Enter Choice");
			
			if(ch < 1 || ch > 3) {
				System.out.println("Invalid Choice. Please try again");
			}
			
		}while(ch < 1 || ch > 3);
		
		switch (ch) {
			case 1:
				return this.addOnlyCommentReview();
			
			case 2:
				return this.addOnlyRatingReview();
				
			case 3:
				return this.addCommentRatingReview();
		}
		
		return null;

	}
	
	
	// Adds new review for content. It does not take rating
	public Comment addOnlyCommentReview() {
		
		Customer custRef = this.printCustomersAndRetSelection();
		AbstractContent contentRef = this.printContentsAndReturnSelection();
		
		System.out.print("\nEnter Comment : ");
		String commentStr = scannerUtil.readString();
		
		Comment newComment = new Comment(custRef, commentStr);
		contentRef.addReview(newComment);
		
		return newComment;
	}

	
	// Adds new review for content. It does not require comment String
	public Comment addOnlyRatingReview() {
		
		Customer custRef = this.printCustomersAndRetSelection();
		AbstractContent contentRef = this.printContentsAndReturnSelection();
		
		int rating = 0;
		do {
			System.out.print("Enter Rating : ");
			rating = scannerUtil.readInt("Enter Rating : ");
		}while(rating < 0 || rating > 5);
		
		Comment newComment = new Comment(custRef, rating);
		contentRef.addReview(newComment);
		
		return newComment;
	}
	
	
	// Adds Review to content with comment and rating 
	public Comment addCommentRatingReview() {
		
		Customer custRef = this.printCustomersAndRetSelection();
		AbstractContent contentRef = this.printContentsAndReturnSelection();
		
		System.out.print("\nEnter Comment : ");
		String commentStr = scannerUtil.readString();
		
		int rating = 0;
		do {
			System.out.print("Enter Rating : ");
			rating = scannerUtil.readInt("Enter Rating : ");
		}while(rating < 0 || rating > 5);
		
		// Create and add new comment to content
		Comment newComment = new Comment(custRef, rating, commentStr);
		contentRef.addReview(newComment);
		
		return newComment;
	}
	
	
	// Allows user to change the name
	public boolean changeNameUser() {
		
		int ch = 0;
		System.out.println("Change Name : User");
		
		// Sub menu for changing name
		do {
			System.out.println("1 - Customer");
			System.out.println("2 - Admin");
			System.out.print("Enter Choice : ");
			
			ch = scannerUtil.readInt("Enter Choice");
			
			if(ch < 1 || ch > 2) {
				System.out.println("Invalid Choice. Please try again");
			}
			
		}while(ch < 1 || ch > 2);
		
		System.out.print("New username");
		String userName = scannerUtil.readString();
		
		switch (ch) {
		
			// Change customer username
			case 1:		
				Customer custRef = printCustomersAndRetSelection();
				custRef.changeName(userName);
			break;
			
			//Change admin username
			case 2:
				Admin adminRef = printAdminsAndReturnSelection();
				adminRef.changeName(userName);
			break;
		}
		
		return true;

	}

	
	// Shows review of a selected product
	public void showProductReview() {
		
		int itemIndex = 0;
		int listSize = this.shop.getMyShopContents().size();
		
		this.printContents();
		
		do {
			System.out.print("Select Content [Enter Serial Number] : ");
			itemIndex = scannerUtil.readInt("Select Content [Enter Serial Number] : ");
			
			if(itemIndex <= 0 || itemIndex > listSize)
				System.out.println("Invalid Choice. Please try again.");
			
		}while(itemIndex <= 0 || itemIndex > listSize);
		
		this.shop.getMyShopContents().get(itemIndex - 1).showReviews();
	}
	
	
	// Show reviews for all products in MyShop
	public void showShopReviews() {
		this.shop.showAllReviews();
	}
	
	
	// Shows the list of contents downloaded by user
	public void showAllDownloadsByUser() {
		
		Customer cust = this.printCustomersAndRetSelection();
		
		cust.showDownloads();
	}
	
	
	// This will be used for downloading single or multiple contents
	public boolean downloadContent() {
		
		Customer cust = printCustomersAndRetSelection();
		int listSize = this.shop.getMyShopContents().size();
		int itemIndex;
		
		printContents();
		
		String moreProducts = "Y";
 		ArrayList<AbstractContent> selectedProducts = new ArrayList<AbstractContent>();
 		
		do {
			System.out.print("Select Item [Enter Serial Number] : ");
			
			do {
				itemIndex = scannerUtil.readInt("Select Item [Enter Serial Number] : ");
				if(itemIndex <= 0 || itemIndex > listSize) {
					System.err.println("Invalid Selection");
				}
			}while(itemIndex <= 0 || itemIndex > listSize);
			
			
			selectedProducts.add(this.shop.getMyShopContents().get(itemIndex -1));
			
			System.out.print("Add more products ? [Y/N] : ");
			moreProducts = scannerUtil.readYesNo("Add more products ? [Y/N] : ");
			
		}while(moreProducts.equalsIgnoreCase("Y"));
		
		// This means that downloading multiple products
		if(selectedProducts.size() > 1) {
			
			return cust.download(selectedProducts.toArray(new AbstractContent [selectedProducts.size()]));
		}else {
			
			// Single product will be downloaded only
			return cust.download(selectedProducts.get(0));
		}
		
	}
	
	
	// Menu Driven - Reset Price Function
	public void resetPrice() {
		
		Admin admin = printAdminsAndReturnSelection();
		
		printContents();
		System.out.print("Select Item [Enter Serial Number] : ");
		int itemIndex = scannerUtil.readInt("Select Item [Enter Serial Number] : ");
		AbstractContent selectedContent = this.shop.getMyShopContents().get(itemIndex - 1);
		
		System.out.println("Name : "+selectedContent.getName()+" | Current Price : "+selectedContent.getPrice());
		
		System.out.print("New Price : ");
		double newPrice = scannerUtil.readDouble("New Price : ");
		
		selectedContent.setPrice(admin.login(), newPrice);
	}
	
	
	// Menu Driven Bulk Reduction
	public void bulkPriceAlteration() {
		
		Admin admin = printAdminsAndReturnSelection();
		
		double pctFactor;
		
		System.out.print("Please enter % [10 for 10% increment] [-10 for 10% reduction] : ");
		pctFactor = scannerUtil.readDouble("Please enter % [10 for 10% increment] [-10 for 10% reduction] : ");
		
		this.shop.setPrice(admin.login(), admin.getLevel(), (pctFactor/100));
	}
	
	
	// Select a customer and then upgrade to premium
	public boolean upgradeToPremium() {
		
		Customer cust = printCustomersAndRetSelection();
		
		return cust.becomePremium();
	}
	
	
	// Prints the items for selection. Make sure that Sr starts with one here that means item can be accessed directly using i-1 
	public void printContents() {
		ArrayList<AbstractContent> shopContents = this.shop.getMyShopContents();
		int i = 1;
		
		System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
		System.out.format("%-3s|%-10s|%-32s|%-10s|\n", "Sr", "ID", "Content Name", "Price");
		System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
		
		for (AbstractContent content : shopContents) {
			System.out.format("%-3s|%-10s|%-32s|%-10s|\n", i, content.getContentID(), content.getName(), content.getPrice());
			System.out.format("%3s%10s%32s%10s\n", "---+","----------+","--------------------------------+","----------+");
			i++;
		}
	}
	
	
	// Prints the list of all contents, waits for input and returns the selected content content
	public AbstractContent printContentsAndReturnSelection() {
		
		AbstractContent contentRef = null;
		
		this.printContents();
		
		
		// Ask for selection and extract the content from the content list
		do {
			System.out.print("Select Content [Enter Serial Number] : ");
			int itemIndex = scannerUtil.readInt("Select Content [Enter Serial Number] : ");
			
			try {
				contentRef = this.shop.getMyShopContents().get(itemIndex - 1);	
			}
			catch(NoSuchElementException | IndexOutOfBoundsException | ClassCastException e) {
				System.out.println("Invalid Selection. Please try again. ");
			}
		}while(contentRef == null);
		
		System.out.println("Selected Content : "+contentRef.getContentID()+" - "+contentRef.getName());
		
		return contentRef;
	}
		
	
	// Only prints the customer of MyShop. NO ADMINS, waits for selection, returns selected customer
	public Customer printCustomersAndRetSelection() {
		
		ArrayList<User> users = this.shop.getUsers();
		int i = 1;
		Customer cust = null;
		
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", "Sr", "ID", "Name", "Premium", "Balance");
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		
		for (User user : users) {
			if(user instanceof Customer) {
				System.out.format("%-3s|%-10s|%-32s|%-10b|%-10s|\n", i, user.getUserID(), user.getName(), ((Customer) user).isPremium(), String.format("%.2f", ((Customer) user).getAvailableFunds()));
				System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
			}
			
			i++;			// Make sure that right index is being referred
		}
		
		// Ask for selection and extract the customer from the Users list, repeate if the customer is null
		do {
			System.out.print("Select Customer [Enter Serial Number] : ");
			int custIndex = scannerUtil.readInt("Select Customer [Enter Serial Number] : ");
			
			try {
				cust = (Customer) this.shop.getUsers().get(custIndex - 1);	
			}
			catch(NoSuchElementException | IndexOutOfBoundsException | ClassCastException e) {
				System.out.println("Invalid Selection. Please try again. ");
			}
			
		}while(cust == null);
		
		return cust;
	}
	
	
	// Prints list of all admins in MyShop, waits for selectiom, returns the selected admin reference
	public Admin printAdminsAndReturnSelection() {
		ArrayList<User> users = this.shop.getUsers();
		int i = 1;
		Admin admin = null;
		
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", "Sr", "ID", "Name", "Password", "Level");
		System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
		
		for (User user : users) {
			if(user instanceof Admin) {
				System.out.format("%-3s|%-10s|%-32s|%-10s|%-10s|\n", i, user.getUserID(), user.getName(), ((Admin) user).getPassword(), ((Admin) user).getLevel());
				System.out.format("%3s%10s%32s%10s%10s\n", "---+","----------+","--------------------------------+","----------+","----------+");
			}
			
			i++;			// Make sure that right index is being referred
		}
		
		// Ask for selection and extract the customer from the Users list
		do {
			System.out.print("Select Admin [Enter Serial Number] : ");
			int adminIndex = scannerUtil.readInt("Select Admin [Enter Serial Number] : ");
			
			try {
				admin = (Admin) this.shop.getUsers().get(adminIndex - 1);	
			}
			catch(NoSuchElementException | IndexOutOfBoundsException | ClassCastException e) {
				System.out.println("Invalid Selection. Please try again. ");
			}
			
		}while(admin == null);
		
		return admin;
	}
    
	
	// Loads the supplied test data in MyShop
    public MyShop prepareSystemWithTestData() {
        
        // ***************   PASS LEVEL  ***************
    	
    	System.out.println("Creating Shop");
                
        // create new publications
        System.out.println("Creating Books ...");
        String[] authors1 = {"L. Tolstoy"};        
        Book b1 = new Book ("b1", "War and Peace", 12.55, "The Russian Messenger", 1225, authors1);
        
        String[] authors2 = {"F. Scott Fitzgerald"};
        Book b2 = new Book ("b2", "The great gatsby", 10, "Charles Scribner's Sons", 180, authors2);
                
        String[] authors3 = {"Thomas H. Cormen", "Charles E. Leiserson", "Ronald L. Rivest", "Clifford Stein"};
        Book b3 = new Book ("b3", "Introduction to algorithms", 100, "MIT Press", 1312, authors3);
        
        System.out.println("Creating Magazines ...");
        Magazine m1 = new Magazine("m1", "Forbes", 8.99, "Forbes Media", 50, 201904);
                
        // add new publications to the shop      
        shop.addContent(b1);
        shop.addContent(b2);
        shop.addContent(b3);
        shop.addContent(m1);
        System.out.println("Contents Has Been Added To The Shop");
        
        // create new applications    
        
        System.out.println("Creating Applications ...");
        Application g1 = new Application("app1", "Pokemon", 5.3, "androidV4");    
        Application g2 = new Application("app2", "Pokemon", 5, "iOSV10");
        //a free app
        Application app1 = new Application("app3", "Calendar", "androidV3"); 

        // add new applications to the shop         
        shop.addContent(g1);
        shop.addContent(g2);
        shop.addContent(app1);
        
        System.out.println("Applications Added");
        
        shop.showContent();

        // ***************  CREDIT LEVEL ***************
                
        // Adding new customers
        System.out.println("Adding Users (Customers & Admins) ...");
		  Customer c1 = new Customer("c1", "coolguy", "0412000", 200); 
		  Customer c2 = new Customer("c2", "neversaynever", "0433191"); 
		  Customer c3 = new Customer("c3", "Hello 123", "0413456", 1000); 
		  Customer c4 = new Customer("c4", "Jackie Chan", "0417654");
		  
		  shop.addUser(c1);
		  shop.addUser(c2);
		  shop.addUser(c3);
		  shop.addUser(c4);
		
		  System.out.println("Users added");
		  // Adding new admins 
		  //Admin a1 = new Admin("a1", "SuperBlack", "opensesame", 10); 
		  //Admin a2 = new Admin("a2", "Adele", "kitty123", 3);
		  
		  Admin a1 = new Admin("a1", "SuperBlack", "password", 10); 
		  Admin a2 = new Admin("a2", "Adele", "password", 3);
		  
		  shop.addUser(a1); 
		  shop.addUser(a2);
		  System.out.println("Admins added");
		  System.out.println("Showing all added users");
		  shop.showUser();
		  
		  System.out.println("Changing username of c2");
		  c2.changeName("neversaybye"); // change the user name
		  
		  System.out.println("Changing username of a1");
		  a1.changeName("superblack"); // make the user name in lowercase
		  
		  shop.showUser();
		
        // ************* DISTINCTION LEVEL ************
        
		  System.out.println("=======");System.out.println("=======");System.out.println("=======");System.out.println("=======");
		
		  System.out.println("Adding Comments");
		  Comment comment1 = new Comment(c1, "This is a fantastic game!");
		  g1.addReview(comment1);
		  
		  Comment comment2 = new Comment(c2, 5); 
		  g1.addReview(comment2);
		  
		  Comment comment3 = new Comment(c3, 3, "This is an average game!");
		  g1.addReview(comment3);
		  
		  Comment comment4 = new Comment(c4, "I quite like this game!", 4);
		  g1.addReview(comment4);
		  
		  g1.addReview(new Comment(c3, "The game crashes frequently"));
		  
		  b1.addReview(new Comment(c2, "I love Tolstoy!"));

		  System.out.println("Review for G1 \n");
		  g1.showReviews(); 			//show comments for g1 
		  
		  System.out.println("Review for B1 \n");
		  b1.showReviews(); 		    //show comments for b1
		  
		  System.out.println("All reviews \n");
		  shop.showAllReviews(); //show all comments ever made
		 
                                
        // Simulating transactions, showing content etc etc.  
        // They can be driven by menu input as well.
		
		 if (c1.download(b1)) 
			 System.out.println(c1.getName() + " bought " + b1.getName()); 
		 else System.out.println("Not enough fund");
		  
		  c1.download(b3); 
		  c1.download(m1);
		  
		  System.out.println("C1 Downloads \n"+Globals.LINE_SEPERATOR+"\n");
		  c1.showDownloads(); // show c1's downloads
		  
		  c2.download(m1); 
		  c3.download(m1);
		  
		  System.out.println("M1 Downloads \n"+Globals.LINE_SEPERATOR+"\n");
		  m1.showDownloads();  //show number of downloads of m1
		  
		  System.out.println("Shop Downloads \n"+Globals.LINE_SEPERATOR+"\n");
		  shop.showDownloads(); //show all comments ever made
		  
		  
        // **************   HD  LEVEL   **************
        
		  System.out.println("Making customers premium ...");
		  c3.becomePremium(); 
		  c3.download(b1); 
		  c3.download(g1); 
		  shop.showDownloads();
		  
		  c4.download(g1); 
		  c4.becomePremium(); 
		  c4.download(m1); 
		  shop.showDownloads();
		  
		  System.out.println("Downloading bulk contents");
		  Customer c5 = new Customer("c5", "neverenough", "0486734", 2000);
		  AbstractContent[] list = {b1, b2, b3, m1, g1, g2, app1};
		  
		  // download many items in one go 
		  c5.download(list); 
		  
		  
		  System.out.println("Shops Downloads \n"+Globals.LINE_SEPERATOR+"\n");
		  shop.showDownloads();
		  
		  System.out.println("Commented admin functions");
		  // an admin can reset the price for an item 
		  //b1.setPrice(a2.login(), 14.25);
		  
		  // an admin with level > 5 bulk reduces all prices by 10%
		  //shop.setPrice(a1.login(), a1.getLevel(), -0.10);
		return shop;
    }
    
    
    // Clears the system console based on the Operating System
    public void clearConsole() {
    	try {
			Thread.sleep(3000);
			
			if(System.getProperty("os.name").toLowerCase().startsWith("linux"))
				Runtime.getRuntime().exec(Globals.unixClearCommand);
			else if (System.getProperty("os.name").toLowerCase().startsWith("windows"))
				Runtime.getRuntime().exec(Globals.windowsClearCommand);
		} catch (Exception e) {
			
			System.out.println("Looks like we ran into problem while clearing console.");
		}
    }
}