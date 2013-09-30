package main.java.com.cs388f13p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentManager implements PersonManager<Student> {

	private List<Student> students;
	
	private static class SingletonHolder { 
        public static final StudentManager INSTANCE = new StudentManager();
	}
	
	public static StudentManager getInstance() {
		
		return SingletonHolder.INSTANCE;
	}
	
	private StudentManager() {
		students = new ArrayList<Student>();
	}
	
	// TODO maybe need more error checking to make sure we bill
	// after drop date
	public void billStudents() {
		Iterator<Student> all = getAll();
		while (all.hasNext()) {
			all.next().bill();
		}
	}
	
	// true if successful, false if not
	public boolean payBalance(Student s, Payment payment) {
		Student stu = get(s.getId());
		if (stu != null) {
			stu.payBalance(payment);
			return true;
		} else {
			return false;
		}
	}
	
	//@Override
	public boolean delete(Student stu) {
		return students.remove(stu);
	}
	
	//@Override
	public Student get(int id) {
		
		Student returnStu = null;
		Iterator<Student> allStudents = getAll();
		
		while (allStudents.hasNext()) {
			Student thisStu = allStudents.next();
			if (thisStu.getId() == id)
			{
				returnStu = thisStu;
				break;
			}
		}
		return returnStu;
	}
	
	// true = added, false = not added
	public boolean enrollInCourse(final Student stu,
			final CourseOffering course) {
		if (stu.getNumCourses() == 4) {
			return false;
		} else {
			if (studentIsTakingCourse(stu, course)) {
				return false;
			}
			else
			{
				stu.addCourse(course);
				return true;
			}
		}
	}
	
	private boolean studentIsTakingCourse(final Student stu,
			final CourseOffering course) {
		return stu.getCourses().contains(course);
	}
	
	// true = dropped, false = not dropped
	public boolean dropCourse(final Student stu, final CourseOffering course) {
		if (studentIsTakingCourse(stu, course)) {
			stu.removeCourse(course);
			return true;
		}
		else
		{
			return false;
		}
	}

	public Student add(final Address homeAddr, final Address workAddr, 
			final PhoneNumbers phones, final String fName, final String lName,
			final Date dob, final int id) {
		
		final Student created = new Student(homeAddr, workAddr, phones, fName, lName, dob, id);
		
		if (contains(created)) {
			return null;
		} else {
			students.add(created);
			return created;
		}
	}

	//@Override
	public Iterator<Student> getAll() {
		return students.iterator();
	}

	//@Override
	public boolean contains(final Student s) {
		if (get(s.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}
}
