package model.manager;

import java.util.Iterator;

public interface Manager<E> {

	public boolean contains(E obj);
	
	public void modify(E oldObj, E newObj);
	
	public boolean delete(E obj);
	
	public Iterator<E> getAll();
}
