package projectManager;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class TestDateServer {
	@Test
	public void testDateServer() {
		int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		int[] result = new DateServer().getDate();
		assertEquals(week,result[0]);
		assertEquals(year,result[1]);
	}
	@Test
	public void testLibAppGetDate() {
		MAIN sys = new MAIN();
		sys.setDateServer(new DateServer());
		
		int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Date d = sys.getDate();
		assertEquals(week,d.getWeek());
		assertEquals(year,d.getYear());
	}
}
