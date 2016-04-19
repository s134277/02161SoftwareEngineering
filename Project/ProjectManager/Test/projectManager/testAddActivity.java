package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;

public class testAddActivity{
	@Test
	public void TestAddActivity() throws NotProjectLeaderException, ActivityAlreadyExistsException, UserAlreadyExistsException, NoPasswordEnteredException, WrongCredentialsException, ProjectAlreadyExistsException{
		MAIN sys = new MAIN();
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		
		User user = new User("Michael","123",37);
		User dev = new User("Jonas","321",37);
		sys.register(user);
		sys.register(dev);
		
		sys.login("Michael","123");
		
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		pro.addDev(user);
		pro.addDev(dev);
		pro.setProjectLeader(user);
		
		//checker om der er aktiviteter tilf�jet
		assertEquals(0,pro.getActivities().size());
		
		pro.addActivity(act);
		
		//checker om aktiviteten er blevet tif�jet til projectet
		assertEquals(1,pro.getActivities().size());
		
		
		act.addDev(pro.findDev("Jonas"));
		
		//checker om dev er blevet added
		assertEquals(1,act.getUsers().size());
	}
	@Test
	public void TestAddActivityFail() throws Exception{
		MAIN sys = new MAIN();
		User user = new User("Michael","123",37);
		User dev = new User("Jonas","321",37);
		sys.register(user);
		sys.register(dev);
		
		sys.login("Michael","123");
		
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		
		pro.addDev(user);
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
}
