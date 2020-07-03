// Global class maintains the list of constants used across the system

public class Globals {

	// Initial funds for customer if not provided
	public static final int INITIAL_FUNDS = 50;
	
	// Default price of the new content
	public static final int DEFAULT_PRICE = 0;
	
	// Default number of downloads for the new content
	public static final int DEFAULT_NUM_DOWNLOADS = 0;
	
	// Premium membership fees
	public static final int PREMIUM_MEMBERSHIP_FEES = 100;
	
	// Percentage Val - Should Always be Positive
	public static final double DISCNT_FOR_PREM_CUST = 0.20;
	
	// Admin level required for price alteration
	public static final int BULK_PRICE_ALTER_LEVEL = 5;
	
	public static final String LINE_SEPERATOR = "------------------------------------------------------------";

	// Menu Options Constants Starts Here
	public static final int _LIST_ALL_CONTENTS = 1;
	public static final int _LIST_ALL_USERS = 2;
	public static final int _DOWNLOAD_CONTENT_S = 3;
	public static final int _SHOW_SHOP_REVIEWS = 4;
		
	public static final int _UPGRADE_TO_PREMIUM = 5;
	public static final int _SHOW_ALL_COMMENTS = 6;
	
	public static final int _CREATE_BOOK = 7;
	public static final int _CREATE_MAGZINE = 8;
	public static final int _CREATE_APP = 9;
	public static final int _CREATE_CUSTOMER = 10;
	public static final int _CREATE_ADMIN = 11;
	public static final int _ADD_REVIEW = 12;
	public static final int _SHOW_PRODUCT_REVIEW = 13;

	
	public static final int _RESET_PRICE = 20;
	public static final int _BULK_PRICE_ALTERATION = 21;
	
	public static final int _ALL_DOWNLOADS_SHOP = 30;
	public static final int _ALL_DOWNLOADS_BY_USER = 31;
	public static final int _PRODUCT_DOWNLOADED_BY = 32;
	
	public static final int _CHANGE_NAME = 40;
	
	public static final int _QUIT = 50;
	
	// Menu Options Constants Ends Here

	public static final String  unixClearCommand = "clear";
	public static final String  windowsClearCommand = "cls";
}
