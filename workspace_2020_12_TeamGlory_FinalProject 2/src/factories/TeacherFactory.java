package factories;

import models.Person;
import models.Teacher;

public class TeacherFactory extends PersonFactoryAPI{

	@Override
	public Person getObject() {
		// TODO Auto-generated method stub
		return new Teacher();
	}
	public static Teacher getTeacher(int teacherID,String name,int age,int credits) {
		return new Teacher(teacherID,name,age,credits);
	}

}
