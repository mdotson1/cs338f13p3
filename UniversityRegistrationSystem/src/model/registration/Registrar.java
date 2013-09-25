package model.registration;

import java.util.ArrayList;
import java.util.List;

import model.course.CourseOffering;
import model.person.Professor;
import model.person.Student;
import model.time.Date;
import model.time.Semester;

public class Registrar {

	private List<CourseOffering> courseCatalog = new ArrayList<CourseOffering>();
	private Semester semester;
	private Date lastDayToChange;
	
	public Registrar() {
		
	}
	
	public void setSemester(Semester sem) {
		semester = sem;
		Date start = sem.getStartDate();
		lastDayToChange = sem.dropDate();
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
	
	/**
	 * Enrolls a student in the desired courses 
	 * @param s student to add courses to
	 * @param desiredCourses list of all courses a student wishes to enroll in
	 * @param alternatives list of alternative courses to enroll in if desired is full
	 */
	public void registerForCourses(Student s, List<CourseOffering> desiredCourses,
			List<CourseOffering> alternatives) {
		
	}
	
	/**
	 * Add a course to a student and added student to course
	 * @param s student to add course to
	 * @param course to add to student
	 * @return true if added both, false if not
	 */
	public boolean enrollStudentInCourse(Student s, CourseOffering course, Date currentDate) {
		if (currentDate.compareTo(lastDayToChange)) {
			boolean addedCourseToStudent = s.enrollInCourse(course);
			if (addedCourseToStudent) {
				if (course.addStudent(s)) {
					return true;
				} else {
					s.dropCourse(course);
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Drop a course from a student
	 * @param s student to drop course from
	 * @param course to drop from student
	 * @return true if dropped, false if not
	 */
	public boolean dropStudentFromCourse(Student s, short courseNum, short sectionNum) {
		return false;
	}
	
	public boolean dropStudentFromCourse(Student s, CourseOffering course) {
		boolean droppedCourseFromStudent = s.dropCourse(course);
		if (droppedCourseFromStudent) {
			if (course.dropStudent(s)) {
				return true;
			} else {
				s.enrollInCourse(course);
				return false;
			}
		} else {
			return false;
		}
	}
}
