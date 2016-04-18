package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestCreateProject {
	@Test
	public void testCreateProject() throws UserAlreadyExistsException, NoPasswordEnteredException, WrongCredentialsException, ProjectAlreadyExistsException{
		// opretter bruger og project
		System sys = new System();
		User user = new User("Michael","123");
		sys.register(user);
		User dev = new User("Jonas","321");
		sys.register(dev);
		
				
		//logger ind med bruger
		Boolean loggedIn = sys.login("Michael","123");
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
		
		pro.setCostumer("costumer");
		pro.setStartDate(3,2016);
		pro.setEndDate(3,2017);
		
		pro.addDev(sys.findDev("Jonas"));
		
		pro.setTimeBudget(100);
		pro.setProjectLeader(pro.findDev("Jonas"));
		
		//checker om projektet er oprettet
		assertEquals(1,sys.getProjects().size());
		
		//Checker om s�g funktionen virker og navnet
		pro = sys.findProject("testName");
		assertEquals("testName",pro.getName());
		
		//Checker customer
		assertEquals("costumer",pro.getCostumer());
		
		//Checker start/end date
		assertEquals(3,pro.getStartWeek());
		assertEquals(2016,pro.getStartYear());
		assertEquals(3,pro.getEndWeek());
		assertEquals(2017,pro.getEndYear());
		
		//checker om dev er tilf�jet
		assertEquals(dev,pro.findDev("Jonas"));
		
		//Checker time budget
		assertEquals(100,pro.getTimeBudget());
		
		//checker projectleader
		assertEquals(dev,pro.getProjectLeader());
	}
	
	@Test
	public void testCreateProjectFail() throws Exception{
		//opretter system og brugere
		System sys = new System();
		User user = new User("Michael","123");
		sys.register(user);
		User dev = new User("Jonas","321");
		sys.register(dev);
				
		//logger ind med bruger
		Boolean loggedIn = sys.login("Michael","123");
		
		Project pro = new Project("sameName",sys);
		sys.createProject(pro);
		
		//checker standard kunde
		assertEquals("Softwarehuset A/S",pro.getCostumer());
		
		//oprette project med samme navn
		try{
			Project pro1 = new Project("sameName",sys);
			sys.createProject(pro1);
			fail("ProjectAlreadyExistsException should have been thrown");
		} catch (ProjectAlreadyExistsException e) {
			assertEquals("Project Already Exists",e.getMessage());
			assertEquals("CreateProject",e.getOperation());
		}
		
		//tilf�je aktiviteter n�r man ikke er projectLeader
		Date start = new Date(3,2016);
		Date end = new Date(3,2017);
		try{
			pro.addActivity(new Activity("testName","testActi",start,end,100));
			fail("NotProjectLeaderException should have been thrown");
		} catch (NotProjectLeaderException e) {
			assertEquals("You have to be project leader to use this function",e.getMessage());
			assertEquals("Add activity",e.getOperation());
		}
		
	}
}