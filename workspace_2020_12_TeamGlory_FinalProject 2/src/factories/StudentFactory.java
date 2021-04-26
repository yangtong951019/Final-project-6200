package factories;

import models.Person;
import models.Student;

public class StudentFactory extends PersonFactoryAPI {

	@Override
	public Person getObject() {
		// TODO Auto-generated method stub
		return new Student();
	}
	public static Student getStudent(String name, int age, int grade) {
		return new Student(name,age,grade);
	}
	public static Student getStudent(int studentID,String name, int age, int grade) {
		return new Student(studentID,name,age,grade);
	}
}
