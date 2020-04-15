package taskmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputUtils {
	public static String promptUser(String prompt) {
		System.out.println("Please type your username and hit Enter to login to Elementary Task Manager");
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String response = null;
		try {
			response = inputReader.readLine();
		} catch (IOException e) {
			System.out.println("Couldn't read user input");
		}
		
		return response;
	}
}
