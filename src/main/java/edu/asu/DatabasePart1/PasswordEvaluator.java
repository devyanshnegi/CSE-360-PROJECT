package edu.asu.DatabasePart1;

/*******
 * <p> PasswordEvaluator Class </p>
 * 
 * <p> Description: This class is responsible for evaluating a given password and checking if it meets the specified criteria. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2022 </p>
 * 
 * @author Lynn Robert Carter
 *  
 */

public class PasswordEvaluator {

	// Stores the error message for an invalid password
	public String passwordErrorMessage = "";
	// Stores the input password
	public String passwordInput = "";
	// Stores the index of the character causing the error
	public int passwordIndexofError = -1;
	// Flag to check if a upper case letter exists
	public boolean foundUpperCase = false;
	// Flag to check if a lower case letter exists
	public boolean foundLowerCase = false;
	// Flag to check if a number exists
	public boolean foundNumericDigit = false;
	// Flag to check if a special character exists
	public boolean foundSpecialChar = false;
	// Flag to check if the length is at least 8 characters long exists
	public boolean foundLongEnough = false;
	// Stores the input password string
	private String inputLine = "";
	// Stores the current character being evaluated
	private char currentChar;
	//	Stores the current index of the character being evaluated
	private int currentCharNdx;
	// Flag to keep the while loop running
	private boolean running;

	/**********************************************************************************************
	 * This method displays the current state of input evaluation.
	 * 
	 */
	private void displayInputState() {
		System.out.println(inputLine);
		System.out.println(inputLine.substring(0,currentCharNdx) + "?");
		System.out.println("The password size: " + inputLine.length() + "  |  The currentCharNdx: " + 
				currentCharNdx + "  |  The currentChar: \"" + currentChar + "\"");
	}

	/**********************************************************************************************
	 * This method evaluates the given password string against a set of rules and returns an error message if any rule is violated.
	 * 
	 * @param input	The password string to be evaluated
	 * 
	 * @return		An error message if any condition is not met; otherwise, an empty string
	 */
	public String evaluatePassword(String input) {
		
		// Initialize error message and input variables
		passwordErrorMessage = "";
		passwordIndexofError = 0;
		inputLine = input;
		currentCharNdx = 0;
	
		// Check if the input password is empty
		if(input.length() <= 0) return "*** Error *** The password is empty!";
		
		currentChar = input.charAt(0);		// The current character from the above indexed position
		passwordInput = input;
		
		// Reset the password evaluation flags
		foundUpperCase = false;
		foundLowerCase = false;	
		foundNumericDigit = false;
		foundSpecialChar = false;
		foundNumericDigit = false;
		foundLongEnough = false;
		running = true;

		// Start evaluating each character in the password string until end of string is reached
		while (running) {
			displayInputState(); // Display current input state
			
			// Check if the current character is an upper case letter
			if (currentChar >= 'A' && currentChar <= 'Z') {
				System.out.println("Upper case letter found");
				foundUpperCase = true;
			} 
			// Check if the current character is an lower case letter
			else if (currentChar >= 'a' && currentChar <= 'z') {
				System.out.println("Lower case letter found");
				foundLowerCase = true;
			} 
			// Check if the current character is a number
			else if (currentChar >= '0' && currentChar <= '9') {
				System.out.println("Digit found");
				foundNumericDigit = true;
			}
			// Check if the current character is a special character
			else if ("~`!@#$%^&*()_-+={}[]|\\:;\"'<>,.?/".indexOf(currentChar) >= 0) {
				System.out.println("Special character found");
				foundSpecialChar = true;
			}
			// Check if the character is invalid
			else {
				passwordIndexofError = currentCharNdx;
				return "*** Error *** An invalid character has been found!";
			}
			// Check if the string is less the 8 characters
			if (currentCharNdx >= 7) {
				System.out.println("At least 8 characters found");
				foundLongEnough = true;
			}
			currentCharNdx++; // Increment to the next character
			if (currentCharNdx >= inputLine.length()) // Stop the loop if the end of the string is reached
				running = false;
			else
				currentChar = input.charAt(currentCharNdx); // Get the next character
			
			System.out.println();
		}
		
		// Construct an error message based on which criteria were not met
		String errMessage = "";
		if (!foundUpperCase)
			errMessage += "Upper case; ";
		
		if (!foundLowerCase)
			errMessage += "Lower case; ";
		
		if (!foundNumericDigit)
			errMessage += "Numeric digits; ";
			
		if (!foundSpecialChar)
			errMessage += "Special character; ";
			
		if (!foundLongEnough)
			errMessage += "Long Enough; ";
		
		// If all conditions are satisfied, return an empty string
		if (errMessage == "")
			return "";
		
		// If any conditions are not met, return the error message
		passwordIndexofError = currentCharNdx;
		return errMessage + "conditions were not satisfied";

	}
}
