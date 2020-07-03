import java.util.Arrays;

public class Book extends Publication {

	private String[] authors;
	
	public Book(String bookID, String bookName, double bookPrice, String publisher, int numberOfPages, String [] authors) {
		
		super(bookID, bookName,  0,  bookPrice,  publisher,  numberOfPages);
		this.authors = authors;
	}
	
	
	@Override
	public String toString() {

		return "Book [bookAuthors=" + Arrays.toString(authors) + ", publisher=" + publisher + ", numberOfPages="
				+ numberOfPages + "]"+ "AbstractContent [contentID=" + contentID + ", name=" + name + ", numberOfDownloads=" + numberOfDownloads
				+ ", price=" + price + "]";
	}
	
	

}
