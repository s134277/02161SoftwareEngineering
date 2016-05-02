package Interface;

import java.util.Scanner;

public class userInput {
	
	public int intInput(String type){
//		@SuppressWarnings("resource")
//		Scanner in = new Scanner(System.in);
//		System.out.println("Enter " + type + ":");
//		String input = in.nextLine();
//		
//		//checks correctness of input:
//		if(input.isEmpty()){
//			System.out.println("No input entered, nothing is changed");
//			return original;
//		}
//		
//		try{
//			int newInt = Integer.parseInt(input);
//			return newInt;
//		} catch(Exception e){
//			System.out.println("You entered something that wasn't an integer when an integer was expected");
//			System.out.println("Nothing is changed or defaultvalue selected");
//			return original;
//		}
		
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
	    } while (input <= 0);
	   
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
		
		Double input;
	    do {
	    	System.out.println("Enter " + type + ":");
	        while (!in.hasNextDouble()) {
	            System.out.println("Incorrect input");
	            in.next();
	        }
	        input = in.nextDouble();
	    } while (input <= 0);
	   
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
	    } while (input <= 0 && input > max);

		return input;
	}
	
}
