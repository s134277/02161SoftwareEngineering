package projectManager;

public class ActivityAlreadyExistsException extends Exception {
	private String operation;
	public ActivityAlreadyExistsException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}

	public String getOperation() {
		return operation;
	}
}