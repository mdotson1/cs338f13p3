package model.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.Manager;
import model.person.Professor;
import model.person.Student;
import model.time.Semester;

public class CourseOfferingManager implements Manager<CourseOffering> {

	private List<CourseOffering> courses;
	
	private static class SingletonHolder { 
        public static final CourseOfferingManager INSTANCE = 
        		new CourseOfferingManager();
	}
	
	public static CourseOfferingManager getInstance() {
		
		return SingletonHolder.INSTANCE;
	}
	
	private CourseOfferingManager() {
		courses = new ArrayList<CourseOffering>();
	}
	
	public List<CourseOffering> getCoursesInSemester(final Semester sem) {
		final List<CourseOffering> courses = new ArrayList<CourseOffering>();
		final Iterator<CourseOffering> all = getAll();
		
		while (all.hasNext()) {
			CourseOffering thisCourse = all.next();
			if (thisCourse.getSemester().equals(sem)) {
				courses.add(thisCourse);
			}
		}
		return courses;
	}
	
	// true = added, false = not added
	public boolean addStudent(final Student s, final CourseOffering course) {
		
		if (course.getNumberOfStudents() == 10) {
			return false;
		} else {
			return course.addStudent(s);
		}
	}
	
	public boolean dropStudent(final Student student, 
			final CourseOffering course) {
		
		if (course.getNumberOfStudents() == 3) {
			return false;
		} else {
			return course.removeStudent(student);
		}
	}
	
	public CourseOffering add(final String dept, final short courseNum,
			final List<Course> prereqs, final int cost, final Semester sem,
			final short sectionNum) {
		
		final CourseOffering created = new CourseOffering(dept, courseNum, 
				prereqs, cost, sem, sectionNum);
		
		if (contains(created)) {
			return null;
		} else {
			courses.add(created);
			return created;
		}
	}
	
	// true = assigned prof, false = not assigned
	public boolean assignProfessorForCourse(final CourseOffering course,
			final Professor prof) {
		
		if (course.getProfessor() == null) {
			course.setProfessor(prof);
			return true;
		} else {
			return false;
		}
	}
	
	// true = removed prof, false = not removed
	public boolean removeProfessor(final CourseOffering course) {
		if (course.getProfessor() == null) {
			return false;
		} else {
			course.setProfessor(null);
			return true;
		}
	}
	
	public List<Student> getRoster(final CourseOffering course) {
		return course.getRoster();
	}

	@Override
	public boolean delete(final CourseOffering obj) {
		return courses.remove(obj);
	}
	
	public CourseOffering get(final String department, 
			final short courseNumber, final short sectionNumber) {
		
		CourseOffering returnCourseOffering = null;
		final Iterator<CourseOffering> allOfferings = getAll();
		
		CourseOffering thisOffering ;
		while (allOfferings.hasNext()) {
			thisOffering = allOfferings.next();
			
			if (thisOffering.getCourseNumber() == courseNumber
					&& thisOffering.getDepartment().equals(department)
					&& thisOffering.getSectionNumber() == sectionNumber) {
				
				returnCourseOffering = thisOffering;
				break;
			}
		}
		return returnCourseOffering;
	}
	
	@Override
	public Iterator<CourseOffering> getAll() {
		return courses.iterator();
	}

	@Override
	public boolean contains(final CourseOffering obj) {
		if (get(obj.getDepartment(), obj.getCourseNumber(), 
				obj.getSectionNumber()) == null) {
			return false;
		} else {
			return true;
		}
	}

}
