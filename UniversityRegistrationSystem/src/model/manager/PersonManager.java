package model.manager;

import model.person.Address;
import model.person.PhoneNumbers;
import model.time.Date;

public interface PersonManager<E> extends Manager<E> {
	
	public E get(int ssn);

}
