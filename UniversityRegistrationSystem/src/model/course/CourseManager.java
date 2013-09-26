/*
package model.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.Manager;

public class CourseManager implements Manager<Course> {
	
	private List<Course> courses;
	
	private static class SingletonHolder { 
        public static final CourseManager INSTANCE = new CourseManager();
	}
	
	public static CourseManager getInstance() {
		
		return SingletonHolder.INSTANCE;
	}
	
	private CourseManager() {
		courses = new ArrayList<Course>();
	}
	
	public boolean add(String dept, short courseNum, List<Course> prereqs, 
			int cost) {
		
		Course created = new Course(dept, courseNum, prereqs, cost);
		if (contains(created)) {
			return false;
		} else {
			courses.add(created);
			return true;
		}
	}

	@Override
	public boolean delete(Course obj) {
		return courses.remove(obj);
	}
	
	public Course get(String department, short courseNumber) {
		
		Course returnCourse = null;
		Iterator<Course> allCourses = getAll();
		
		while (allCourses.hasNext()) {
			
			Course thisCourse = allCourses.next();
			
			if (thisCourse.getCourseNumber() == courseNumber
					&& thisCourse.getDepartment().equals(department)) {
				returnCourse = thisCourse;
				break;
			}
		}
		return returnCourse;
	}
	
	@Override
	public Iterator<Course> getAll() {
		return courses.iterator();
	}

	@Override
	public boolean contains(Course obj) {
		if (get(obj.getDepartment(), obj.getCourseNumber()) == null) {
			return false;
		} else {
			return true;
		}
	}

}

 */
