package projectManager;
/**
 * Author: Michael
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateServer {
		public int[] getDate() {
			int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
			int year = Calendar.getInstance().get(Calendar.YEAR);
			return new int[]{week,year};
		}
}
