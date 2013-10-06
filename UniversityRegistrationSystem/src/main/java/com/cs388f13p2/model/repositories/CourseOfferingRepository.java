package com.cs388f13p2.model.repositories;

import java.util.Iterator;

import com.cs388f13p2.database.CourseOfferingDAO;
import com.cs388f13p2.model.course.CourseOffering;
import com.cs388f13p2.model.course.CourseOffering.Season;

public class CourseOfferingRepository implements MutableRepository<CourseOffering> {
	
	private CourseOfferingDAO courseOfferingDAO = new CourseOfferingDAO();

	private static class SingletonHolder { 
		public static final CourseOfferingRepository INSTANCE = new CourseOfferingRepository();
	}

	public static CourseOfferingRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private CourseOfferingRepository() {
		courseOfferingDAO = new CourseOfferingDAO();
	}
	
	public void add(final CourseOffering obj) {
		try {
			courseOfferingDAO.addCourseOffering(obj);
	    } catch (Exception se) {
	      System.err.println("CourseOfferingRepository: Threw a Exception retrieving student.");
	      System.err.println(se.getMessage());
	    }
	}

	public CourseOffering findById(final int id) {
		try {
			return courseOfferingDAO.findCourseOfferingById(id);
	    } catch (Exception se) {
	      System.err.println("CourseOfferingRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	public Iterator<CourseOffering> getAll() {
		return courseOfferingDAO.getAllCourseOfferings();
	}

	public void update(final int id, final CourseOffering newObj) {
		try {
			courseOfferingDAO.updateCourseOffering(id, newObj);
	    } catch (Exception se) {
	      System.err.println("CourseOfferingRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
	}

	public boolean delete(final int id) {
		try {
			return courseOfferingDAO.deleteCourseOffering(id);
	    } catch (Exception se) {
	      System.err.println("CourseOfferingRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return false;
	}

	public boolean contains(final int id) {
		if (findById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	public Iterator<CourseOffering> findAllCoursesBySemester(Season season,
			short year) {
		try {
			return courseOfferingDAO.findAllCoursesBySemester(season, year);
	    } catch (Exception se) {
	      System.err.println("CourseOfferingRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}

}
