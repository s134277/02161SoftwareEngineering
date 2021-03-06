package projectManager;
/**
 * Author: Michael
 */
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class TestDateServer {
	@Test
	public void testDateServer() {
		//dags dato i uger og �r
		int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		//ser om Dateserver returnere dags dato
		int[] result = new DateServer().getDate();
		assertEquals(week,result[0]);
		assertEquals(year,result[1]);
	}
	@Test
	public void testSysGetDate() {
		//opretter system
		MAIN sys = new MAIN();
		sys.setDateServer(new DateServer());
		
		int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		//checker om systemet modtager den rigtige dato
		Date d = sys.getDate();
		assertEquals(week,d.getWeek());
		assertEquals(year,d.getYear());
	}
}
