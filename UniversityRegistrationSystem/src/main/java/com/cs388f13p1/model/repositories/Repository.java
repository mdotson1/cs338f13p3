package com.cs388f13p1.model.repositories;

import java.util.Iterator;


public interface Repository<E> {
	
	// C
	public void add(final E obj);
	
	// R
	public E findById(final int id);
	
	public Iterator<E> getAll();
	
	// #### For update ability, extend MutableRepository ####
	
	// #### For delete ability, extend MutableRepository ####
	
	public boolean contains(final int id);
	
	
}
