package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import taskmanager.*;


public class FileHandlerTests {
	File testFile = new File("./src/fileTesting.txt");

	@Test
	public void writeAndReadLine() throws IOException {
		testFile.createNewFile();
		FileWriterHandler fw = new FileWriterHandler(testFile);
		fw.writeLine("line1");
		FileReaderHandler fr = new FileReaderHandler(testFile);
		assertTrue(fr.containsLine("line1"));
	}
	
	@Test
	public void testAppendMode() throws IOException {
		testFile.createNewFile();
		FileWriterHandler fw = new FileWriterHandler(testFile);
		fw.writeLine("line2");
		FileWriterHandler fw2 = new FileWriterHandler(testFile);
		fw2.writeLine("line3");
		FileReaderHandler fr = new FileReaderHandler(testFile);
		assertTrue(fr.containsLine("line3"));
		FileReaderHandler fr2 = new FileReaderHandler(testFile);
		assertFalse(fr2.containsLine("line2"));
	}
	@Test
	public void writeAndReadMultipleLines() throws IOException {
		testFile.createNewFile();
		FileWriterHandler fw = new FileWriterHandler(testFile);
		List<String> linesToAdd = new LinkedList<String>();
		linesToAdd.add("Sarah");
		linesToAdd.add("Emily");
		linesToAdd.add("Jessica");
		linesToAdd.add("Claire");
		fw.writeLines(linesToAdd);
		FileReaderHandler fr = new FileReaderHandler(testFile);
		List<String> linesFromRead = fr.getLines();
		int count = 0;
		for(String line : linesFromRead) {
			if(line.equals("Sarah")) {
				count++;
			}
			if(line.equals("Emily")) {
				count++;
			}
			if(line.equals("Jessica")) {
				count++;
			}
			if(line.equals("Claire")) {
				count++;
			}
		}
		assertTrue(count == 4);
	}

}
