package dataGenerator;
import java.io.*;
import java.sql.SQLException;
import java.util.StringTokenizer;

import controller.StudentController;

public class CSVreader {
	public static void readCSV(File file) throws IOException, SQLException {
		//String file = "student.csv";
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			boolean sign = false;
			while(reader.ready()) {
				String line = reader.readLine();
				StringTokenizer st = new StringTokenizer(line,";");
				int studentID,age,grade,groupID;
				String name;
				if(st.hasMoreTokens() && sign) {
					name = st.nextToken().trim();
					age = Integer.valueOf(st.nextToken().trim());
					grade = Integer.valueOf(st.nextToken().trim());				
					StudentController.addStudent(name, age, grade);					
				}else {
					sign = true;
				}
			}  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			reader.close();
		}
	}
	
}
