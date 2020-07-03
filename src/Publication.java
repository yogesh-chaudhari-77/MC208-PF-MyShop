public class Publication extends AbstractContent {
	
	protected String publisher;
	protected int numberOfPages;
	
	public Publication(String contentID, String name, int numberOfDownloads, double price, String publisher, int numberOfPages) {
		
		// Initialising content class variables
		super(contentID, name, numberOfDownloads, price);
		
		// Initialising Publication Variables
		this.publisher = publisher;
		this.numberOfPages = numberOfPages;
	}

}