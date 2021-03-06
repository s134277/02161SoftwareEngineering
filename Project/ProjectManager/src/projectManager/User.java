package projectManager;
/**
 * Author: Jonas
 */
import java.util.ArrayList;
import java.util.List;

public class User {
	private String name; 
	private String PW; 
	private double weeklyWorkHours;
	private List<Activity> acts = new ArrayList<Activity>();
	private List<Double> registeredTime = new ArrayList<Double>();
	private List<Activity> holiday = new ArrayList<Activity>();
	private List<Activity> sick = new ArrayList<Activity>();
	
	public User(String name, String PW,double weeklyWorkHours) {
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

	public boolean isAvailable(Date date,double hours) {
		return weeklyWorkHours > getHours(date) + hours;
	}

	public double getRegisteredTime(Activity act) {
		int i = 0;
		for(Activity act1 : acts){
			if(act.getName().equals(act1.getName())) {
				return registeredTime.get(i);
			}
			i++;
		}
		return 0;
	}

	public double getWeeklyWorkHours() {
		return weeklyWorkHours;
	}

	public void RegisterTime(Date date, Project pro, String name, double hours) {
		Activity act = null;
		if(pro == null){
			if(name.equals("Holiday")){
				act = new Activity("Holiday","TimeOff",date,date,hours);
				holiday.add(act);
			}else if(name.equals("Sick")){
				act = new Activity("Sick","TimeOff",date,date,hours);
				sick.add(act);
			}
		}else{
			for(int i = 0; i < pro.getActivities().size(); i++){
				if(name.equals(pro.getActivities().get(i).getName())){
					act = pro.getActivities().get(i);
					break;
				}
			}
			for(int i = 0; i < acts.size(); i++){
				if(act.equals(acts.get(i))){
					registeredTime.set(i, registeredTime.get(i) + hours);
				}
			}
		}
	}

	public void addActivity(Activity activity) {
		acts.add(activity);
		registeredTime.add((double)0);
	}

	public double getHours(Date start){
		double workHours=0;
		int week = start.getWeek();
		int year = start.getYear();
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
		return workHours;
	}

	public void setName(String setUsername) {
		this.name = setUsername;
	}

	public void setPW(String setPW) {
		this.PW = setPW;
		
	}

	public void setWeeklyWorkHours(double setWeeklyHours) {
		this.weeklyWorkHours = setWeeklyHours;
		
	}
	public List<Activity> getActivities(){
		return acts;
	}
}
