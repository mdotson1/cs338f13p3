package main.java.com.cs388f13p1;

public interface PersonManager<E> extends Manager<E> {
	
	public E get(int ssn);

}
