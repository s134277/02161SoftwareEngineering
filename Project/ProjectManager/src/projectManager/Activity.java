package projectManager;

public class Activity {
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int timeBudget;
	public Activity(String name, String description, Date startDate, Date endDate, int timeBudget) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.timeBudget = timeBudget;
	}

}
