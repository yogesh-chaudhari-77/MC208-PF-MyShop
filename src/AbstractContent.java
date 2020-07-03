import java.util.ArrayList;

public abstract class AbstractContent extends Comment
{
	protected String contentID; 
	protected String name; 
	protected int numberOfDownloads; 
	protected double price;
	protected ArrayList<Comment> commentsList;
	protected ArrayList<User> downloadedByList;
	
	public AbstractContent(String contentID, String name, int numberOfDownloads, double price) {
		super();
		this.contentID = contentID;
		this.name = name;
		this.numberOfDownloads = numberOfDownloads;
		this.price = price;
		
		this.commentsList = new ArrayList<Comment>();
		this.downloadedByList = new ArrayList<User>();
	}
	
	
	public void addReview(Comment userComment){
		this.commentsList.add(userComment);
	}
	
	
	// Only kept so that test case doesn't fail - Single product review will be shown
	public void showReviews() {
		
		// Header
		System.out.format("%3s%13s%38s%10s\n", "---+","-------------+","--------------------------------------+","----------+");
		System.out.format("%40s\n", "S-I-N-G-A-L P-R-O-D-U-C-T R-E-V-I-E-W-S");

		// Body
		ArrayList<Comment> contentComments = this.getCommentsList();

		System.out.format("%3s%13s%38s%10s\n", "---+","-------------+","--------------------------------------+","----------+");
		System.out.format("%-3s|%-13s|%-38s|%-10s|\n", "Sr", "ID", "Name", "R.Count");
		System.out.format("%3s%13s%38s%10s\n", "---+","-------------+","--------------------------------------+","----------+");
		System.out.format("%-3s|%-13s|%-38s|%-10s|\n", 1, this.getContentID(), this.getName(), contentComments.size());
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

	
	// Shows the list of users who downlaoded the content
	public void showDownloads() {
		System.out.println("Product Name : "+this.getName()+"\n======================"); 
		for(User user : this.downloadedByList) {
			System.out.println(user.getName());
		}
	}

	
	// Overides the price of content with supplied price
	public void setPrice(boolean adminLoginStatus, double price) {
		if(adminLoginStatus) {
			double oldPrice = this.getPrice(); 
			this.price = price;
			System.out.format("%-30s%-10s%-10s", this.getName(), String.format("%.2f", oldPrice), String.format("%.2f", this.getPrice()));
			System.out.println();
		}else {
			System.err.println("Invalid Login. Can not update price for "+this.getName());
		}
	}
	
	
	// Getter Setter Starts Here
	public ArrayList<User> getDownloadedByList() {
		return downloadedByList;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Comment> getCommentsList() {
		return commentsList;
	}
	
	public String getContentID() {
		return contentID;
	}

	public void setContentID(String contentID) {
		this.contentID = contentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfDownloads() {
		return numberOfDownloads;
	}

	public void setNumberOfDownloads(int numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
	}

	public double getPrice() {
		return price;
	}
	// Getter Setters Ends Here
}
