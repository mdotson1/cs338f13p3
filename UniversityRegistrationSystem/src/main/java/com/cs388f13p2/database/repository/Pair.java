package com.cs388f13p2.database.repository;

public class Pair<E,F> {
	
	private E first;
	private F second;
	
	public Pair(final E first, final F second) {
		this.first = first;
		this.second = second;
	}

	public E first() {
		return first;
	}
	
	public F second() {
		return second;
	}
}
