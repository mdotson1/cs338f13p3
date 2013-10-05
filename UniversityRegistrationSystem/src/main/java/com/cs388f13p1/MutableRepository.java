package com.cs388f13p1;

public interface MutableRepository<E> extends Repository<E> {

	public void update(final int id, final E newObj);
	
	public boolean delete(final int id);
}
