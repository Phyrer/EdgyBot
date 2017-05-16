package discordbot.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularBuffer<T> implements Iterable<T>{
	//=== ATTRIBUTES ===
	private T[] list;
	private final int size;
	private int start;
	private int end;
	
	//=== CONSTRUCTORS ===
	@SuppressWarnings("unchecked")
	public CircularBuffer(int size){
		this.size = size;
		list = (T[]) new Object[size];
		start = 0;
		end = 0;
	}
	
	//=== ITERATOR ===
	private class CircularIterator implements Iterator<T>{
		private int cursor;
		public CircularIterator(){
			cursor = -1;
		}
		
		public boolean hasNext(){
			if (cursor == -1 && start == end) return false;	//empty list
			if (cursor == -1 && start != end) return true;	//cursor not set to head yet
			else if (list[cursor] != null && cursor + 1 != start) return true;
			else return false;
		}
		
		public T next(){
			if (!this.hasNext()){
				throw new NoSuchElementException();
			}
			if (cursor == -1) cursor = start;
			else cursor = (cursor + 1) % size;
			return list[cursor];
		}
	}
	@Override
	public Iterator<T> iterator() {
		return new CircularIterator();
	}
	
	//=== METHODS ===
	/**
	 * Pushes a new object on top of the stack
	 * @param obj
	 */
	public void push(T obj){
		list[end] = obj;
		end = (end + 1) % size;
		if (end == start)
			start = (start + 1) & size;
	}
	
	/**
	 * @return The last element on the stack
	 */
	public T pop(){
		if (start != end){
			end = (end - 1 + size) % size;
			return list[end];
		}
		return null;
	}
	
	public T get(int index){
		if (index >= size || index < 0) return null;
		return list[start + index];
	}
	
	public boolean contains(Object obj){
		for (T t: list){
			if (t == obj || t.equals(obj)) return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String res = "(";
		for (T t: list){
			res += t + ",";
		}
		return res.substring(0,res.length()-1) + ")";
	}
	
}
