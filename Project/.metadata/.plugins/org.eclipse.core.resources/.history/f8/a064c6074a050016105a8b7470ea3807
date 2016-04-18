package projectManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;


public class testCreateUser {



	/** 
	 * Tests the scenario when the administrator successfully logs in.
	 * <ol>
	 *  <li> The administrator logs in with the correct password
	 * 	<li> The library application knows that the administrator is logged in
	 * </ol>
	 * @throws NoPasswordEnteredException 
	 * @throws UserAlreadyExistsException 
	 */
	@Test
	public void testCreateUserMichael123() throws UserAlreadyExistsException, NoPasswordEnteredException {
		System sys = new System();
		
		// Step 1)
		List<User> users = sys.getUsers();
		assertEquals(0, users.size());

		// Step 2)
		User user = new User("Michael", "123");

		sys.register(user);
		users = sys.getUsers();

		// Step 3)
		assertEquals(1, users.size());

		User registeredUser = users.get(0);
		assertEquals("Michael", registeredUser.getName());
		assertEquals("123", registeredUser.getPW());
	}
	
	@Test
	public void testUsernameTaken() throws Exception {
		System sys = new System();

		//step 1
		User user1 = new User("Michael","123");
		sys.register(user1);
		assertEquals(1, sys.getUsers().size());
		
		//step 2
		User user2 = new User("Michael","123");

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
		System sys = new System();
		
		User user = new User("Jonas","");

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
