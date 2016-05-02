package Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import projectManager.Activity;
import projectManager.MAIN;
import projectManager.Project;
import projectManager.User;
import projectManager.UserNotFoundException;

public class menuManager {
	
	static userInput ui = new userInput();
	
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

	public int viewSelectedProject(Project project,boolean isLeader) {
		System.out.println("Project: " + project.getName());
		System.out.println("Options:");
		System.out.println("0. Cancel(return)");
		System.out.println("1. View project details");
		System.out.println("2. Add developers");
		System.out.println("3. Add/change project leader");
		if(isLeader){
		System.out.println("4. Add activity");
		System.out.println("5. View/edit activities");
		System.out.println("6. Delete project");
		System.out.println("7. Generate project report");
		return ui.intInputInterval("a number", 7);
		}else
			return ui.intInputInterval("a number", 3);
		
		
	}

	public String createProject() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Project name:");
		return in.nextLine();
	}

	public void displayProjectDetails(Project project,reportManager rm) {
		
		System.out.println("Details for project: " + project.getName());
		String[] projectInfo = rm.generateHeader(project);
		for(String s : projectInfo){
			System.out.println(s);
		}
		
		System.out.println("Activities:");
		if(project.getActivities().isEmpty())System.out.println("No activities");
		else printActivitiesAndDevelopers(project);
	}

	public void printActivitiesAndDevelopers(Project project) {
		List<Activity> Activities = project.getActivities();
		int index = 1;
		for(Activity act : Activities){
			System.out.println(index + ". " + act.getName());
			index++;
			if(act.getUsers().isEmpty()) System.out.println("   - No developers added");
			else{
				List<User> Developers = act.getUsers();
				for(User dev : Developers){
					System.out.println("   - " + dev.getName());
				}
			}
		}
	}

	public String[] addActivity() {
		String data[] = new String[5];
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Add activity:");
		System.out.println("Enter activity name:");
		data[0] = in.nextLine();
		System.out.println("Enter description:");
		data[1] = in.nextLine();
		System.out.println("Enter start date: (format: ww.yyyy)");
		data[2] = in.nextLine();
		System.out.println("Enter end date: (format: ww.yyyy)");
		data[3] = in.nextLine();
		System.out.println("Enter time budget: (hours)");
		data[4] = in.nextLine();
		return data;
	}

	public int editActivity(Activity act) {
		int choice = 0;
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Edit activity: (select an number to change the info)");
		System.out.println("0. Cancel");
		System.out.println("1. Name: " + act.getName());
		System.out.println("2. Description: " + act.getDescription());
		System.out.println("3. Start date: " + act.getStartDate().getWeek()+"."+act.getStartDate().getYear() + "(format: ww.yyyy)");
		System.out.println("4. End date: " + act.getEndDate().getWeek()+"."+act.getEndDate().getYear() + "(format: ww.yyyy)");
		System.out.println("5. Time budget: " + act.getTimeBudget());
		System.out.println("6. Add developer");
		System.out.println("7. Delete activity");
		choice = in.nextInt();
		
		if(choice < 0 || choice > 7) return 0;
		else return choice; 
	}

	public int editUser() {
		int choice = 0;
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.println("Edit user: 8select one)");
		System.out.println("1. Change username");
		System.out.println("2. Change password");
		System.out.println("3. Change weekly work hours");
		System.out.println("4. Delete user");
		
		choice = in.nextInt();
		if(choice < 0 || choice > 4) return 0;
		else return choice; 
	}

	public Activity selectUserRelatedActivity(Project proj, User user) {
		List<Activity> Activities = proj.getActivities();
		int index = 1;
		for(Activity act : Activities){
			System.out.println(index + ". " + act.getName() + " : " + user.getRegisteredTime(act));
			index++;
		}
		
		int choice = ui.intInputInterval("an activity number", index-1);
		
		return Activities.get(choice);
		
	}

	public Project selectUserRelatedProject(MAIN main) {
		User user = main.getCurrentUser();
		
		List<Project> projects = new ArrayList<Project>();
		for(Project proj : main.getProjects()){
			try{
				proj.findDev(user.getName());
				projects.add(proj);
			}catch(UserNotFoundException e){
				continue;
			}
		}
		
		if(projects.isEmpty()){
			System.out.println("You are not registered with any projects yet");
			return null;
		}
		
		System.out.println("Projects you are associated with:");
		int index = 1;
		for(Project proj : projects){
			System.out.println(index + ". " + proj.getName());
		}
		int selection = ui.intInputInterval("a project number", index-1);
		return projects.get(selection-1);
	}

}
