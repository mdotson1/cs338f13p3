package com.cs388f13p2.database.repository;

import java.sql.SQLException;

public interface ConcreteIntKeyRepository<E> {

	// C
	public void add(final E obj) throws SQLException;

	// R
	public E findById(final int id) throws SQLException;
	
	// ### Update is defined in each specific repository, as it depends
	// on what needs updating.

	// D
	public boolean delete(final int id) throws SQLException;

	public boolean contains(final int id) throws SQLException;
}
