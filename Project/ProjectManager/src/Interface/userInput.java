package Interface;

import java.util.Scanner;

/**
 * Note: we used 
 * http://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner
 * for inspiration on how to make the do-while loops that takes input and validates it.
 *
 */

public class userInput {
	
	public int intInput(String type){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		int input;
	    do {
	    	System.out.println("Enter " + type + ":");
	        while (!in.hasNextDouble()) {
	            System.out.println("Incorrect input");
	            in.next();
	        }
	        input = in.nextInt();
	    } while (input < 0);
	   
		return input;
	}
	
	public String stringInput(String type){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter " + type + ":");
		String input = in.nextLine();
		
		
		//checks correctness of input:
		if(input.isEmpty()){
			System.out.println("No input entered, nothing is changed");
			return null;
		}
		
		try{
			@SuppressWarnings("unused")
			int testInt = Integer.parseInt(input);
			System.out.println("You entered an integer when a string was expected");
			System.out.println("Nothing is changed");
			return null;
		} catch(Exception e){
			return input;
		}
	}

	public double doubleInput(String type) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		double input;
	    do {
	    	System.out.println("Enter " + type + ":");
	        while (!in.hasNextDouble()) {
	            System.out.println("Incorrect input");
	            in.next();
	        }
	        input = in.nextDouble();
	    } while (input < 0);
	   
		return input;
	}
	
	public int intInputInterval(String type, int max){
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		int input;
	    do {
	    	System.out.println("Enter " + type + ":");
	        while (!in.hasNextDouble()) {
	            System.out.println("Incorrect input");
	            in.next();
	        }
	        input = in.nextInt();
	    } while (input < 0 || input > max);

		return input;
	}
	
	public String stringInputSpecificLength(String type, int value){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		String input;
	    do {
	    	System.out.println("Enter " + type + ":");
	        while (!in.hasNextLine()) {
	            System.out.println("Incorrect input, must be of length " + value);
	            in.next();
	        }
	        input = in.nextLine();
	    } while (input.length() != value);
	   
		return input;
	}
	
	
}
