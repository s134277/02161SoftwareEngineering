import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;

public class testRegisterTimeOff {
	
	/*
	 * 1. Tests if a user can register one week of vacation (week 5 of 2016)
	 * 
	 * 2. Tests if the system throws correct error if bad duration argument is entered
	 * 
	 * 3. Tests if the system throws correct error if registration conflicts with another activity
	 */
	
	
	@Test
	public void testRegisterHoliday() extends sampleDataSetup{
		// Create user
		System sys = new System();
		User user = sys.newUser("Michael","123");
		
		//Loggin the user in
		Boolean loggedIn = sys.login("Michael","123");
		assertTrue(loggedIn);
		
		//Registers holiday:
		//Arguments: beginning week, beginning year, number of weeks, reason
		user.RegisterHoliday(5,2016,1,"Holiday");
		
		//Asserts if the user is now unavailable in week 5:
		assertFalse(user.isAvailable(5,2016));
		
	}
	
	public void testRegisterHolidayDurationFail() throws Exception{
		// Create user
		System sys = new System();
		User user = sys.newUser("Michael","123");
				
		//Loggin the user in
		Boolean loggedIn = sys.login("Michael","123");
		assertTrue(loggedIn);
	
		
		//Registers holiday with wrong duration:
		try{
			user.RegisterHoliday(5,2016,0,"Holiday");
			fail("registerActivityException exception should have been thrown");
		} catch (registerActivityException e) {
			assertEquals("Wrong duration for holiday given, must be positive integer",e.getMessage());
			assertEquals("duration",e.getOperation());
		}
	}
	
	public void testRegisterHolidayConflictFail() throws Exception{
		// Create user
		System sys = new System();
		User user = sys.newUser("Michael","123");
		User dev = sys.newUser("Jonas","321");
				
		//Loggin the user in
		Boolean loggedIn = sys.login("Michael","123");
		assertTrue(loggedIn);
		Project pro = sys.createProject("testName");
				
		pro.setCostumer("costumer");
		pro.setStartDate(3,2016);
		pro.setEndDate(3,2017);
		pro.addDev(sys.findDev("Jonas"));
		pro.setTimeBudget(100);
		pro.setProjectLeader(pro.getDev("Jonas"));
		
		
		user.RegisterActivity(5,2016,);
		
		
		//Registers holiday with wrong duration:
		try{
			user.RegisterHoliday(5,2016,1,"Holiday");
			fail("registerActivityException exception should have been thrown");
		} catch (registerActivityException e) {
			assertEquals("Wrong duration for holiday given, must be positive integer",e.getMessage());
			assertEquals("duration",e.getOperation());
		}
	}
	
}
