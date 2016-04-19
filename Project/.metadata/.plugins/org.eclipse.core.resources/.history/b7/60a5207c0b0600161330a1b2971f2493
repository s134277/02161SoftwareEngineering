package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestLogin {
	@Test
	public void testLogin() throws Exception{
		// opretter bruger
		MAIN sys = new MAIN();
		User user = new User("Michael","123");
		sys.register(user);
		
		//logger ind med bruger
		Boolean loggedIn = sys.login("Michael","123");
		assertTrue(loggedIn);
		
		// logger ud
		loggedIn = sys.logOut();
		assertFalse(loggedIn);
		
		//wrong password
		try{
			loggedIn = sys.login("Michael","321");
			fail("WrongCredentialsException exception should have been thrown");
		} catch (WrongCredentialsException e) {
			assertEquals("Wrong Password or Username",e.getMessage());
			assertEquals("Login",e.getOperation());
		}
		assertFalse(loggedIn);
	
		//wrong Username
		try{
			loggedIn = sys.login("ichael","123");
			fail("WrongCredentialsException exception should have been thrown");
		} catch (WrongCredentialsException e) {
			assertEquals("Wrong Password or Username",e.getMessage());
			assertEquals("Login",e.getOperation());
		}
		assertFalse(loggedIn);
	}
}
