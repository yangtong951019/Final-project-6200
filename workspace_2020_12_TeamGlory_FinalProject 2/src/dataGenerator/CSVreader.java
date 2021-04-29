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
				StringTokenizer st = new StringTokenizer(line,",");
				int age,grade,hib,dTap,pollio,hepatitisB;
				boolean mMR,varicella;
				String name;
				if(st.hasMoreTokens() && sign) {
					name = st.nextToken().trim();
					age = Integer.valueOf(st.nextToken().trim());
					grade = Integer.valueOf(st.nextToken().trim());
					hib = Integer.valueOf(st.nextToken().trim());
					dTap = Integer.valueOf(st.nextToken().trim());
					pollio = Integer.valueOf(st.nextToken().trim());
					hepatitisB = Integer.valueOf(st.nextToken().trim());
					if(Integer.valueOf(st.nextToken().trim())==0)
						mMR=false;
					else
						mMR=true;
					if(Integer.valueOf(st.nextToken().trim())==0)
						varicella=false;
					else
						varicella=true;
					StudentController.addStudent(name, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella);					
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
