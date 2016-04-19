package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;

public class testRegisterTimeOff{
	
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
	public void testRegisterHoliday() throws UserAlreadyExistsException, NoPasswordEnteredException, WrongCredentialsException{
		MAIN sys = new MAIN();
		
		User user = new User("Michael","123",37);
		User dev = new User("Jonas","321",37);
		sys.register(user);
		sys.register(dev);
		
		sys.login("Michael","123");
		Date date = new Date(5,2016,sys);
		
		//Asserts if the user is available in week 4, 5 and 6:
		assertTrue(user.isAvailable(5,2016,10));
		assertTrue(user.isAvailable(6,2016,10));
		assertTrue(user.isAvailable(4,2016,10));
		
		
		//Registers holiday: (RegisterHoliday returns true if conflicts occur)
		assertFalse(user.RegisterTime(date,"Holiday",user.getWeeklyWorkHours()));
		
		
		//Asserts if the user is now unavailable in week 5 and available in week 4 and 6:
		assertFalse(user.isAvailable(5,2016,10));
		assertTrue(user.isAvailable(6,2016,10));
		assertTrue(user.isAvailable(4,2016,10));
		
		//Registers holiday in week 6-9:
		Date date2 = new Date(6,2016,sys);
		Date date3 = new Date(7,2016,sys);
		Date date4 = new Date(8,2016,sys);
		Date date5 = new Date(9,2016,sys);
		assertFalse(user.RegisterTime(date2,"Holiday",user.getWeeklyWorkHours()));
		assertFalse(user.RegisterTime(date3,"Holiday",user.getWeeklyWorkHours()));
		assertFalse(user.RegisterTime(date4,"Holiday",user.getWeeklyWorkHours()));
		assertFalse(user.RegisterTime(date5,"Holiday",user.getWeeklyWorkHours()));
		
		//Tests if the user is now unavailable during the holiday:
		assertFalse(user.isAvailable(6,2016,10));
		assertFalse(user.isAvailable(7,2016,10));
		assertFalse(user.isAvailable(8,2016,10));
		assertFalse(user.isAvailable(9,2016,10));
		
	}
	
	public void testRegisterHolidayConflict() throws NotProjectLeaderException, ActivityAlreadyExistsException{
		MAIN sys = new MAIN();
		
		User user = sys.findDev("Michael");
		Date start = new Date(5,2016,sys);
		Date end = new Date(6,2016,sys);
		Date startDate = new Date(5,2016,sys);
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
		assertTrue(user.RegisterTime(startDate,"Holiday",user.getWeeklyWorkHours())); //returns true if conflict
	}
	
}
