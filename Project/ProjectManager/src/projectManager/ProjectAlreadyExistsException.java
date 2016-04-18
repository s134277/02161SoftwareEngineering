package projectManager;

public class ProjectAlreadyExistsException extends Exception {
	private String operation;
	public ProjectAlreadyExistsException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}

	public String getOperation() {
		return operation;
	}
}
