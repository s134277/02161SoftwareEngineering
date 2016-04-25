package projectManager;

public class ProjectNotFoundException extends Exception {
	private String operation;
	public ProjectNotFoundException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}

	public String getOperation() {
		return operation;
}
}
