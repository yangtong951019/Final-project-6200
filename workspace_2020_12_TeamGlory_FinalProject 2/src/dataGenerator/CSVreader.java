package dataGenerator;
import java.io.*;

public class CSVreader {
	public static void readCSV() throws IOException {
		String file = "student.csv";
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line=reader.readLine())!=null) {
				String[] row = line.split(",");
				
				for(String index:row) {
					System.out.printf("%10s",index);
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			reader.close();
		}
	}
	
}
