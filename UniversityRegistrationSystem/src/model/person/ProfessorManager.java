package model.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.manager.PersonManager;
import model.time.Date;

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
	
	public Professor get(int id) {
		
		Professor returnProf = null;
		Iterator<Professor> allProfs = getAll();
		
		while (allProfs.hasNext()) {
			Professor thisProf = allProfs.next();
			if (thisProf.getId() == id)
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

	@Override
	public boolean contains(Professor obj) {
		if (get(obj.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean add(Address homeAddr, Address workAddr, PhoneNumbers phones,
			String fName, String lName, Date dob, int id, String department) {
		
		Professor created = new Professor(homeAddr, workAddr, phones, fName, lName, id, dob, department);
		
		if (contains(created)) {
			return false;
		} else {
			professors.add(created);
			return true;
		}
	}

}
