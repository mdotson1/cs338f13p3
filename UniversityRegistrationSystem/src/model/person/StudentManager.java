package model.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.billing.Payment;
import model.manager.PersonManager;
import model.time.Date;

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
	
	@Override
	public boolean delete(Student stu) {
		return students.remove(stu);
	}
	
	@Override
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

	public boolean add(Address homeAddr, Address workAddr, PhoneNumbers phones, String fName, 
			String lName, Date dob, int id) {
		
		Student created = new Student(homeAddr, workAddr, phones, fName, lName, dob, id);
		
		if (contains(created)) {
			return false;
		} else {
			students.add(created);
			return true;
		}
	}

	@Override
	public Iterator<Student> getAll() {
		return students.iterator();
	}

	@Override
	public boolean contains(Student s) {
		if (get(s.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}
}
