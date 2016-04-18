package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;

public class testRegisterTimeOff extends sampleDataSetup{
	
	/*
	 * 1. Tests if a user can register one week of vacation (week 5 of 2016)
	 * 
	 * 2. Tests if a user can  register several weeks of vacation
	 * 
	 * 3. Tests if the system throws correct error if bad duration argument is entered
	 * 
	 * 4. Tests if the system throws correct error if registration conflicts with another activity
	 */
	
	
	@Test
	public void testRegisterHoliday(){
		User user = sys.findDev("Michael");
		Date startDate = new Date(5,2016);
		
		//Asserts if the user is available in week 4, 5 and 6:
		assertTrue(user.isAvailable(5,2016));
		assertTrue(user.isAvailable(6,2016));
		assertTrue(user.isAvailable(4,2016));
		
		
		//Registers holiday: (RegisterHoliday returns true if conflicts occur)
		//Arguments: beginning week, beginning year, number of weeks, reason
		assertFalse(user.RegisterHoliday(startDate,1,"Holiday"));
		
		
		//Asserts if the user is now unavailable in week 5 and available in week 4 and 6:
		assertFalse(user.isAvailable(5,2016));
		assertTrue(user.isAvailable(6,2016));
		assertTrue(user.isAvailable(4,2016));
		
		//Registers holiday in week 6-9:
		Date startDate2 = new Date(6,2016);
		assertFalse(user.RegisterHoliday(startDate2,4,"Holiday"));
		
		//Tests if the user is now unavailable during the holiday:
		assertFalse(user.isAvailable(6,2016));
		assertFalse(user.isAvailable(9,2016));
		
	}
	
	public void testRegisterHolidayDurationFail() throws Exception{
		User user = sys.findDev("Michael");
		Date startDate = new Date(5,2016);
		//Registers holiday with wrong duration:
		try{
			user.RegisterHoliday(startDate,0,"Holiday");
			fail("registerActivityException exception should have been thrown");
		} catch (registerActivityException e) {
			assertEquals("Wrong duration for holiday given, must be positive integer",e.getMessage());
			assertEquals("duration",e.getOperation());
		}
	}
	
	public void testRegisterHolidayConflict(){
		User user = sys.findDev("Michael");
		Date start = new Date(5,2016);
		Date end = new Date(6,2016);
		Date startDate = new Date(5,2016);
		Project pro = sys.findProject("testName");
		//scheduling a conflicting activity:
		Activity act = new Activity("testName","testActi",start,end,100);
		pro.addActivity(act);
		pro.addDev(sys.findDev("Michael"));
		act.addDev(pro.findDev("Michael"));
		
		/*
		 * register holiday returns true/false, 
		 * NOT depending on success of the registration, 
		 * but to notify if there is a conflict between a holiday 
		 * and a scheduled activity
		 */
		assertTrue(user.RegisterHoliday(startDate,0,"Holiday")); //returns true if conflict
	}
	
}
Chat afsluttet
