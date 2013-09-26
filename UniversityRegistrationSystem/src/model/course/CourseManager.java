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
	
	@Override
	public void add(Course obj) {
		courses.add(obj);
	}

	@Override
	public void modify(Course oldObj, Course newObj) {
		int index = courses.indexOf(oldObj);
		courses.set(index, newObj);
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

}
