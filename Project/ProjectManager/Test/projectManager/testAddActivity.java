package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;

public class testAddActivity{
	@Test
	public void TestAddActivity() throws Exception{
		
		//opretter system
		MAIN sys = new MAIN();
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		
		User user = new User("Michael","123",37);
		User dev = new User("Jonas","321",37);
		sys.register(user);
		sys.register(dev);
		
		sys.login("Michael","123");
		
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		pro.addDev(dev);
		pro.setProjectLeader(user);
		

		//checker om der er aktiviteter tilf�jet
		assertEquals(0,pro.getActivities().size());
		
		//tilf�jer en aktivitet
		pro.addActivity(act);
		
		//checker om aktiviteten er blevet tif�jet til projectet
		assertEquals(1,pro.getActivities().size());
		
		//tilf�jer en developer
		act.addDev(pro.findDev("Jonas"));
		
		//checker om dev er blevet added
		assertEquals(1,act.getUsers().size());
	}
	@Test
	public void TestAddActivityFail() throws Exception{
		
		//opretter system
		MAIN sys = new MAIN();
		User user = new User("Michael","123",37);
		User dev = new User("Jonas","321",37);
		sys.register(user);
		sys.register(dev);
		
		sys.login("Michael","123");
		
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		
		pro.setProjectLeader(user);
		
		
		pro.addActivity(act);
		//add aktivitet med samme navn
		try{
			pro.addActivity(act);
			fail("ActivityAlreadyExistsException should have been thrown");
		} catch (ActivityAlreadyExistsException e) {
			assertEquals("Activity Already Exists",e.getMessage());
			assertEquals("AddActivityProject",e.getOperation());
		}
	}
	@Test
	public void ListAvailableDev() throws Exception{
		//opretter system
		MAIN sys = new MAIN();
		User dev1 = new User("Michael","123",37);
		User dev2 = new User("Jonas","321",37);
		User dev3 = new User("Daniel","321",37);
		User dev4 = new User("Emil","321",37);
		sys.register(dev1);
		sys.register(dev2);
		sys.register(dev3);
		sys.register(dev4);
		
		sys.login("Michael", "123");
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		
		pro.addDev(dev2);
		pro.addDev(dev3);
		pro.addDev(dev4);
		
		pro.setProjectLeader(dev1);
		pro.addActivity(act);
		
		Date date1 = new Date(3,2016,sys);
		Date date2 = new Date(4,2016,sys);
		Date date3 = new Date(5,2016,sys);
		
		//ingen har registreret noget tid s� alle skulle have mulighed for at deltage
		List<User> devs = pro.getAvailableDev(date1,date3,50);
		assertEquals(devs.size(),4);
		
		//dev3 registrere 3 ugers ferie
		dev3.RegisterTime(date1,"Holiday",dev3.getWeeklyWorkHours());
		dev3.RegisterTime(date2,"Holiday",dev3.getWeeklyWorkHours());
		dev3.RegisterTime(date3,"Holiday",dev3.getWeeklyWorkHours());
		
		//dev4 registrere 2 uger ferie
		dev4.RegisterTime(date1,"Holiday",dev4.getWeeklyWorkHours());
		dev4.RegisterTime(date2,"Holiday",dev4.getWeeklyWorkHours());
		
		//fordi dev3 har ferie og dev 4 kun har 37 ledig skulle kun dev1 og dev2 to returneres
		devs = pro.getAvailableDev(date1,date3,50);
		assertEquals(devs.size(),2);
		
	}
}
