package model.manager;

public interface PersonManager<E> extends Manager<E> {
	
	public E get(int ssn);

}
