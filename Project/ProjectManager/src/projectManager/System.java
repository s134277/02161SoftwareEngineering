package projectManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class System {
	private DateServer dateServer;
	private List<User> users = new ArrayList<User>();
	private List<Project> projects = new ArrayList<Project>();
	private User currentUser;
	private boolean loggedIn = false;
	
	public System(){
		
	}

	public List<User> getUsers() {
		return users;
	}

	public void register(User user) throws UserAlreadyExistsException, NoPasswordEnteredException {
		for(User user1 : users){
			if(user.getName().equals(user1.getName())){
				throw new UserAlreadyExistsException("Register user operation not allowed if username already taken.","CreateUser");
			}
		}
		if(user.getPW().isEmpty()) throw new NoPasswordEnteredException("Register user operation not allowed if no password entered.","CreateUser");
		users.add(user);
	}

	public Boolean login(String name, String PW) throws WrongCredentialsException {
		User user = findDev(name); 
		if(user == null) throw new WrongCredentialsException("Wrong Password or Username", "Login");
		if(user.getPW().equals(PW)){
			currentUser = user;
			loggedIn = true;
		}else{
			throw new WrongCredentialsException("Wrong Password or Username", "Login");
		}
		return loggedIn;
	}
	public User findDev(String name){
		for(User user : users){
			if(name.equals(user.getName())) return user;
		}
		return null;
	}
	public Boolean logOut() {
		currentUser = null;
		loggedIn = false;
		return loggedIn;
	}

	public void createProject(Project pro) throws ProjectAlreadyExistsException {
		for(Project proj : projects){
			if(pro.getName().equals(proj.getName())){
				throw new ProjectAlreadyExistsException("Project Already Exists","CreateProject");
			}
		}
		projects.add(pro);
	}

	public List<Project> getProjects() {
		return projects;
	}

	public Project findProject(String name) {
		for(Project pro : projects){
			if(name.equals(pro.getName())) return pro;
		}
		return null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public Date getDate() {
		Calendar c = dateServer.getDate();
		return new Date(c.WEEK_OF_YEAR,c.YEAR,this);
	}

	
}