package projectManager;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

/**
 * A class the defines a common setUp (e.g. a method with the @Before Annotation) containing
 * sample data.<p>
 * Test classes that want to use that data should inherit from this class.
 * @author hub
 *
 */
public class sampleDataSetup {
	
	MAIN sys = new MAIN();
	
	@Before
	public void setUp() throws Exception {
		// Create user
		MAIN sys = new MAIN();
		User user = new User("Michael","123");
		User dev = new User("Jonas","321");
		sys.register(user);
		sys.register(dev);
								
		//Loggin the user in
		Boolean loggedIn = sys.login("Michael","123");
		assertTrue(loggedIn);
		Project pro = new Project("testName",sys);
		sys.createProject(pro);
						
		pro.setCostumer("costumer");
		pro.setStartDate(3,2016);
		pro.setEndDate(3,2017);
		pro.addDev(sys.findDev("Jonas"));
		pro.setTimeBudget(100);
		pro.setProjectLeader(pro.findDev("Jonas"));
	}


}
