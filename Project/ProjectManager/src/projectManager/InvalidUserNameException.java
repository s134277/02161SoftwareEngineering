package projectManager;

public class InvalidUserNameException extends Exception {
	private String operation;
	public InvalidUserNameException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}
}