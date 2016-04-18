package projectManager;

public class NotProjectLeaderException extends Exception {
	private String operation;
	public NotProjectLeaderException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}

	public String getOperation() {
		return operation;
	}
}
