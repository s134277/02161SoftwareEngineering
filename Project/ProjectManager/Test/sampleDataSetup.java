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
public class SampleDataSetup {
	
	protected System sys = new System();
	
	@Before
	public void setUp() throws Exception {
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
	}


}
