package taskmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class FileReaderHandler {
	private BufferedReader reader;
	
	public FileReaderHandler(String pathname) {
		try {
			this.reader = new BufferedReader(new FileReader(new File(pathname)));
		} catch (FileNotFoundException e) {
			System.out.println("File " + pathname + " wasn't found.");
		}
	}
	
	public FileReaderHandler(File file) {
		try {
			this.reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File " + file + " wasn't found.");
		}
	}
	
	
	
	
	public List<String> getLines(){
		List<String> lines = new LinkedList<String>();
		
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			System.out.println("Could not read line from file.");
		}
		
		return lines;
	}
	
}
