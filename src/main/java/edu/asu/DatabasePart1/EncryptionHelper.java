package edu.asu.DatabasePart1;

//Java security and cryptography imports
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//Importing Bouncy Castle provider for cryptographic operations
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/*******
* <p> EncryptionHelper Class </p>
* 
* <p> Description: This class provides encryption and decryption functionality using AES 
*                  with CBC (Cipher Block Chaining) mode and PKCS5 padding. It utilizes 
*                  the Bouncy Castle cryptographic provider for enhanced security features. 
*                  The class includes methods to securely encrypt and decrypt data given a 
*                  plaintext or ciphertext and an initialization vector. </p>
* 
* <p> Copyright: Arizona State University © 2024 </p>
* 
* @author Your Name
* 
* @version 1.00   2024-11-19 Initial implementation of the EncryptionHelper class 
*                              for secure data encryption and decryption.
*/
public class EncryptionHelper {

 /** Identifier for the Bouncy Castle provider */
 private static final String BOUNCY_CASTLE_PROVIDER_IDENTIFIER = "BC";    

 /** Cipher instance for performing encryption and decryption operations */
 private Cipher cipher;

 /** Key bytes for AES encryption */
 private final byte[] keyBytes = new byte[] {
         0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
         0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
         0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 
 };

 /** Secret key used for AES encryption */
 private final SecretKey key = new SecretKeySpec(keyBytes, "AES");

 /*******
  * Constructor for EncryptionHelper
  * 
  * <p> Initializes the encryption helper by registering the Bouncy Castle provider 
  *     and creating a Cipher instance configured for AES encryption using CBC 
  *     mode and PKCS5 padding. </p>
  * 
  * @throws Exception If there is an issue initializing the Cipher instance.
  */
 public EncryptionHelper() throws Exception {
     // Add the Bouncy Castle provider for enhanced security algorithms
     Security.addProvider(new BouncyCastleProvider());
     
     // Initialize the Cipher instance with the specified transformation and provider
     cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", BOUNCY_CASTLE_PROVIDER_IDENTIFIER);
 }
 
 /*******
  * encrypt method
  * 
  * <p> Encrypts the given plaintext using the AES algorithm in CBC mode with 
  *     the provided initialization vector (IV). </p>
  * 
  * @param plainText The plaintext data to encrypt.
  * @param initializationVector A 16-byte array used as the IV for encryption.
  * @return A byte array containing the encrypted data (ciphertext).
  * @throws Exception If an error occurs during the encryption process.
  */
 public byte[] encrypt(byte[] plainText, byte[] initializationVector) throws Exception {
     // Initialize the cipher in encryption mode with the secret key and IV
     cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(initializationVector));
     
     // Perform the encryption and return the resulting ciphertext
     return cipher.doFinal(plainText);
 }
 
 /*******
  * decrypt method
  * 
  * <p> Decrypts the given ciphertext using the AES algorithm in CBC mode with 
  *     the provided initialization vector (IV). </p>
  * 
  * @param cipherText The ciphertext data to decrypt.
  * @param initializationVector A 16-byte array used as the IV for decryption.
  * @return A byte array containing the decrypted data (plaintext).
  * @throws Exception If an error occurs during the decryption process.
  */
 public byte[] decrypt(byte[] cipherText, byte[] initializationVector) throws Exception {
     // Initialize the cipher in decryption mode with the secret key and IV
     cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(initializationVector));
     
     // Perform the decryption and return the resulting plaintext
     return cipher.doFinal(cipherText);
 }
}
