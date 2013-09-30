package main.java.com.cs388f13p1;

import java.util.Iterator;

public interface Manager<E> {

	public boolean contains(E obj);
	
	public boolean delete(E obj);
	
	public Iterator<E> getAll();
}
