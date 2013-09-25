package model.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.Manager;

public class CourseOfferingManager implements Manager<CourseOffering> {

	private List<CourseOffering> courses;
	
	public CourseOfferingManager() {
		courses = new ArrayList<CourseOffering>();
	}
	
	@Override
	public void add(CourseOffering obj) {
		courses.add(obj);
	}

	@Override
	public void modify(CourseOffering oldObj, CourseOffering newObj) {
		int index = courses.indexOf(oldObj);
		courses.set(index, newObj);
	}

	@Override
	public boolean delete(CourseOffering obj) {
		return courses.remove(obj);
	}
	
	public CourseOffering get(String department, short courseNumber, short sectionNumber) {
		CourseOffering returnCourseOffering = null;
		for (int i = 0; i < courses.size(); i++) {
			CourseOffering thisOffering = courses.get(i);
			if (thisOffering.getCourseNumber() == courseNumber) {
				if (thisOffering.getDepartment() == department) {
					if (thisOffering.getSectionNumber() == sectionNumber) {
						returnCourseOffering = thisOffering;
						break;
					}
				}
			}
		}
		return returnCourseOffering;
	}
	
	@Override
	public Iterator<CourseOffering> getAll() {
		return courses.iterator();
	}

}
