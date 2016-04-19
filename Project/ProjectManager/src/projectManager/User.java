package projectManager;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name; 
	private String PW; 
	private int weeklyWorkHours;
	private List<Activity> acts = new ArrayList<Activity>();
	private List<Integer> registeredTime = new ArrayList<Integer>();
	private List<Activity> holiday = new ArrayList<Activity>();
	private List<Activity> sick = new ArrayList<Activity>();
	
	public User(String name, String PW,int weeklyWorkHours) {
		this.name = name;
		this.PW = PW;
		this.weeklyWorkHours = weeklyWorkHours;
	}

	public String getName() {
		return name;
	}

	public String getPW() {
		return PW;
	}

	public boolean isAvailable(int week, int year,int hours) {
		int workHours=0;
		for(Activity h : holiday){
			if(year >= h.getStartDate().getYear() && year <= h.getEndDate().getYear()){
				if(week >= h.getStartDate().getWeek() && week <= h.getEndDate().getWeek()){
					workHours = workHours + h.getTimeBudget();
				}
			}
		}
		for(Activity s : sick){
			if(year >= s.getStartDate().getYear() && year <= s.getEndDate().getYear()){
				if(week >= s.getStartDate().getWeek() && week <= s.getEndDate().getWeek()){
					workHours = workHours + s.getTimeBudget();
				}
			}
		}
		for(Activity act : acts){
			if(year >= act.getStartDate().getYear() && year <= act.getEndDate().getYear()){
				if(week >= act.getStartDate().getWeek() && week <= act.getEndDate().getWeek()){
					workHours = workHours + (act.getRemainingHours()/act.getEndDate().getRemainingWeeks()) / act.getUsers().size();
				}
			}
		}
		return weeklyWorkHours > workHours + hours;
	}

	public List<Activity> getActs() {
		return acts;
	}

	public int getRegisteredTime(Activity act) {
		int i = 0;
		for(Activity act1 : acts){
			if(act.getName().equals(act1.getName())) {
				return registeredTime.get(i);
			}
			i++;
		}
		return 0;
	}

	public int getWeeklyWorkHours() {
		return weeklyWorkHours;
	}

	public boolean RegisterTime(Date date, String name, int hours) {
		Activity act;
		if(name.equals("Holiday")){
			act = new Activity("Holiday","TimeOff",date,date,hours);
			holiday.add(act);
		}else{
			if(name.equals("Sick")){
				act = new Activity("Sick","TimeOff",date,date,hours);
				sick.add(act);
			}else{
				for(int i = 0; i < acts.size();i++){
					if(name.equals(acts.get(i).getName())){
						registeredTime.set(i, registeredTime.get(i) + hours);
					}
				}
				 
			}
		}
		return hours > weeklyWorkHours;
	}
}
