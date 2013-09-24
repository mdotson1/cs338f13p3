package model.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.PersonManager;

public class StudentManager implements PersonManager<Student> {

	private List<Student> students;
	
	public StudentManager() {
		students = new ArrayList<Student>();
	}
	
	@Override
	public boolean delete(Student stu) {
		return students.remove(stu);
	}
	
	@Override
	public Student get(int ssn) {
		Student returnStu = null;
		for (int i = 0; i < students.size(); i++) {
			Student thisStu = students.get(i);
			if (thisStu.getSsn() == ssn)
			{
				returnStu = thisStu;
				break;
			}
		}
		return returnStu;
	}

	@Override
	public void add(Student obj) {
		students.add(obj);
	}

	@Override
	public void modify(Student oldObj, Student newObj) {
		int index = students.indexOf(oldObj);
		students.set(index, newObj);
	}

	@Override
	public Iterator<Student> getAll() {
		return students.iterator();
	}
}
