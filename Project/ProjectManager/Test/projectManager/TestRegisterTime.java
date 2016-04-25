package projectManager;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestRegisterTime {
	@Test
	public void testRegisterTime() throws Exception{
		// opretter systemet
		MAIN sys = new MAIN();
		Activity act = new Activity("testName","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		Activity act2 = new Activity("test2Name","testActi",new Date(3,2016,sys),new Date(3,2017,sys),100);
		
		User user = new User("Michael","123",37);
		User dev = new User("Jonas","321",37);
		sys.register(user);
		sys.register(dev);
		sys.login("Michael","123");
		
		
		//tjekker om brugeren har registreret noget tid
		assertEquals(dev.getRegisteredTime(act),0);
		
		
		//opretter project og tilf�jer aktiviteter
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		pro.addDev(user);
		pro.addDev(dev);
		pro.setProjectLeader(user);
		pro.addActivity(act);
		pro.addActivity(act2);
		act.addDev(dev);
		act2.addDev(dev);
		
		// registrere tid
		dev.RegisterTime(new Date(10,2016,sys), "testName", 10);
		dev.RegisterTime(new Date(10,2016,sys), "test2Name", 10);
		
		//checker om projectet har registret tiden
		assertEquals(act.getRemainingHours(),90);
		assertEquals(act2.getRemainingHours(),90);
		
		//ser om brugeren har registreret tiden
		assertEquals(dev.getRegisteredTime(act),10);
		assertEquals(dev.getRegisteredTime(act2),10);
		
	}
}
