package coinsExchange;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import junit.framework.TestCase;

public class PasswordHash extends TestCase {
	
	public void testBCryptHash() {
		String password = "erty1234";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = bCryptPasswordEncoder.encode(password);
		System.out.println(hashedPassword);
	}
	
	public void testMD5Hash() {
		String password = "erty1234";
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String hashedPassword = md5PasswordEncoder.encodePassword(password, null);
		System.out.println(hashedPassword);
	}
	
}
