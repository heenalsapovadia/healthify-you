package presentation.startup;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA_Hash {
	public String getSHA(String password){
			String sha256 = null;  
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
				BigInteger num = new BigInteger(1, digest);
			    String hashtext = num.toString(16);
	            while (hashtext.length() < 32) {
	                sha256 = "0" + hashtext;
	            }
	           
			} catch (NoSuchAlgorithmException e) {
				e.getLocalizedMessage();
			}
			return sha256;
		}

	
}
