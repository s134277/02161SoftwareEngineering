package projectManager;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private double timeBudget;
	private List<User> users = new ArrayList<User>();
	
	public Activity(String name, String description, Date startDate, Date endDate, double timeBudget) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.timeBudget = timeBudget;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setStartDate(Date start){
		this.startDate = start;
	}
	
	public void setEndDate(Date end){
		this.endDate = end;
	}
	
	public void setTimebudget(int budget){
		this.timeBudget = budget;
	}
	
	public void addDev(User dev) {
		users.add(dev);
		dev.addActivity(this);
	}
	public List<User> getUsers() {
		return users;
	}
	public String getName() {
		return name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
	public String getDescription(){
		return description;
	}
	
	public double getRemainingHours() {
		double hours = 0;
		for(User user : users){
			hours = hours + user.getRegisteredTime(this);
		}
		return timeBudget - hours;
	}
	public double getTimeBudget() {
		return timeBudget;
	}
	

}
