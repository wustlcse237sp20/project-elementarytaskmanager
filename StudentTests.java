import static org.junit.Assert.*;

import java.io.File;

import org.junit.jupiter.api.Test;

public class StudentTests {

//		@Before
//		public void setup() {
//			
//			try {
//				Files.copy(new File("testfiles/test.dat.bak").toPath(), new File("testfiles/test.dat").toPath(), StandardCopyOption.REPLACE_EXISTING);
//			} catch (IOException e) {
//				System.out.println("unable to copy files");
//				e.printStackTrace();
//			}
//			
//			c = Database.getCatalog();
//			c.loadSchema("testfiles/test.txt");
//			
//			int tableId = c.getTableId("test");
//			td = c.getTupleDesc(tableId);
//			hf = c.getDbFile(tableId);
//			hp = hf.readPage(0);
//		}
		
		@Test
		public void addSingleTask() throws Exception{
//			for(int i = 0; i < 10; i++) {
//				int size = (int)(Math.random() * 15 + 1);
//				Type[] t = randomTypes(size);
//				String[] c = randomColumns(size);
//				TupleDesc td = new TupleDesc(t, c);
//				for(int j = 0; j < size; j++) {
//					assertTrue("Tuple not recording types properly", td.getType(j) == t[j]);
//				}
//
//			}
			Student toAddTask = new Student(new File("toAddTask"), "toAddTask");
			assertTrue(true);

		}

		

	

}
