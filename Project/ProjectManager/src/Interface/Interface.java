package Interface;

import java.util.*;
import projectManager.*;
import projectManager.Date;

public class Interface {
	static menuManager mm = new menuManager();
	static MAIN main = new MAIN();
	static reportManager rm = new reportManager();
	static userInput ui = new userInput();
	
	public static void main(String[] args) throws Exception {
		
		boolean exit = false;
		while(!exit && !main.getLoggedIn()){ 
			int choice = mm.printWelcomeMenu();
			switch (choice){
				case 0: break;
				case 1: login(); break;
				case 2: createUser(); break;
				case 3:	exit = true; break;
			}	
		}
		
		while(!exit && main.getLoggedIn()){
			int choice = mm.printMainMenu();
			switch(choice){
				case 0: break;
				case 1: viewProjects(); break;
				case 2: createProject(); break;
				case 3: registerTime(); break; //register time
				case 4: editUser(); break;
				case 5: main.logOut(); break;
			}
		}
	}

	private static void registerTime() {
		Project proj = mm.selectUserRelatedProject(main);
		
		Activity act = null;
		if(proj != null){
			act = mm.selectUserRelatedActivity(proj, main.getCurrentUser());
		}
		
		if(act != null){
			int choice = ui.intInput("");
			while(choice != 0){
				switch(choice){
					case 0: break; //cancel
					case 1: main.getCurrentUser().RegisterTime(main.getDate(),proj,act.getName(),ui.doubleInput("total work hours for today")); break; //register time
				}
			}
		}else{
			System.out.println("No activity selected or found by system");
		}
	}

	private static void editUser() {
		int choice = mm.editUser();
		switch(choice){
		case 0: break;
		case 1:			
			main.getCurrentUser().setName(ui.stringInput("new username"));
			break;
		case 2:
			main.getCurrentUser().setPW(ui.stringInput("new password"));
			break;
		case 3:
			main.getCurrentUser().setWeeklyWorkHours(ui.intInput("weekly work hours"));
			break;
		case 4:
			main.deleteGlobalUser(main.getCurrentUser());
			main.logOut();
			break;
		}
	}

	private static void createProject() {
		String projectName = mm.createProject();
		
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
		int choice = mm.viewProjects(projects);
		
		if(choice == -1||choice == 0){}// do nothing
		else viewSelectedProject(projects.get(choice-1));
	}

	private static void viewSelectedProject(Project project) {
		boolean cancel = false;
		while(!cancel){
			int choice = mm.viewSelectedProject(project);
			switch (choice){
				case 0: cancel = true; break;
				case 1: mm.displayProjectDetails(project,rm); break;
				case 2: addDeveloper(project); break;
				case 3:	addProjectLeader(project); break;
				case 4: addActivity(project); break;
				case 5: editActivity(project); break;
				case 6: deleteProject(project); break;
				case 7: generateProjectReport(project); break;
			}
		}
	}

	private static void generateProjectReport(Project project) {
		rm.printReport();
		
	}

	private static void deleteProject(Project project) {
		main.deleteProject(project);
		System.out.println("Project succesfully deleted!");
	}

	private static void editActivity(Project project) {
		System.out.println("Activities:");
		System.out.println("0. Cancel");
		mm.printActivitiesAndDevelopers(project);
		int choice = ui.intInput("an activity number from the list above");
		if(choice== 0){
			//user has selected cancel
		}else{
			Activity act = project.getActivities().get(choice-1);
			int editChoice = -1;
			while(editChoice != 0){
				editChoice = mm.editActivity(act);
				switch(editChoice){
					case 0: break;
					case 1:	act.setName(ui.stringInput("a new name")); break;
					case 2: act.setDescription(ui.stringInput("a description")); break;
					case 3: act.setStartDate(interpretDate(ui.stringInput("new date in format 'ww.yyyy'"))); break;
					case 4: act.setEndDate(interpretDate(ui.stringInput("new date in format 'ww.yyyy'"))); break;
					case 5: act.setTimebudget(ui.intInput("a time budget (hours)")); break;
					case 6: break;
					/**
					 * still needs to be able to add devs.
					 */
					case 7: project.deleteActivity(act); 
							System.out.println("Activity deleted");
							editChoice = 0; break;
				}
			}
		}
	}

	private static void addActivity(Project project) {
		String data[] = mm.addActivity();
		
		// Generating start end end-date from the data:
		Date d1 = interpretDate(data[2]);
		Date d2 = interpretDate(data[3]);
		
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
		String developer = ui.stringInput("project leader name");
		
		try {
			project.setProjectLeader(main.findDev(developer));
			System.out.println("Developer " + developer + " succesfully designated as project leader");
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addDeveloper(Project project) {
		String developer = ui.stringInput("developer name");
		
		try {
			project.addDev(main.findDev(developer));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Developer " + developer + " Succesfully added to project");
	}

	private static void createUser() throws Exception {
		System.out.println("Create user menu:");
		String username = ui.stringInput("desired username");
		String PW = ui.stringInput("desired passwrod");
		int WWH = ui.intInput("weekly workhours", 37);
		User user = new User(username,PW,WWH);
		try{
			main.register(user);
			System.out.println("Success! - The user has been created");
		} catch(UserAlreadyExistsException e){
			System.out.println("Something went wrong, either the username is already taken or you left some input fields empty!");
		}
		main(null);
	}

	private static void login() throws Exception{
		System.out.println("Log in menu:");
		String username = ui.stringInput("username");
		String PW = ui.stringInput("password");
		try {
			main.login(username, PW);
		} catch (WrongCredentialsException e) {
			System.out.println("Error: wrong username or password");
		}
		main(null);
	}
	
	private static Date interpretDate(String input) {
		//Converts a string-format ww.yyyy date into a date object
		String[] date = input.split("\\.");
		int week = Integer.parseInt(date[0]);
		int year = Integer.parseInt(date[1]);
		return new Date(week,year,main);
	}
}
