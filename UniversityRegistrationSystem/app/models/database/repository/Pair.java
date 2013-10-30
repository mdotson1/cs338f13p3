package models.database.repository;

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
	
	public void setFirst(final E first){
		this.first = first;
	}
	
	public void setSecond(final F second){
		this.second = second;
	}
	
	public boolean compare(final E first, final F second){
		return (this.first == first) && (this.second == second);
	}
}
