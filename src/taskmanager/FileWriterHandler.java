package taskmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterHandler {
	private BufferedWriter writer;

	public FileWriterHandler(File file, boolean append) {
		try {
			this.writer = new BufferedWriter(new FileWriter(file, append)); // Set true for append mode
		} catch (IOException e) {
			System.out.println("Could not find file " + file);
		}
	}
	/**
	 * Makes a new file
	 * @param path the path for the file within .src, excluding .txt
	 * ex.	"students/Emily"		-> ./src/students/Emily.txt
	 * 		"teachers/RonCytron"	-> ./src/teachers/RonCytron.txt
	 * @return the file created
	 */
	public static File makeFile(String path) {
		File file = new File("./src/" + path + ".txt"); 
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Couldn't create new file " + file);
			e.printStackTrace();
		}
		return file;
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
