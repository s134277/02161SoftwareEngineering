package projectManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;

public class testAddActivity extends sampleDataSetup {
	@Test
	public void TestAddActivity(){
		
		Activity act = new Activity("testName","testActi",new Date(3,2016),new Date(3,2017),100);
		
		Project pro = sys.findProject("testName");
		pro.setProjectLeader(sys.findDev("Michael"));
		
		//checker om der er aktiviteter tilf�jet
		assertEquals(0,pro.getActivities().length);
		
		pro.addActivity(act);
		
		//checker om aktiviteten er blevet tif�jet til projectet
		assertEquals(1,pro.getActivities().length);
		
		
		//checker om der er added nogle dev
		assertEquals(0,act.getDev().length);
		
		act.addDev(pro.findDev("Jonas"));
		
		//checker om dev er blevet added
		assertEquals(1,act.getDev().length);
	}
	@Test
	public void TestAddActivityFail() throws Exception{
		Activity act = new Activity("testName","testActi",new Date(3,2016),new Date(3,2017),100);
		
		Project pro = sys.findProject("testName");
		pro.setProjectLeader(sys.findDev("Michael"));
		
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