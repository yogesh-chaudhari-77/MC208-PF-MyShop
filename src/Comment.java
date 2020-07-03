
public class Comment {
	protected Customer customerRef;
	
	protected String comment;
	protected int rating;
	
	protected static final int INITIAL_RATING = 0;
	
	public Comment()
	{
		this.customerRef = null;
		this.comment = "";
		this.rating = INITIAL_RATING;
	}
	
	
	// Constructor type 1 
	public Comment(Customer customerRef, String comment){
		this.customerRef = customerRef;
		this.comment = comment;
	}
	
	
	// Constructor type 2 
	public Comment(Customer customerRef, int rating) {
		this.customerRef = customerRef;
		this.rating = rating;
	}
	
	
	// Constructor type 3 
	public Comment(Customer customerRef, int rating, String comment) {
		this.customerRef = customerRef;
		this.rating = rating;
		this.comment = comment;
	}
	
	
	// Constructor type 4 
	public Comment(Customer customerRef, String comment, int rating) {
		this.customerRef = customerRef;
		this.rating = rating;
		this.comment = comment;
	}
	
	
	@Override
	public String toString() {
		return "Comment [customerRef=" + customerRef + ", comment=" + comment + ", rating=" + rating + "]";
	}
	
	
	// Getters Setters Starts Here
	public Customer getCustomerRef() {
		return customerRef;
	}
	
	public void setCustomerRef(Customer customerRef) {
		this.customerRef = customerRef;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	// Getter Setter Ends Here
}
