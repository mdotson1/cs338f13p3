package model.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.PersonManager;

public class ProfessorManager implements PersonManager<Professor> {
	
	private List<Professor> professors;
	
	private static class SingletonHolder { 
        public static final ProfessorManager INSTANCE = new ProfessorManager();
	}
	
	public static ProfessorManager getInstance() {
		
		return SingletonHolder.INSTANCE;
	}
	
	private ProfessorManager() {
		professors = new ArrayList<Professor>();
	}
	
	public void add(Professor prof) {
		professors.add(prof);
	}
	
	public void modify(Professor oldProf, Professor newProf) {
		int index = professors.indexOf(oldProf);
		professors.set(index, newProf);
	}
	
	public boolean delete(Professor prof) {
		return professors.remove(prof);
	}
	
	public Professor get(int ssn) {
		
		Professor returnProf = null;
		Iterator<Professor> allProfs = getAll();
		
		while (allProfs.hasNext()) {
			Professor thisProf = allProfs.next();
			if (thisProf.getSsn() == ssn)
			{
				returnProf = thisProf;
				break;
			}
		}
		return returnProf;
	}

	@Override
	public Iterator<Professor> getAll() {
		return professors.iterator();
	}

}
