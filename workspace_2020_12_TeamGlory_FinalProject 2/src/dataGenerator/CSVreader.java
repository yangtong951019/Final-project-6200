package dataGenerator;
import java.io.*;
import java.util.StringTokenizer;

import controller.StudentController;

public class CSVreader {
	public static void readCSV() throws IOException {
		String file = "student.csv";
		BufferedReader reader = null;
//		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			boolean sign = false;
			while(reader.ready()) {
				String line = reader.readLine();
				StringTokenizer st = new StringTokenizer(line,";");
				int studentID,age,grade,groupID;
				String name;
				if(st.hasMoreTokens() && sign) {
					studentID = Integer.valueOf(st.nextToken().trim());
//					System.out.println(studentID);
					name = st.nextToken().trim();
//					System.out.println(name);
					age = Integer.valueOf(st.nextToken().trim());
//					System.out.println(age);
					grade = Integer.valueOf(st.nextToken().trim());
//					System.out.println(grade);
					groupID = Integer.valueOf(st.nextToken().trim());
//					System.out.println(groupID);
//					classroom = 
					
					StudentController.addStudent(name, age, grade, Classroom);
					
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
