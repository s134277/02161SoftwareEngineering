package Interface;

import java.util.ArrayList;
import java.util.List;

import projectManager.Activity;
import projectManager.Project;
import projectManager.User;

public class reportManager {

	public void generateReport(Project pro){

		String[] title = new String[3];
		title[0] = "Weekly status report for project: " + pro.getName();
		title[1] = "week: ";
		title[2] = "Basic info:";
		String[] head = generateHeader(pro);
		String[] body = generateBody(pro);

		for(String tit : title){
			System.out.println(tit);
		}
		for(String hea : head){
			System.out.println(hea);
		}
		for(String bod : body){
			System.out.println(bod);
		}
	}
	
	public String[] generateHeader(Project pro) {
		//Report head contains basic info about the project.
		
		String head[] = new String[5];
		
		if(pro.getProjectLeader()==null) head[0] = "Project leader: none";
		else head[0] = "Project leader: " + pro.getProjectLeader().getName();
		
		if(pro.getCostumer()==null) head[1] = "Customer: Software Huset A/S";
		else head[1] = "Customer: " + pro.getCostumer();
		
		if(pro.getStartDate()==null) head[2] = "Start date: none yet";
		else head[2] = "Start date: week " + pro.getStartDate().getWeek() + " year " + pro.getStartDate().getYear();
		
		
		if(pro.getEndDate()==null) head[3] = "Deadline: No deadline yet";
		else head[3] = "Deadline: week " + pro.getEndDate().getWeek() + " year " + pro.getEndDate().getYear();
		
		if(pro.getTimeBudget()==0) head[4] = "Time budget: No time budget yet";
		else head[4] = "Time budget: " + pro.getTimeBudget();
		
		return head;
	}

	private String[] generateBody(Project pro) {
		// Report body contains info about the activities, 
		// associated developers and time spent versus time left 
		// and comparisons to the deadlines
		List<String> body = new ArrayList<String>();
		List<Activity> Activities = pro.getActivities();
		
		int index = 1;
		for(Activity act : Activities){
			body.add(index + ". " + act.getName() + " : " + act.getRemainingHours() + "/" + act.getTimeBudget());
			index++;
			if(act.getUsers().isEmpty()) body.add("   - No developers added");
			else{
				List<User> Developers = act.getUsers();
				for(User dev : Developers){
					body.add("   - " + dev.getName() + " : " + dev.getRegisteredTime(act) + "/" + act.getTimeBudget());
				}
			}
		}
		
		String[] StringBody = body.toArray(new String[body.size()]);
		
		return StringBody;
	}
}
