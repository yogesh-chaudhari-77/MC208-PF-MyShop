
public class Magazine extends Publication {

	int volume;
	

	public Magazine(String magzineID, String magzineName, double magzinePrice, String publisher, int numberOfPages, int volume) {
		
		super( magzineID, magzineName, 0, magzinePrice, publisher, numberOfPages);
		this.volume = volume;
	}

	
	// Getters Setters starts here
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	// Getters Setters ends here

	@Override
	public String toString() {
		return "Magazine [volume=" + volume + ", publisher=" + publisher + ", numberOfPages=" + numberOfPages + "]"+ "AbstractContent [contentID=" + contentID + ", name=" + name + ", numberOfDownloads=" + numberOfDownloads
				+ ", price=" + price + "]";
	}
	
	
}
