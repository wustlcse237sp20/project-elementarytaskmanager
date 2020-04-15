package taskmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputUtils {
	public static String promptUser(String prompt) {
		System.out.println(prompt);
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
