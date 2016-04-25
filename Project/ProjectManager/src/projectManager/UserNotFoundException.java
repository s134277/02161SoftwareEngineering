package projectManager;

public class UserNotFoundException extends Exception {
	private String operation;
	public UserNotFoundException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}

	public String getOperation() {
		return operation;
	}
}
