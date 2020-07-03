import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

// Singleton class returns only one reference of scannerUtil which is being used everywhere for accepting user input.

public class ScannerUtil {

	private static ScannerUtil scannerInstance = null;
	private Scanner consoleIn = null;
	
	// Maintains the list of accepted responses for yes no type questions
	static Set<String> yesNoStrings = new HashSet<String>();

	private ScannerUtil() {
		yesNoStrings.add("y");
		yesNoStrings.add("n");
		yesNoStrings.add("yes");
		yesNoStrings.add("no");
	}

	
	/* Its there as part of standard implementation.
	 * Kept is static for code reduction 
	 */
	
	public static ScannerUtil createInstance() {
		if(scannerInstance == null) {
			scannerInstance = new ScannerUtil();
		}

		return scannerInstance;
	}


	// Initialize Scanner with system in but returns reference of ScannerUtil for method chaining 
	public static ScannerUtil consoleReader(){

		if (ScannerUtil.createInstance() != null) {		
			if(ScannerUtil.scannerInstance.consoleIn == null) {
				scannerInstance.consoleIn = new Scanner(System.in);
			}

		}

		return ScannerUtil.scannerInstance;
	}
	
	
	// Reads integer data until integer data is not supplied
	public int readInt(String inputStr) {
		
		boolean success = false;
		int readVal = 0;
		do {
			try {
				readVal = ScannerUtil.scannerInstance.consoleIn.nextInt();
				success = true;
			} catch (InputMismatchException | IllegalArgumentException ime) {
				System.out.println("Please enter numeric value.");
				System.out.print(inputStr);
			}
			
			ScannerUtil.consoleReader().clearReader();
		}while(!success);
		
		
		return readVal;
	}
	
	// Reads double data and does not accept wrong data
	public double readDouble(String inputInstr) {
		
		boolean success = false;
		double readVal = 0;
		do {
			
			try {
				 readVal = ScannerUtil.scannerInstance.consoleIn.nextDouble();
				 success = true;
			} catch (InputMismatchException ime) {
				System.out.println("Please enter numeric value. ");
				System.out.print(inputInstr);
			}
			
			ScannerUtil.consoleReader().clearReader();
			
		} while(!success);

		return readVal;
	}
	
	
	// Reads any String data but empty values are not allowed
	public String readString() {
		
		String userInput = null;
		do {
			userInput = ScannerUtil.scannerInstance.consoleIn.nextLine();
			
			if(userInput == null || userInput == "") {
				System.out.print("Empty value is not allowed\nEnter Again : ");
			}
			
		}while(userInput == null || userInput == "" || userInput.isBlank() || userInput.isEmpty());
		
		return userInput;
	}
	
	
	// Reads any String and validates against given pattern
	public String readString(String pattern, String errMsg, String inputInstr) {
	
		String userInput;
		boolean matched = false;
		do {
			userInput = ScannerUtil.scannerInstance.consoleIn.nextLine();
			matched = userInput.matches(pattern);
			if(!matched) {
				System.out.println(errMsg);
				System.out.println(inputInstr);
			}
		}while(!matched);
		
		return userInput;
	}
		
		
	// Special method of reading only yes no type answers
	public String readYesNo(String inputMessage) {
		String typedInput = "";
		boolean success = false;
		
		do {
			typedInput = ScannerUtil.scannerInstance.consoleIn.nextLine();
			
			if(typedInput.trim().equalsIgnoreCase("yes") || typedInput.trim().equalsIgnoreCase("y")) {
				success = true;
				typedInput = "Y";
			} else if (typedInput.trim().equalsIgnoreCase("no") || typedInput.trim().equalsIgnoreCase("n")){
				success = true;
				typedInput = "N";
			}else {
				System.out.println("-- Invalid Input --");
				System.out.print(inputMessage);
				success = false;
			}
			
		}while(!success);
		 
		return typedInput;
	}
	
	
	// Removes the newline character from the input stream
	public void clearReader() {
		ScannerUtil.scannerInstance.consoleIn.nextLine();
	}

	
	// Close connection after use. Now to be used with every request.
	public void closeReader() {

		if(this.consoleIn != null) {
			this.consoleIn.close();
		}
	}
}

