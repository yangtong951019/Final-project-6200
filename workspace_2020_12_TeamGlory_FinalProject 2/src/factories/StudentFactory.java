package factories;

import java.util.Date;

import models.Person;
import models.Student;

public class StudentFactory extends PersonFactoryAPI {

	@Override
	public Person getObject() {
		// TODO Auto-generated method stub
		return new Student();
	}
	public static Student getStudent(String name, int age, int grade,int hib, int dTap, int pollio,int hepatitisB, boolean mMR,boolean varicella) {
		return new Student(name,age,grade,hib,dTap,pollio,hepatitisB,mMR,varicella);
	}
	public static Student getStudent(int studentID,String name, int age, int grade,Date registrationDay,int hib, int dTap, int pollio,int hepatitisB, boolean mMR,boolean varicella) {
		return new Student(studentID,name,age,grade,registrationDay,hib,dTap,pollio,hepatitisB,mMR,varicella);
	}
}
