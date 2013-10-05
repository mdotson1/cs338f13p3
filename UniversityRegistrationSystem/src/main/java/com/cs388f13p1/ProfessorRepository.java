package com.cs388f13p1;

import java.util.Iterator;

public class ProfessorRepository implements MutableRepository<Professor> {
	
	private ProfessorDAO professorDAO = new ProfessorDAO();

	private static class SingletonHolder { 
		public static final ProfessorRepository INSTANCE = new ProfessorRepository();
	}

	public static ProfessorRepository getInstance() {

		return SingletonHolder.INSTANCE;
	}

	private ProfessorRepository() {
		studentDAO = new ProfessorDAO();
	}
	
	public void add(final Professor obj) {
		try {
			professorDAO.addProfessor(obj);
	    } catch (Exception se) {
	      System.err.println("ProfessorRepository: Threw a Exception retrieving student.");
	      System.err.println(se.getMessage());
	    }
	}

	public Professor findById(final int id) {
		try {
			Professor professor = professorDAO.findProfessorById(id);
	    	return professor;
	    } catch (Exception se) {
	      System.err.println("ProfessorRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}

	public Iterator<Professor> getAll() {
		return professorDAO.getAllProfessors();
	}
	
	public void update(final int id, final Professor newObj) {
		try {
			professorDAO.updateProfessor(id, newObj);
	    } catch (Exception se) {
	      System.err.println("ProfessorRepository: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
	}

	public boolean delete(final Professor obj) {
		try {
			return professorDAO.deleteProfessor(obj);
	    } catch (Exception se) {
	      System.err.println("ProfessorRepository: Threw a Exception retrieving customer.");
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
