package model.registration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.course.CourseOffering;
import model.person.Professor;
import model.person.Student;
import model.time.Date;
import model.time.Semester;

public class Registrar {

	private List<CourseOffering> courseCatalog = new ArrayList<CourseOffering>();
	
	public Registrar() {
		
	}
	
	public void addCourseToCatalog(CourseOffering course) {
		courseCatalog.add(course);
	}
	
	public List<CourseOffering> getCourseCatalog() {
		return courseCatalog;
	}
	
	public List<Student> getRosterForCourse(CourseOffering course) {
		return course.getRoster();
	}
	
	public List<CourseOffering> getCourseCatalog(Semester sem) {
		return null;
	}
	
	// modify courseoffering
	public boolean assignProfessorToCourse(CourseOffering course, Professor prof) {
		return course.assignProfessor(prof);
	}
	
	public boolean removeProfessorFromCourse(CourseOffering course) {
		return course.removeProfessor();
	}
	
	public boolean allCoursesInSameSemester(Iterator<CourseOffering> courses) {
		Semester firstSemester;
		if (courses.hasNext()) {
			firstSemester = courses.next().getSemester();
		} else {
			return false;
		}
		while (courses.hasNext()) {
			if (!(courses.next().getSemester().equals(firstSemester))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Enrolls a student in the desired courses 
	 * @param s student to add courses to
	 * @param desiredCourses list of all courses a student wishes to enroll in
	 * @param alternatives list of alternative courses to enroll in if desired is full
	 * @return true if registered, false if not
	 */
	public boolean registerForCourses(Student s, List<CourseOffering> desiredCourses,
			List<CourseOffering> alternatives, Date currentDate) {
		ArrayList<CourseOffering> concatenated = new ArrayList<CourseOffering>(desiredCourses);
		concatenated.addAll(alternatives);
		if (allCoursesInSameSemester(concatenated.iterator())) {
			if (! s.hasRegistered()) {
				if (currentDate.compareTo(sem.dropDate()) < 0) {
					
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Add a course to a student and added student to course
	 * @param s student to add course to
	 * @param course to add to student
	 * @return true if added both, false if not
	 */
	public boolean enrollStudentInCourse(Student s, CourseOffering course, Date currentDate) {
		if (validDate(s, course.getSemester(), currentDate)) {
			boolean addedCourseToStudent = s.enrollInCourse(course);
			if (addedCourseToStudent) {
				boolean addedStudentToCourse = course.addStudent(s);
				if (addedStudentToCourse) {
					return true;
				} else {
					s.dropCourse(course); // backtrack and drop the student
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean dropStudentFromCourse(Student s, CourseOffering course, Date currentDate) {
		if (validDate(s, course.getSemester(), currentDate)) {
			boolean droppedCourseFromStudent = s.dropCourse(course);
			if (droppedCourseFromStudent) {
				boolean droppedStudentFromCourse = course.dropStudent(s);
				if (droppedStudentFromCourse) {
					return true;
				} else {
					s.enrollInCourse(course);
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Can only enroll or drop after registering and 1 week before classes
	 * @param sem course's semester
	 * @param currentDate
	 * @return true if the date is valid for enrolling or dropping
	 */
	public boolean validDate(Student student, Semester sem, Date currentDate) {
		if (student.hasRegistered()) {
			if (currentDate.compareTo(sem.dropDate()) < 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
