package projectManager;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class testDate extends sampleDataSetup {
	@Test
	public void TestDate(){
		//opretter mock server
		DateServer dateServer = mock(DateServer.class);
		sys.setDateServer(dateServer);

		Calendar cal = new GregorianCalendar(2016,Calendar.JANUARY,5);
		when(dateServer.getDate()).thenReturn(cal);
		
		//opretter datoer
		Date d1 = new Date(3,2016,sys);
		Date d2 = new Date(4,2016,sys);
		Date d3 = new Date(7,2015,sys);
		
		//tester denne dato mod idag
		assertTrue(d1.afterToday());
		assertFalse(d3.afterToday());
		
		//tester to datoer mod hinanden
		assertTrue(d1.before(d2));
		assertTrue(d1.before(d1));
		assertFalse(d1.before(d3));
		
		
	}
}