package factories;

import models.Person;
import models.Teacher;

public class TeacherFactory extends PersonFactoryAPI{

	@Override
	public Person getObject() {
		// TODO Auto-generated method stub
		return new Teacher();
	}
	public static Teacher getTeacher(String name,int age,int credits) {
		return new Teacher(name,age,credits);
	}
	public static Teacher getTeacher(int TeacherID,String name,int age,int credits) {
		return new Teacher(TeacherID,name,age,credits);
	}
	public static Teacher getTeacher(int TeacherID,String name,int age,int credits,int groupID) {
		return new Teacher(TeacherID,name,age,credits,groupID);
	}
}
