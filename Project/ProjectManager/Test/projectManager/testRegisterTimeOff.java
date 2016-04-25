package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.Test;

public class testRegisterTimeOff{
	@Test
	public void testRegisterHoliday() throws Exception{
		//opretter systemet
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
		
		
		//Registers holiday:
		user.RegisterTime(date,"Holiday",user.getWeeklyWorkHours());
		assertFalse(user.isAvailable(date.getWeek(), date.getYear(),user.getWeeklyWorkHours()));
		
		//Asserts if the user is now available in week 5 and available in week 4 and 6:
		assertFalse(user.isAvailable(5,2016,10));
		assertTrue(user.isAvailable(6,2016,10));
		assertTrue(user.isAvailable(4,2016,10));
		
		//Registers holiday in week 6-9:
		Date date2 = new Date(6,2016,sys);
		Date date3 = new Date(7,2016,sys);
		Date date4 = new Date(8,2016,sys);
		Date date5 = new Date(9,2016,sys);
		user.RegisterTime(date2,"Holiday",user.getWeeklyWorkHours());
		assertFalse(user.isAvailable(date2.getWeek(), date2.getYear(),user.getWeeklyWorkHours()));
		user.RegisterTime(date3,"Holiday",user.getWeeklyWorkHours());
		assertFalse(user.isAvailable(date3.getWeek(), date3.getYear(),user.getWeeklyWorkHours()));
		user.RegisterTime(date4,"Holiday",user.getWeeklyWorkHours());
		assertFalse(user.isAvailable(date4.getWeek(), date4.getYear(),user.getWeeklyWorkHours()));
		user.RegisterTime(date5,"Holiday",user.getWeeklyWorkHours());
		assertFalse(user.isAvailable(date4.getWeek(), date4.getYear(),user.getWeeklyWorkHours()));
		
		//Tests if the user is now unavailable during the holiday:
		assertFalse(user.isAvailable(6,2016,10));
		assertFalse(user.isAvailable(7,2016,10));
		assertFalse(user.isAvailable(8,2016,10));
		assertFalse(user.isAvailable(9,2016,10));
		
	}
	
	@Test
	public void testRegisterHolidayConflict() throws Exception{
		//opretter systemet
		MAIN sys = new MAIN();
		User user = new User("Michael","123",37);
		sys.register(user);
		Date start = new Date(5,2016,sys);
		Date end = new Date(6,2016,sys);
		Date startDate = new Date(5,2016,sys);
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		//opretter mock server
		DateServer dateServer = mock(DateServer.class);
		sys.setDateServer(dateServer);
		int[] d = new int[]{5,2017};
		when(dateServer.getDate()).thenReturn(d);
		
		//scheduling a conflicting activity:
		Activity act = new Activity("testName","testActi",start,end,100);
		sys.login("Michael","123");
		pro.addDev(sys.findDev("Michael"));
		pro.setProjectLeader(user);
		pro.addActivity(act);
		act.addDev(pro.findDev("Michael"));

		user.RegisterTime(startDate,"Holiday",user.getWeeklyWorkHours());
		assertFalse(user.isAvailable(startDate.getWeek(), startDate.getYear(),user.getWeeklyWorkHours()));
		
		
		//tester for anden dato mock server
		d = new int[]{5,2016};
		when(dateServer.getDate()).thenReturn(d);
		assertFalse(user.isAvailable(startDate.getWeek(), startDate.getYear(),user.getWeeklyWorkHours()));
	}
	@Test
	public void testRegisterSick() throws Exception{
		//opretter system
		MAIN sys = new MAIN();
		
		User user = new User("Michael","123",37);
		User dev = new User("Jonas","321",37);
		sys.register(user);
		sys.register(dev);
		
		sys.login("Michael","123");
		Date date = new Date(5,2016,sys);
		
		
		//Registers sick: 
		user.RegisterTime(date,"Sick",user.getWeeklyWorkHours());
		assertFalse(user.isAvailable(date.getWeek(), date.getYear(),user.getWeeklyWorkHours()));
		
		
	}
}
