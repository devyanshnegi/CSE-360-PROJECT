package edu.asu.DatabasePart1;

/*******
 * <p> UserNameRecognizer Class </p>
 * 
 * <p> Description: The UserNameRecognizer class demonstrates the mechanical translation of a 
 *                  Finite State Machine (FSM) diagram into an executable Java program. It validates 
 *                  usernames based on specific criteria, including allowed characters and length, 
 *                  using an FSM approach. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2024 </p>
 * 
 * @version 1.00   2024-09-13 Initial baseline derived from the Even Recognizer.
 * @version 1.01   2024-09-17 Correction to address UNChar coding error, improper error 
 *                  message, and improve internal documentation.
 */

public class UserNameRecognizer {

    /**********************************************************************************************
     * 
     * Result attributes to be used for GUI applications where a detailed error message and a 
     * pointer to the character of the error will enhance the user experience.
     * 
     */

    /** Error message describing the issue with the username */
    public String userNameRecognizerErrorMessage = "";

    /** The input string being processed */
    public String userNameRecognizerInput = "";

    /** The index where the error occurred */
    public int userNameRecognizerIndexofError = -1;

    /** The current state of the FSM */
    private int state = 0;

    /** The next state of the FSM */
    private int nextState = 0;

    /** Indicates if the current state is a final state */
    private boolean finalState = false;

    /** The input line to be processed */
    private String inputLine = "";

    /** The current character being analyzed */
    private char currentChar;

    /** The index of the current character in the input line */
    private int currentCharNdx;

    /** Indicates whether the FSM is still running */
    private boolean running;

    /** The length of the username being validated */
    private int userNameSize = 0;

    /**********
     * displayDebuggingInfo method
     * 
     * <p> Displays debugging information about the current state of the FSM, including the 
     *     current state, whether it is a final state, the current character, next state, and 
     *     the size of the username being validated. </p>
     */
    private void displayDebuggingInfo() {
        if (currentCharNdx >= inputLine.length()) {
            System.out.println(((state > 99) ? " " : (state > 9) ? "  " : "   ") + state +
                    ((finalState) ? "       F   " : "           ") + "None");
        } else {
            System.out.println(((state > 99) ? " " : (state > 9) ? "  " : "   ") + state +
                ((finalState) ? "       F   " : "           ") + "  " + currentChar + " " +
                ((nextState > 99) ? "" : (nextState > 9) || (nextState == -1) ? "   " : "    ") +
                nextState + "     " + userNameSize);
        }
    }

    /**********
     * moveToNextCharacter method
     * 
     * <p> Advances the FSM to the next character in the input string, if available. If there 
     *     are no more characters, sets the FSM to stop running. </p>
     */
    private void moveToNextCharacter() {
        currentCharNdx++;
        if (currentCharNdx < inputLine.length()) {
            currentChar = inputLine.charAt(currentCharNdx);
        } else {
            currentChar = ' ';
            running = false;
        }
    }

    /**********
     * checkForValidUserName method
     * 
     * <p> Validates the given username using a Finite State Machine approach. The FSM checks 
     *     if the username meets the criteria of starting with a letter, followed by letters, 
     *     numbers, or certain special characters, and if it is within the allowed length. </p>
     * 
     * @param input The username string to validate.
     * @return      An error message if the username is invalid, or an empty string if valid.
     */
    public String checkForValidUserName(String input) {
        // Check if the input is empty
        if (input.length() <= 0) {
            userNameRecognizerIndexofError = 0; // Error at first character
            return "\n*** ERROR *** The input is empty";
        }

        // Initialize FSM variables
        state = 0;
        inputLine = input;
        currentCharNdx = 0;
        currentChar = input.charAt(0);
        userNameRecognizerInput = input;
        running = true;
        nextState = -1;
        userNameSize = 0;
        System.out.println("\nCurrent Final Input  Next  Date\nState   State Char  State  Size");

        // Start the FSM loop
        while (running) {
            // Determine the next state based on the current state and character
            switch (state) {
                case 0:
                    // State 0: Check if the first character is a letter
                    if ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z')) {
                        nextState = 1;
                        userNameSize++;
                    } else {
                        running = false;
                    }
                    break;

                case 1:
                    // State 1: Check for letters, numbers, or certain special characters
                    if ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z') ||
                        (currentChar >= '0' && currentChar <= '9')) {
                        nextState = 1;
                        userNameSize++;
                    } else if (currentChar == '.' || currentChar == '-' || currentChar == '_') {
                        nextState = 2;
                        userNameSize++;
                    } else {
                        running = false;
                    }
                    if (userNameSize > 16) {
                        running = false;
                    }
                    break;

                case 2:
                    // State 2: Check for a letter or number after a special character
                    if ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z') ||
                        (currentChar >= '0' && currentChar <= '9')) {
                        nextState = 1;
                        userNameSize++;
                    } else {
                        running = false;
                    }
                    if (userNameSize > 16) {
                        running = false;
                    }
                    break;
            }

            if (running) {
                displayDebuggingInfo();
                moveToNextCharacter();
                state = nextState;
                finalState = (state == 1);
                nextState = -1;
            }
        }
        displayDebuggingInfo();
        System.out.println("The loop has ended.");

        // Determine if the username is valid based on the final state
        userNameRecognizerIndexofError = currentCharNdx;
        userNameRecognizerErrorMessage = "";

        switch (state) {
            case 0:
                userNameRecognizerErrorMessage += "A UserName must start with A-Z, a-z.\n";
                return userNameRecognizerErrorMessage;

            case 1:
                if (userNameSize < 4) {
                    userNameRecognizerErrorMessage += "A UserName must have at least 4 characters.\n";
                    return userNameRecognizerErrorMessage;
                } else if (userNameSize > 16) {
                    userNameRecognizerErrorMessage += "A UserName must have no more than 16 characters.\n";
                    return userNameRecognizerErrorMessage;
                } else if (currentCharNdx < input.length()) {
                    userNameRecognizerErrorMessage += "A UserName character may only contain A-Z, a-z, 0-9.\n";
                    return userNameRecognizerErrorMessage;
                } else {
                    userNameRecognizerIndexofError = -1;
                    userNameRecognizerErrorMessage = "";
                    return userNameRecognizerErrorMessage;
                }

            case 2:
                userNameRecognizerErrorMessage += "A UserName character after a period must be A-Z, a-z, 0-9.\n";
                return userNameRecognizerErrorMessage;

            default:
                return "";
        }
    }
}
