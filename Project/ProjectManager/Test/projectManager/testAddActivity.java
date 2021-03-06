package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;
/**
 * Author: Michael
 */
public class testAddActivity{
	@Test
	public void TestAddActivity() throws Exception{
		
		//opretter system
		MAIN sys = new MAIN();
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		sys.setDateServer(new DateServer());
		User user = new User("Mich","123",37);
		User dev = new User("Jona","321",37);
		sys.register(user);
		sys.register(dev);
		
		sys.login("Mich","123");
		
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		pro.addDev(dev);
		pro.setProjectLeader(user);
		

		//checker om der er aktiviteter tilf�jet
		assertEquals(0,pro.getActivities().size());
		
		//G1 - tilf�jer en aktivitet
		pro.addActivity(act);	
		//checker om aktiviteten er blevet tif�jet til projectet
		assertEquals(1,pro.getActivities().size());
		
		//G2 - tilf�jer en developer
		act.addDev(pro.findDev("Jona"));
		//checker om dev er blevet added
		assertEquals(1,act.getUsers().size());
		
		//checker om description er added
		assertEquals("testActi",act.getDescription());
	}
	@Test
	public void TestAddActivityFail() throws Exception{
		
		//opretter system
		MAIN sys = new MAIN();
		User user = new User("Mich","123",37);
		User dev = new User("Jona","321",37);
		sys.register(user);
		sys.register(dev);
		sys.setDateServer(new DateServer());
		sys.login("Mich","123");
		
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		
		pro.setProjectLeader(user);
		
		
		pro.addActivity(act);
		//H1 - add aktivitet med samme navn
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
		User dev1 = new User("Mich","123",37);
		User dev2 = new User("Jona","321",37);
		User dev3 = new User("Dani","321",37);
		User dev4 = new User("Emil","321",37);
		sys.register(dev1);
		sys.register(dev2);
		sys.register(dev3);
		sys.register(dev4);
		sys.setDateServer(new DateServer());
		
		sys.login("Mich", "123");
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
		
		//I1 - ingen har registreret noget tid s� alle skulle have mulighed for at deltage
		List<User> devs = pro.getAvailableDev(date1,date3,50);
		assertEquals(devs.size(),4);
		
		//I2 
		//dev3 registrere 3 ugers ferie
		dev3.RegisterTime(date1,null,"Holiday",dev3.getWeeklyWorkHours());
		dev3.RegisterTime(date2,null,"Holiday",dev3.getWeeklyWorkHours());
		dev3.RegisterTime(date3,null,"Holiday",dev3.getWeeklyWorkHours());
		
		//dev4 registrere 2 uger ferie
		dev4.RegisterTime(date1,null,"Holiday",dev4.getWeeklyWorkHours());
		dev4.RegisterTime(date2,null,"Holiday",dev4.getWeeklyWorkHours());
		
		//fordi dev3 har ferie og dev 4 kun har 37 ledig skulle kun dev1 og dev2 to returneres
		devs = pro.getAvailableDev(date1,date3,50);
		assertEquals(devs.size(),2);
		
	}
	@Test
	public void testEditAct() throws Exception{
		// testing get and set for edit act
		//opretter system
				MAIN sys = new MAIN();
				Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
				sys.setDateServer(new DateServer());
				User user = new User("Mich","123",37);
				User dev = new User("Jona","321",37);
				sys.register(user);
				sys.register(dev);
				
				sys.login("Mich","123");
				
				Project pro = new Project("testName",sys);
				sys.createProject(pro);
				
				pro.addDev(dev);
				pro.setProjectLeader(user);
				
				//tilf�jer en aktivitet
				pro.addActivity(act);
				
				//tilf�jer en developer
				act.addDev(pro.findDev("Jona"));
				
				//�ndre i alle v�rdier
				String name = "newName";
				String description = "newDescription";
				Date newStartDate = new Date(9,2017,sys);
				Date newEndDate = new Date(20,2017,sys);
				int newTimeBudget = 200;
				
				act.setName(name);
				act.setDescription(description);
				act.setStartDate(newStartDate);
				act.setEndDate(newEndDate);
				act.setTimebudget(newTimeBudget);
				
				//checker om det er blevet �ndret korrekt
				assertEquals(name,act.getName());
				assertEquals(description,act.getDescription());
				assertEquals(newStartDate,act.getStartDate());
				assertEquals(newEndDate,act.getEndDate());
				assertEquals(newTimeBudget,act.getTimeBudget(),1e15);
				
				pro.deleteActivity(act);
				
				assertEquals(0,pro.getActivities().size());
	}
}
