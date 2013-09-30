package com.cs388f13p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProfessorManager implements PersonManager<Professor> {
	
	final private List<Professor> professors;
	
	private static class SingletonHolder { 
        public static final ProfessorManager INSTANCE = new ProfessorManager();
	}
	
	public static ProfessorManager getInstance() {
		
		return SingletonHolder.INSTANCE;
	}
	
	private ProfessorManager() {
		professors = new ArrayList<Professor>();
	}
	
	public void modify(final Professor oldProf, final Professor newProf) {
		final int index = professors.indexOf(oldProf);
		professors.set(index, newProf);
	}
	
	public boolean delete(Professor prof) {
		return professors.remove(prof);
	}
	
	public Professor get(final int id) {
		
		Professor returnProf = null;
		final Iterator<Professor> allProfs = getAll();
		
		Professor thisProf;
		while (allProfs.hasNext()) {
			thisProf = allProfs.next();
			if (thisProf.getId() == id)
			{
				returnProf = thisProf;
				break;
			}
		}
		return returnProf;
	}

	//@Override
	public Iterator<Professor> getAll() {
		return professors.iterator();
	}

	//@Override
	public boolean contains(final Professor obj) {
		if (get(obj.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}

	public Professor add(final Address homeAddr, final Address workAddr,
			final PhoneNumbers phones, final String fName, final String lName,
			final Date dob, final int id, final String department) {
		
		final Professor created = new Professor(homeAddr, workAddr, phones, 
				fName, lName, id, dob, department);
		
		if (contains(created)) {
			return null;
		} else {
			professors.add(created);
			return created;
		}
	}

}
