package projectManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;


public class testCreateUser {
	@Test
	public void testCreateUserMichael123() throws Exception {
		MAIN sys = new MAIN();
		
		// Step 1) ingen brugere i systemet
		List<User> users = sys.getUsers();
		assertEquals(0, users.size());

		// Step 2) opretter bruger i systemet
		User user = new User("Michael", "123",37);
		sys.register(user);
		

		// Step 3) Checker om brugeren er oprettet i systemet
		users = sys.getUsers();
		assertEquals(1, users.size());
		
		User registeredUser = users.get(0);
		assertEquals("Michael", registeredUser.getName());
		assertEquals("123", registeredUser.getPW());
	}
	
	@Test
	public void testUsernameTaken() throws Exception {
		MAIN sys = new MAIN();

		//step 1 
		User user1 = new User("Michael","123",37);
		sys.register(user1);
		assertEquals(1, sys.getUsers().size());
		
		//step 2
		User user2 = new User("Michael","123",37);

		try {
			sys.register(user2);
			fail("An UserAlreadyExistsException should have been thrown");
		} catch (UserAlreadyExistsException e) {
			
			// Step 3
			assertEquals("Register user operation not allowed if username already taken.", e
					.getMessage());
			assertEquals(1, sys.getUsers().size());
		}
	}
	
	@Test
	public void testNoPasswordEntered() throws Exception {
		MAIN sys = new MAIN();
		
		User user = new User("Jonas","",37);

		try {
			sys.register(user);
			fail("An NoPasswordEnteredException should have been thrown");
		} catch (NoPasswordEnteredException e) {
			
			// Step 3
			assertEquals("Register user operation not allowed if no password entered.", e
					.getMessage());
		}
	}
	
	
}
