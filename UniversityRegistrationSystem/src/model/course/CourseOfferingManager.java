package model.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.Manager;
import model.time.Semester;

public class CourseOfferingManager implements Manager<CourseOffering> {

	private List<CourseOffering> courses;
	
	private static class SingletonHolder { 
        public static final CourseOfferingManager INSTANCE = new CourseOfferingManager();
	}
	
	public static CourseOfferingManager getInstance() {
		
		return SingletonHolder.INSTANCE;
	}
	
	private CourseOfferingManager() {
		courses = new ArrayList<CourseOffering>();
	}
	
	public List<CourseOffering> getCoursesInSemester(Semester sem) {
		List<CourseOffering> courses = new ArrayList<CourseOffering>();
		Iterator<CourseOffering> all = getAll();
		
		while (all.hasNext()) {
			CourseOffering thisCourse = all.next();
			if (thisCourse.getSemester().equals(sem)) {
				courses.add(thisCourse);
			}
		}
		return courses;
	}
	
	public boolean add(String dept, short courseNum, List<Course> prereqs, 
			int cost, Semester sem, short sectionNum) {
		
		CourseOffering created = new CourseOffering(dept, courseNum, prereqs, cost, sem, sectionNum);
		if (contains(created)) {
			return false;
		} else {
			courses.add(created);
			return true;
		}
	}

	@Override
	public boolean delete(CourseOffering obj) {
		return courses.remove(obj);
	}
	
	public CourseOffering get(String department, short courseNumber, short sectionNumber) {
		
		CourseOffering returnCourseOffering = null;
		Iterator<CourseOffering> allOfferings = getAll();
		
		while (allOfferings.hasNext()) {
			CourseOffering thisOffering = allOfferings.next();
			
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
	public boolean contains(CourseOffering obj) {
		if (get(obj.getDepartment(), obj.getCourseNumber(), 
				obj.getSectionNumber()) == null) {
			return false;
		} else {
			return true;
		}
	}

}
