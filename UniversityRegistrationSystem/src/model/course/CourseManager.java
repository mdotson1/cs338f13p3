package model.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.Manager;

public class CourseManager implements Manager<Course> {
	
private List<Course> courses;
	
	public CourseManager() {
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
		for (int i = 0; i < courses.size(); i++) {
			Course thisCourse = courses.get(i);
			if (thisCourse.getCourseNumber() == courseNumber)
			{
				if (thisCourse.getDepartment() == department) {
					returnCourse = thisCourse;
					break;
				}
			}
		}
		return returnCourse;
	}
	
	@Override
	public Iterator<Course> getAll() {
		return courses.iterator();
	}

}
