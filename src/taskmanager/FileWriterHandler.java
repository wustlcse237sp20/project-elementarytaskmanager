package taskmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterHandler {
	private BufferedWriter writer;
	
	public FileWriterHandler(File file) {
		try {
			this.writer = new BufferedWriter(new FileWriter(file, true));	// Set true for append mode
		} catch (IOException e) {
			System.out.println("Could not find file " + file);
		}
	}
	
	
	
	public void writeLine(String line) {
		try {
			writer.write(line);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Could not write '" + line + "' to file");
		}
	}
}
