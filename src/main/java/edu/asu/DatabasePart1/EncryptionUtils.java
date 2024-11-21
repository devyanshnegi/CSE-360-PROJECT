package edu.asu.DatabasePart1;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/*******
 * <p> EncryptionUtils Class </p>
 * 
 * <p> Description: This utility class provides helper methods for converting between 
 *                  byte arrays and character arrays, generating initialization vectors 
 *                  (IVs), and printing character arrays. These methods are designed 
 *                  to support cryptographic operations in a secure and efficient manner. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @author Your Name
 * 
 * @version 1.00   2024-11-19 Initial implementation of the EncryptionUtils class 
 *                              for cryptographic utility methods.
 */
public class EncryptionUtils {

    /** Size of the initialization vector (IV) in bytes */
    private static final int IV_SIZE = 16;

    /*******
     * toCharArray method
     * 
     * <p> Converts a byte array to a character array using the system's default 
     *     character set. </p>
     * 
     * @param bytes The byte array to be converted.
     * @return A character array representing the decoded bytes.
     */
    public static char[] toCharArray(byte[] bytes) {
        // Decode the byte array into a CharBuffer and convert it to a char array
        CharBuffer charBuffer = Charset.defaultCharset().decode(ByteBuffer.wrap(bytes));
        return Arrays.copyOf(charBuffer.array(), charBuffer.limit());
    }

    /*******
     * toByteArray method
     * 
     * <p> Converts a character array to a byte array using the system's default 
     *     character set. </p>
     * 
     * @param chars The character array to be converted.
     * @return A byte array representing the encoded characters.
     */
    static byte[] toByteArray(char[] chars) {
        // Encode the character array into a ByteBuffer and convert it to a byte array
        ByteBuffer byteBuffer = Charset.defaultCharset().encode(CharBuffer.wrap(chars));
        return Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
    }

    /*******
     * getInitializationVector method
     * 
     * <p> Generates an initialization vector (IV) of fixed size by cycling through 
     *     the input text. If the input text is shorter than the IV size, characters 
     *     are reused cyclically. </p>
     * 
     * @param text The input character array to generate the IV.
     * @return A byte array of size IV_SIZE representing the initialization vector.
     */
    public static byte[] getInitializationVector(char[] text) {
        char[] iv = new char[IV_SIZE];

        int textPointer = 0;
        int ivPointer = 0;

        // Fill the IV by cycling through the input text
        while (ivPointer < IV_SIZE) {
            iv[ivPointer++] = text[textPointer++ % text.length];
        }

        // Convert the IV character array to a byte array
        return toByteArray(iv);
    }

    /*******
     * printCharArray method
     * 
     * <p> Prints a character array to the standard output without additional formatting. </p>
     * 
     * @param chars The character array to be printed.
     */
    public static void printCharArray(char[] chars) {
        for (char c : chars) {
            System.out.print(c);
        }
    }
}
