package Interface;

import java.util.*;
import projectManager.*;
import projectManager.Date;

public class Interface {
	static menuManager mn = new menuManager();
	static MAIN main = new MAIN();
	
	public static void main(String[] args) throws Exception {
		
		boolean exit = false;
		while(!exit && !main.getLoggedIn()){ 
			int choice = mn.printWelcomeMenu();
			switch (choice){
				case 0: break;
				case 1: login(); break;
				case 2: createUser(); break;
				case 3:	exit = true; break;
			}	
		}
		
		while(!exit && main.getLoggedIn()){
			int choice = mn.printMainMenu();
			switch(choice){
				case 0: break;
				case 1: viewProjects(); break;
				case 2: createProject(); break;
				case 3: break; //register time
				case 4: break; //edit user
				case 5: main.logOut(); break;
			}
		}
	}

	private static void createProject() {
		String projectName = mn.createProject();
		
		Project pro = new Project(projectName,main);
		try {
			main.createProject(pro);
			System.out.println("Project " + projectName + " succesfully created");
		} catch (ProjectAlreadyExistsException e) {
			System.out.println("Project with desired name does already exist!");
		}
		
	}

	private static void viewProjects() {
		List<Project> projects = main.getProjects();
		int choice = mn.viewProjects(projects);
		
		if(choice == -1){
			// do nothing (no active projects)
		} else if(choice == 0){
			// do nothing (user cancelled)
		} else viewSelectedProject(projects.get(choice-1));
	}

	private static void viewSelectedProject(Project project) {
		boolean cancel = true;
		while(cancel){
			int choice = mn.viewSelectedProject(project);
			switch (choice){
				case 0: cancel = false; break;
				case 1: mn.displayProjectDetails(project); break;
				case 2: addDeveloper(project); break;
				case 3:	addProjectLeader(project); break;
				case 4: addActivity(project); break;
				case 5: editActivity(project); break;
			}
		}
	}

	private static void editActivity(Project project) {
		System.out.println("Activities:");
		System.out.println("0. Cancel");
		mn.printActivitiesAndDevelopers(project);
		
		int choice = mn.displayActivity();
		
		if(choice== 0){
			//user as selected cancel
		}else{
			///// MISSING TO DO!
		}
		
		
		
	}

	private static void addActivity(Project project) {
		String data[] = mn.addActivity();
		
		// Generating start end end-date from the data:
		String[] start = data[2].split("\\.");
		int startWeek = Integer.parseInt(start[0]);
		int startYear = Integer.parseInt(start[1]);
		
		String[] end = data[3].split("\\.");
		int endWeek = Integer.parseInt(end[0]);
		int endYear = Integer.parseInt(end[1]);
		
		Date d1 = new Date(startWeek,startYear,main);
		Date d2 = new Date(endWeek,endYear,main);
		
		// Generating timeBudget from data:
		int timeBudget = Integer.parseInt(data[4]);
		
		Activity act = new Activity(data[0],data[1],d1,d2,timeBudget);
		
		try {
			project.addActivity(act);
			System.out.println("The activity " + data[0] + " has succesfully been added");
		} catch (NotProjectLeaderException e) {
			System.out.println(e.getMessage());
		} catch (ActivityAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void addProjectLeader(Project project) {
		String developer = mn.addDeveloper();
		
		project.setProjectLeader(main.findDev(developer));
		System.out.println("Developer " + developer + " succesfully designated as project leader");
		
	}

	private static void addDeveloper(Project project) {
		String developer = mn.addDeveloper();
		
		project.addDev(main.findDev(developer));
		System.out.println("Developer " + developer + " Succesfully added to project");
		
		
	}

	private static void createUser() throws Exception {
		String data[] = mn.createUser();
		int WeeklyWorkHours = Integer.parseInt(data[2]);
		User user = new User(data[0],data[1],WeeklyWorkHours);
		
		try{
			main.register(user);
			System.out.println("Success! - The user has been created");
		} catch(UserAlreadyExistsException e){
			System.out.println("Something went wrong, either the username is already taken or you left some input fields empty!");
		}
		main(null);
	}

	private static void login() throws Exception{
		String data[] = mn.login();
		
		try {
			main.login(data[0], data[1]);
		} catch (WrongCredentialsException e) {
			System.out.println("Error: wrong username or password");
		}
		main(null);
	}
}
