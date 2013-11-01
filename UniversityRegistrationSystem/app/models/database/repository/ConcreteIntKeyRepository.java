package models.database.repository;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

public interface ConcreteIntKeyRepository<E> {

	// C
	public int add(final E obj) throws SQLException;

	// R
	public E findById(final int id) throws SQLException;
	
	public Iterator<E> getAll() throws SQLException;
	
	
	
	// ### Update is defined in each specific repository, as it depends
	// on what needs updating.

	// D
	public boolean delete(final int id) throws SQLException;

	public boolean contains(final int id) throws SQLException;
}
