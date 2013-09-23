package model.person;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

	private List<Student> students;
	
	public StudentManager() {
		students = new ArrayList<Student>();
	}
	
	public void add(Student stu) {
		students.add(stu);
	}
	
	public void modify(Student oldStu, Student newStu) {
		int index = students.indexOf(oldStu);
		students.set(index, newStu);
	}
	
	public boolean delete(Student stu) {
		return students.remove(stu);
	}
	
	public Student get(int ssn) {
		Student returnStu = null;
		for (int i = 0; i < students.size(); i++) {
			Student thisStu = students.get(i);
			if (thisStu.getSsn() == ssn)
			{
				returnStu = thisStu;
			}
		}
		return returnStu;
	}
}
