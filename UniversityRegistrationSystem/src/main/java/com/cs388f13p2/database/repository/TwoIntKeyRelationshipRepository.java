package com.cs388f13p2.database.repository;

import java.sql.SQLException;

public interface TwoIntKeyRelationshipRepository<First,Second> {

	// C
	public void add(final int oneId, final int manyId) throws SQLException;
	
	// ### READ and UPDATE defined in implementations

	// D
	public boolean delete(final int oneId, final int manyId) throws SQLException;
}
