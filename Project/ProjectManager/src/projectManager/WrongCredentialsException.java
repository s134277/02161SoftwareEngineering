package projectManager;

public class WrongCredentialsException extends Exception {
	private String operation;
	public WrongCredentialsException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}

	public String getOperation() {
		return operation;
	}
}
