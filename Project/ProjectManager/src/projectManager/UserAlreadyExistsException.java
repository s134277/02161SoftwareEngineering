package projectManager;

public class UserAlreadyExistsException extends Exception {
private String operation;
	public UserAlreadyExistsException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}

	public String getOperation() {
		return operation;
	}
}
