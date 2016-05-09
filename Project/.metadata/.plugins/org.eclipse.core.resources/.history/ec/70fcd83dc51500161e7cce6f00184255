package projectManager;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class testDate{
	@Test
	public void TestDate() throws Exception{
		// opretter bruger og project
		MAIN sys = new MAIN();
		User user = new User("Mich","123",37);
		sys.register(user);
		User dev = new User("Jona","321",37);
		sys.register(dev);
		
		//opretter mock server
		DateServer dateServer = mock(DateServer.class);
		sys.setDateServer(dateServer);
		int[] d = new int[]{2,2016};
		when(dateServer.getDate()).thenReturn(d);
		
		//opretter datoer
		Date d1 = new Date(3,2016,sys);
		Date d2 = new Date(4,2016,sys);
		Date d3 = new Date(7,2015,sys);
		Date d4 = new Date(1,2016,sys);
		Date d5 = new Date(2,2017,sys);
		
		// J1 - tester dato mod idag
		assertTrue(d1.afterToday()); //J11
		assertFalse(d4.afterToday()); //J12
		assertFalse(d3.afterToday()); //J13
		
		// J2 - tester to datoer mod hinanden
		assertTrue(d1.before(d2)); //J21
		assertTrue(d1.before(d5)); //J22
		assertFalse(d1.before(d1)); //J23
		assertFalse(d1.before(d3)); //J24
		
		
	}
}
