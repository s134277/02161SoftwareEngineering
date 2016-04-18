package projectManager;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int timeBudget;
	private List<User> users = new ArrayList<User>();
	
	public Activity(String name, String description, Date startDate, Date endDate, int timeBudget) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.timeBudget = timeBudget;
	}
	public void addDev(User dev) {
		users.add(dev);
	}
	public User findDev() {
		for(User user : users){
			if(name.equals(user.getName())) return user;
		}
		return null;
	}
	public List<User> getUsers() {
		return users;
	}
	public String getName() {
		return name;
	}

}
