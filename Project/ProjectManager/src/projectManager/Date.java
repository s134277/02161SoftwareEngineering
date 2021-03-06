package projectManager;
/**
 * Author: Jonas
 */
public class Date {
	private int week;
	private int year;
	private MAIN sys;
	public Date(int week, int year,MAIN sys) {
		this.week = week;
		this.year = year;
		this.sys = sys;
	}
	public int getWeek() {
		return week;
	}
	public int getYear() {
		return year;
	}
	public boolean afterToday() {
		Date today = sys.getDate();
		if(today.getYear() <= year){
			return today.getWeek() <= week;
		}else{
			return false;
		}
		
	}
	public boolean before(Date d2) {
		if(d2.getYear() == year){
			return d2.getWeek() > week;
		}else{
			return d2.getYear() > year;
		}
		
		
	}
	public int getRemainingWeeks() {
		Date today = sys.getDate();
		if(today.getYear() == year){
			return week - today.getWeek();
		}else{
			return today.getWeek() + week;
		}
		
	}
}
