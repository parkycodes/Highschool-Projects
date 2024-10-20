/*
 * Parker Zhang 
 * Turner pd 3
 * a class that adds, removes, and changes properties of arrays, can change elements in array and resize it, also accessing specific locations the client asks for
 */
import java.util.*;

public class IteratorArrayList<E> implements Iterable<E>{

	private E[] privArray;
	private int numElements;

	public IteratorArrayList() {
		
		privArray = (E[])new Object[0];
	}

	public IteratorArrayList(int length) {

		if (length < 0)
			throw new IllegalArgumentException("Bad Length");

		privArray = (E[])new Object[length];
	}

	public IteratorArrayList(IteratorArrayList other) {

		privArray = (E[])new Object[other.privArray.length];

		for (int i = 0; i < privArray.length; i++)
			privArray[i] = (E) other.privArray[i];

	}
	
	// resize your array
	private void resize() {

		// resize the length of array
		E[] temp =(E[])new Object[privArray.length + 10];

		for (int i = 0; i < privArray.length; i++) 
			temp[i] = this.privArray[i];
		
		this.privArray = temp;
	}

	// appends the element to the end of the list. Resizing if necessary
	public void add(Object element) {

		// checks if the objects array has reached a multiple of 10
		if (numElements % 10 == 0) {
			resize();
		}

		privArray[numElements] = (E)element;
		numElements++;
	}

	// inserts element at index. slides all elements from index on to the right by
	// one. resizes if necessary
	public void add(int index, E element) {

		if (index < 0 || index > numElements)
			throw new IllegalArgumentException("Not an element");

		if (numElements % 10 == 0)
			resize();

		// shift everything to the right of index up one
		for (int i = numElements; i > index; i--)
			privArray[i] = privArray[i - 1];

		privArray[index] = element;
		numElements++;
	}

	// removes value at index and shifts remaining elements down to the left.
	// return the removed element
	public E removeIndex(int index) {

		if (index < 0 || index > numElements)
			throw new IllegalArgumentException("Not an element");

		// removes element and swaps everything after down 1
		E temp = privArray[index];

		E[] tempArray = (E[])new Object[privArray.length];

		for (int i = 0; i < index; i++) 
			tempArray[i] = privArray[i];
		
		for (int i = index + 1; i < tempArray.length - 1; i++) 
			tempArray[i - 1] = privArray[i];
		
		numElements--;

		return temp;
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

	//returns index of first location of element. returns -1 if element isnt found
	public int indexOf(int element) {
		
		for (int i = 0; i < this.privArray.length; i++) {
			
			if (this.privArray[i].equals(element)) 
				return i;
			
		}
		return -1;
	}

	//returns true if list is currently empty
	public boolean isEmpty() {
		
		if (numElements == 0) 
			return true;
		
		return false;
	}

	//changes the element at a given position returns original value
	public E set(int index, E element) {
		
		if (index < 0 || index > numElements) 
			throw new IllegalArgumentException("Not an index");
		
		E temp = this.privArray[index];

		this.privArray[index] = element;

		privArray[index] = element;

		return temp;
	}

	//returns the total number of active elements
	public int size() {
		
		return this.privArray.length;
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
