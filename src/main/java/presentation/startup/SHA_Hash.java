package presentation.startup;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is used to generate an hash for any input string. Utilized mainly
 * for encrpyting passwords using SHA-256 message digest
 * 
 * @author Deeksha Sareen
 *
 */
public class SHA_Hash {

	public String getSHA(String password) {
		String p = "";
		MessageDigest digest = null; /* creates a MessageDigest object to generate hash value */
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] h = digest.digest(password.getBytes(StandardCharsets.UTF_8)); /* converts the text into hash value */
		StringBuilder sb = new StringBuilder(
				new BigInteger(1, h).toString(16)); /*
													 * converts the hash value array into a StringBuilder instance, to
													 * make it convertible to String object
													 */
		p = sb.toString(); /*
							 * converts the value of StringBuilder class into a String object and stores it
							 * in the tmp variable
							 */

		return p;

	}
}
