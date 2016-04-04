import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestCreateProject {
	@Test
	public void testCreateProject() throws Exception{
		// opretter bruger
		System sys = new System();
		User user = sys.newUser("Michael","123");
		User dev = sys.newUser("Jonas","321");
				
		//logger ind med bruger
		Boolean loggedIn = sys.login("Michael","123");
		
		Project pro = sys.createProject("testName");
		
		pro.setCostumer("customer");
		pro.setStartDate(3,2016);
		pro.setEndDate(3,2017);
		
		pro.addDev(sys.findDev("Jonas"));
		
	}
}