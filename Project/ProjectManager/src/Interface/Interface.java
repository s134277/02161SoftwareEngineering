package Interface;

import java.util.*;
import projectManager.*;

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
				case 3: break;
				case 4: break;
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
		} else{
			viewSelectedProject(projects.get(choice-1));
		}
	}

	private static void viewSelectedProject(Project project) {
		int choice = mn.viewSelectedProject(project);
		switch (choice){
			case 0: break;//cancel
			case 1: mn.displayProjectDetails(project); break;//project details
			case 2: break;//add developer
			case 3:	break;//add project leader
			case 4: break;//add activity
			case 5: break;//view/edit activities
		}
		
		
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
