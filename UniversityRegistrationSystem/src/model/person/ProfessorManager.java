package model.person;

import java.util.ArrayList;
import java.util.List;

public class ProfessorManager {
	
	private List<Professor> professors;
	
	public ProfessorManager() {
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
		for (int i = 0; i < professors.size(); i++) {
			Professor thisProf = professors.get(i);
			if (thisProf.getSsn() == ssn)
			{
				returnProf = thisProf;
			}
		}
		return returnProf;
	}

}
