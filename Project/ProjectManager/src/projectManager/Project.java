package projectManager;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private MAIN sys;
	private String name;
	private String costumer = "Softwarehuset A/S";
	private Date startDate;
	private Date endDate;
	private List<User> users = new ArrayList<User>();
	private List<Activity> acts = new ArrayList<Activity>();
	private int timeBudget;
	private User projectLeader;
	
	public Project(String name, MAIN sys) {
		this.name = name;
		this.sys = sys;
	}
	public void setCostumer(String costumer) {
		this.costumer = costumer;
	}
	public void setStartDate(int week, int year) {
		startDate = new Date(week,year,sys);	
	}
	public void setEndDate(int week, int year) {
		endDate = new Date(week,year,sys);
		
	}
	public void addDev(User user) {
		users.add(user);
	}
	public void setTimeBudget(int timeBudget) {
		this.timeBudget = timeBudget;
	}
	public User findDev(String name) throws UserNotFoundException{
		for(User user : users){
			if(name.equals(user.getName())) return user;
		}
		throw new UserNotFoundException("No user found","Find developer");
	}
	public void setProjectLeader(User dev) {
		projectLeader = dev;
	}
	public String getName() {
		return name;
	}
	public int getTimeBudget() {
		return timeBudget;
	}
	public User getProjectLeader() {
		return projectLeader;
	}
	public String getCostumer() {
		return costumer;
	}
	public void addActivity(Activity activity) throws NotProjectLeaderException, ActivityAlreadyExistsException {
		if(sys.getCurrentUser().equals(projectLeader)){
			for(Activity act : acts){
				if(act.getName().equals(activity.getName())){
					throw new ActivityAlreadyExistsException("Activity Already Exists","AddActivityProject");
				}
			}
			acts.add(activity);
		}else{
			throw new NotProjectLeaderException("You have to be project leader to use this function","Add activity");
		}
		
	}
	public List<Activity> getActivities() {
		return acts;
	}
	public Date getStartDate(){
		return startDate;
	}
	public Date getEndDate(){
		return endDate;
	}
}
