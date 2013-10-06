package com.cs388f13p2.model.repositories;

import java.util.Iterator;

import com.cs388f13p2.database.StudentDAO;
import com.cs388f13p2.model.person.Student;

public class StudentRepository implements MutableRepository<Student> {
	
	private StudentDAO studentDAO;
	
	private static class SingletonHolder { 
		public static final StudentRepository INSTANCE = new StudentRepository();
	}

	public static StudentRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private StudentRepository() {
		studentDAO = new StudentDAO();
	}

	public void add(final Student obj) {
		try {
			studentDAO.addStudent(obj);
	    } catch (Exception se) {
	      System.err.println("StudentRepository: Threw a Exception retrieving student.");
	      System.err.println(se.getMessage());
	    }
	}

	public Student findById(final int id) {
		try {
			Student student = studentDAO.findStudentById(id);
	    	return student;
	    } catch (Exception se) {
	      System.err.println("StudentRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	public Iterator<Student> getAll() {
		return studentDAO.getAllStudents();
	}

	public void update(final int id, final Student newObj) {
		try {
			studentDAO.updateStudent(id, newObj);
	    } catch (Exception se) {
	      System.err.println("StudentRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
	}

	public boolean delete(final int id) {
		try {
			return studentDAO.deleteStudent(id);
	    } catch (Exception se) {
	      System.err.println("StudentRepository: Threw a Exception retrieving customer.");
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

}
