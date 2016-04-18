package projectManager;

public class Date {
	private int week;
	private int year;
	private System sys;
	public Date(int week, int year,System sys) {
		this.week = week;
		this.year = year;
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
			if(today.getWeek() <= week){
				return true;
			}
		}
		return false;
		
	}
	public boolean before(Date d2) {
		// TODO Auto-generated method stub
		return false;
	}

}