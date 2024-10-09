package edu.asu.DatabasePart1;

/*******
 * <p> PasswordEvaluationTestingAutomation Class </p>
 * 
 * <p> Description: A Class to run tests on the PasswordEvaluator Class </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2022 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 2.00	2023-10-2 Added Addition Test Cases and Comments 
 */


public class PasswordEvaluationTestingAutomation {
	
	/** The number of test cases that passed **/
	static int numPassed = 0;
	
	/** The number of test cases that failed **/
	static int numFailed = 0;
	
	/**********************************************************************************************
	 * This is the method that stores the Test Cases and calls the function to run the Test Cases
	 * 
	 * @param args	The standard argument list for a Java Mainline
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("____________________________________________________________________________");
		System.out.println("\nTesting Automation");
		
		// Test cases 
		performTestCase(1, "Aa!15678", true);					// Test case 1: Valid password

		performTestCase(2, "A!", false);						// Test case 2: Invalid password
		
		performTestCase(3, "Aa!15678", false);					// Test case 3: Valid password
		
		performTestCase(4, "A!", true);							// Test case 4: Invalid password
		
		performTestCase(5, "", true);							// Test case 5: Invalid password
		
		performTestCase(6, "?@#$%@#$^@#$%@#$%$!9Ab", true);		// Test case 6: Valid password
	
		performTestCase(7, "boo-0-", false);					// Test case 7: Invalid password
		
		System.out.println("____________________________________________________________________________");
		System.out.println();
		System.out.println("Number of tests passed: "+ numPassed);
		System.out.println("Number of tests failed: "+ numFailed);
	}

	/**********************************************************************************************
	 * This is the method that calls the evaluatePassword method in the PasswordEvaluator Class on the Test Case
	 * 
	 * @param testCase	The testCase number
	 * @param inputText	The String to check for a valid password
	 * @param expectedPass	The expected pass value of the string
	 * 
	 */
	private static void performTestCase(int testCase, String inputText, boolean expectedPass) {
		System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
		System.out.println("Input: \"" + inputText + "\"");
		System.out.println("______________");
		System.out.println("\nFinite state machine execution trace:");
		
		String resultText= PasswordEvaluator.evaluatePassword(inputText); // Run the Test Case in the Password Evaluator Class
		
		System.out.println();
		
		// Check if the resultText is not empty
		if (resultText != "") {
			if (expectedPass) { 	// Expected result was valid but was not supposed to
				System.out.println("***Failure*** The password <" + inputText + "> is invalid." + 
						"\nBut it was supposed to be valid, so this is a failure!\n");
				System.out.println("Error message: " + resultText);
				numFailed++;
			}
			else {					// Expected result was invalid, an error was found
				System.out.println("***Success*** The password <" + inputText + "> is invalid." + 
						"\nBut it was supposed to be invalid, so this is a pass!\n");
				System.out.println("Error message: " + resultText);
				numPassed++;
			}
		}
		
		// No error message was found
		else {	
			if (expectedPass) {		//	Expected result was valid and no error was found
				System.out.println("***Success*** The password <" + inputText + 
						"> is valid, so this is a pass!");
				numPassed++;
			}
			else {					//	Expected result was invalid but no error was found.
				System.out.println("***Failure*** The password <" + inputText + 
						"> was judged as valid" + 
						"\nBut it was supposed to be invalid, so this is a failure!");
				numFailed++;
			}
		}
		displayEvaluation();
	}
	
	/**********************************************************************************************
	 * This is the method that displays which conditions a string satisfies and does not.
	 * 
	 */
	private static void displayEvaluation() {
		// Check if the password contains at least one upper case letter
		if (PasswordEvaluator.foundUpperCase)
			System.out.println("At least one upper case letter - Satisfied");
		else
			System.out.println("At least one upper case letter - Not Satisfied");

		// Check if the password contains at least one lower case letter
		if (PasswordEvaluator.foundLowerCase)
			System.out.println("At least one lower case letter - Satisfied");
		else
			System.out.println("At least one lower case letter - Not Satisfied");
	
		// Check if the password contains at least one numeric digit
		if (PasswordEvaluator.foundNumericDigit)
			System.out.println("At least one digit - Satisfied");
		else
			System.out.println("At least one digit - Not Satisfied");

		// Check if the password contains at least one special character
		if (PasswordEvaluator.foundSpecialChar)
			System.out.println("At least one special character - Satisfied");
		else
			System.out.println("At least one special character - Not Satisfied");

		// Check if the password is at least 8 characters long
		if (PasswordEvaluator.foundLongEnough)
			System.out.println("At least 8 characters - Satisfied");
		else
			System.out.println("At least 8 characters - Not Satisfied");
	}
}
