package taskmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterHandler {
	private BufferedWriter writer;

	public FileWriterHandler(File file) {
		try {
			this.writer = new BufferedWriter(new FileWriter(file)); // Set true for append mode
		} catch (IOException e) {
			System.out.println("Could not find file " + file);
		}
	}

	/**
	 * writes a single line to the file
	 * @param line line to add
	 */
	public void writeLine(String line) {
		try {
			writer.write(line);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not write '" + line + "' to file");
		}
	}

	/**
	 * writes multiple lines to the file
	 * @param lines list of strings (lines) to add
	 */
	public void writeLines(List<String> lines) {
		for (String line : lines) {
			try {
				writer.write(line);
				writer.newLine();
			} catch (IOException e) {
				System.out.println("Could not write '" + line + "' to file");
			}

		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not close file");
		}
	}
}
