
public class User {
	protected String userID;
	protected String name;
	protected String phoneNumber;
	
	public User(String userID, String userName, String phoneNumber){
		
		this.userID = userID;
		this.name = userName;
		this.phoneNumber = phoneNumber;
	}
	

	public User(String userID, String userName){
		
		this.userID = userID;
		this.name = userName;
	}
	
	
	public void changeName(String userName) {
		this.setName(userName);
	}

	
	// Getters - Setters Starts Here 
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	// Getters - Setters Ends Here
	
}
