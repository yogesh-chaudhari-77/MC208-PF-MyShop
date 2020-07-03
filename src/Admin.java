public class Admin extends User
{
	private String password; 
	private int level;
	
	
	public Admin(String adminID, String adminName, String password, int level) {
		super(adminID, adminName);
		this.setPassword(password);
		this.setLevel(level);
	}
	
	
	public boolean login() {
		System.out.println("\n\n");
		System.out.print(this.getName()+" - Admin Password : ");
	
		String enteredPwd = ScannerUtil.createInstance().readString().trim();
		
		if(enteredPwd.contentEquals(this.getPassword())) {
			return true;
		}else {
			System.out.println("Invalid Password.");
			return false;
		}
	}
	
	
	@Override
	public String toString() {
		return "Admin [password=" + password + ", level=" + level + ", userID=" + userID + ", userName=" + name
				+ "]";
	}

	// Getters - Setters Starts Here
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	// Getters - Setters Ends Here 
}
