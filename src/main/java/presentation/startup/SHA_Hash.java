package presentation.startup;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class SHA_Hash {
	public String getSHA(String password){
			String sha256 = null;  
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
				sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();

			} catch (NoSuchAlgorithmException e) {
				e.getLocalizedMessage();
			}
			return sha256;
		}

	
}
