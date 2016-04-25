package projectManager;

public class NoPasswordEnteredException extends Exception {
	private String operation;
	public NoPasswordEnteredException(String errorMsg, String operation){
		super(errorMsg);
		this.operation = operation;
		
	}
}
