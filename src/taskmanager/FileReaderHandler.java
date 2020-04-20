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
	
	
	
	/**
	 * gets all lines from a file
	 * @return list of each line as a string
	 */
	public List<String> getLines(){
		List<String> lines = new LinkedList<String>();
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Could not read line from file.");
		}
		
		return lines;
	}
	
	/**
	 * determines if a file contains a particular line
	 * @param lineToFind desired line
	 * @return boolean true if contains line, false otherwise
	 */
	public boolean containsLine(String lineToFind) {
		boolean containsLine = false;
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.equals(lineToFind)) {
					containsLine = true;
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Could not read file.");
			e.printStackTrace();
		}
		
		return containsLine;
	}
	
}
