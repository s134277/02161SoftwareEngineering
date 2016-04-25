package Interface;

import java.util.List;
import java.util.Scanner;

import projectManager.MAIN;
import projectManager.Project;
import projectManager.User;
import projectManager.UserAlreadyExistsException;

public class menuManager {
	
	public int printWelcomeMenu(){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the project manager app!");
		System.out.println("Your options are:");
		System.out.println("(Select an option by typing the corresponding number and pressing enter)");
		System.out.println("1. Login");
		System.out.println("2. Create user");
		System.out.println("3. Exit");
		int i = in.nextInt();
	
		if(i < 0 || i > 3) return 0;
		else return i;
	}

	public String[] login() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Log in menu:");
		System.out.println("Enter username:");
		String username = in.nextLine(); 
		System.out.println("Enter password:");
		String password = in.next();
		
		return new String[]{username,password};
	}

	public String[] createUser(){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Create user menu:");
		System.out.println("Enter desired username:");
		String username = in.nextLine(); 
		System.out.println("Enter desired password:");
		String password = in.next();
		System.out.println("Enter your weekly workhour maximum:");
		int i = in.nextInt();
		String workHours = ""+i;
		return new String[]{username,password,workHours};
	}

	public int printMainMenu() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Main menu:");
		System.out.println("1. View active projects");
		System.out.println("2. Create new project");
		System.out.println("3. Register time");
		System.out.println("4. Edit user");
		System.out.println("5. Logout");
		int i = in.nextInt();
		if(i < 0 || i > 5) return 0;
		else return i;
	}

	public int viewProjects(List<Project> projects) {
		
		
		if(projects.isEmpty()){ 
			System.out.println("No active projects found!");
			return -1;
		}
		else{
			System.out.println("Current active projects:");
			System.out.println("Select a project to edit: (0 cancels)");
			int index = 0;
			do{
			System.out.println(index+1 + ". " + projects.get(index).getName());
			index++;
			}
			while(projects.size()>index);
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			
			if(i < 0 || i > index) return 0;
			else return i;
		}
	}

	public int viewSelectedProject(Project project) {
		System.out.println("Project: " + project.getName());
		System.out.println("Options:");
		System.out.println("0. Cancel(return)");
		System.out.println("1. View project details");
		System.out.println("2. Add developers");
		System.out.println("3. Add/change project leader");
		System.out.println("4. Add adtivity");
		System.out.println("5. View/edit activities");
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		
		if(i < 0 || i > 5) return 0;
		else return i;
		
	}

	public String createProject() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Project name:");
		return in.nextLine();
//		System.out.println("Customer:");
//		data[1] = in.nextLine();
//		if(data[1].isEmpty()) data[1] = "Software Huset A/S";
//		
//		System.out.println("Start date: (format: ww.yyyy)");
//		data[2] = in.nextLine();
//		
//		System.out.println("End date: (format: ww.yyyy)");
//		data[3] = in.nextLine();
//		
//		System.out.println("Time budget: (hours)");
//		data[4] = in.nextLine();
		
		
		
	}

	public void displayProjectDetails(Project project) {
		System.out.println("Details for project: " + project.getName());
		
		if(project.getProjectLeader()==null) System.out.println("Project leader: none");
		else System.out.println("Project leader: " + project.getProjectLeader().getName());
		
		if(project.getCostumer()==null) System.out.println("Customer: Software Huset A/S");
		else System.out.println("Customer: " + project.getCostumer());
		
		if(project.getStartDate()==null) System.out.println("Start date: none yet");
		else System.out.println("Start date: week " + project.getStartWeek() + " year " + project.getStartYear());
		
		
		if(project.getEndDate()==null) System.out.println("Deadline: No deadline yet");
		else System.out.println("Deadline: week " + project.getEndWeek() + " year " + project.getEndYear());
		
		if(project.getTimeBudget()==0) System.out.println("Time budget: No time budget yet");
		else System.out.println("Time budget: " + project.getTimeBudget());
		
		
	}
}
