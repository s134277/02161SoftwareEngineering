package projectManager;

public class User {
	private String name; 
	private String PW; 
	
	public User(String name, String PW) {
		this.name = name;
		this.PW = PW;
	}

	public String getName() {
		return name;
	}

	public String getPW() {
		return PW;
	}

}
