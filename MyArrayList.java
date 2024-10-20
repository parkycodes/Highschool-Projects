/*
 * Parker Zhang 
 * Turner pd 3
 * a class that adds, removes, and changes properties of arrays, can change elements in array and resize it, also accessing specific locations the client asks for
 */
import java.util.*;

public class MyArrayList<E> implements Iterable<E>{

	private E[] privArray;
	private int numElements;

	public MyArrayList() {
		
		privArray = (E[])new Object[0];
	}

	public MyArrayList(int length) {

		if (length < 0)
			throw new IllegalArgumentException("Bad Length");

		privArray = (E[])new Object[length];
	}

	public MyArrayList(MyArrayList other) {

		privArray = (E[])new Object[other.privArray.length];

		for (int i = 0; i < privArray.length; i++)
			privArray[i] = (E) other.privArray[i];

	}

	// removes the first instance of element and slides elements down left. returns
	// true if element is found and removed
	public boolean removeElement(int element) {

		for (int i = 0; i < this.privArray.length; i++) {
			
			// check every element in array to see if it equals the parameter
			if (privArray[i].equals(element)) {

				E[] tempArray = (E[])new Object[privArray.length];

				// copies everything except element
				for (int c = 0; c < i; c++) 
					tempArray[c] = privArray[c];
				
				for (int c = i + 1; c < tempArray.length - 1; c++) 
					tempArray[c - 1] = privArray[c];
				
				numElements--;

				return true;

			}
		}
		return false;
	}
	
	//returns the element located at the index
	public E get(int index) {
		
		if (index < 0 || index > numElements) 
			throw new IllegalArgumentException("Not an index");
		
		return privArray[index];
	}

	public Iterator<E> iterator(){
		
		return new Iterator<E>() {
			int nextLoc = 0;
			boolean removeOK = false;
			
			public boolean hasNext() {
				
				if(nextLoc <numElements)
					return true;
				
				return false;
				
			}
			
			public E next() {
				
				if(!hasNext()) 
					throw new NoSuchElementException();

				nextLoc++;
				removeOK = true;
				return get(nextLoc+1);
			}
			
			public void remove() {
				
				if(removeOK) 
					removeElement(nextLoc-1);
				
				else 
					throw new IllegalStateException();

			}
			
		};
		
	}

}
