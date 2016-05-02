package Interface;

import java.util.List;

import projectManager.DateServer;
import projectManager.Project;

public class reportManager {

	public void generateReport(Project pro){

		String[] title = new String[3];
		title[0] = "Weekly status report for project: " + pro.getName();
		title[1] = "week: ";
		title[2] = "Basic info:";
		
		
		String[] head = generateHeader(pro);
		String[] body = generateBody(pro);
		
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
		return null;
	}

	public void printReport() {
		// TODO Auto-generated method stub
		
	}

	
	
}
