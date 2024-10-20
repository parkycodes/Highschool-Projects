import java.util.NoSuchElementException;

/*
 * Parker Zhang
 * Pd 7 turner
 * 8/28
 * linked list that can be iterated forward and backwards
 */

public class ParkerZhang_DoublyLinkedList<E> {

	private ListNode front;
	private ListNode end;
	private int numElements;
	
	//if list is empty return true
	public boolean isEmpty(){
		
		if(front == null) 
			return true;
		
		return false;
	}
	
	//adds to the fronts and creates all connections if list is not empty
	public void addFront(E data) {
		
		if(isEmpty()) {
			
			ListNode temp = new ListNode(null, data, null);
			front = temp;
			end = temp;
		}
		else {
			
			ListNode temp = new ListNode(null, data, front);
			front.previous = temp;
			front = temp;
		}
		
		numElements++;
	}
	
	//adds to the end of the list and adds all connections
	public void addLast(E data) {
		
		if(isEmpty()) 
			addFront(data);
		
		else {
			ListNode temp = new ListNode(end, data, null);
			end.next = temp;
			end = temp;
		}
		
		numElements++;
		
	}
	
	public void removeFirst() {
		
		if(isEmpty()) 
			throw new IllegalArgumentException("empty list");
		
		else {
			//cuts out the front and nullifies it
			front=front.next;
			front.previous=null;
		}
		
		numElements--;
	}
	
	public void removeLast() {
		
		if(isEmpty()) 
			throw new IllegalArgumentException("empty list");
		
		end = end.previous;
		end.next=null;
		
		numElements--;
	}
	
	public void addAfter(int index, E data) {
		
		//check if index is in bounds of list
		if(index<0 || index>=numElements || isEmpty())
			throw new IndexOutOfBoundsException();
		
		if(index == numElements-1) 
			addLast(data);
		
		else {
			
			int loc = 0;
			ListNode current = front;
			
			while(loc != index) {
				loc++;
				current = current.next;
			}
			
			//save the existing listnode after index
			ListNode temp = current.next;
			
			ListNode toAdd = new ListNode (current, data, temp);
			
			//creates all connections
			current.next=toAdd;
			temp.previous = toAdd;
			
		}
		
		numElements++;
	}
	
	//removes the first instance of the data
	public void remove(E data) {
		
		ListNode current = front;
		int counter = 0;
		
		while(current!=null) {
			
			if(current.data == data) {
				
				if(counter==0) 
					removeFirst();
				
				else if(counter==numElements-1)
					removeLast();
				
				//if not first or last
				else {
					
					//grab nodes before and after
					ListNode before = current.previous;
					ListNode after = current.next;
					
					//change the adjacent nodes to cut out current node & nullify
					before.next= after;
					after.previous = before;
					current.previous = null;
					current.next = null;
					
				}
				numElements--;
				return;
			}
			
			current = current.next;
			counter++;
		}
		
		//check if counter is valid and if first or last
		if(current==null||isEmpty()) 
			throw new NoSuchElementException();

	}
	
	
	public int size() {
		return numElements;
	}
	
	//loop through list and once found returns data
	public E get(int index) {
		
		if(index <0 || index>=numElements ||isEmpty()) 
			throw new IndexOutOfBoundsException();
		
		ListNode current = front;
		int counter = 0;
		
		//find listnode at index
		while(counter!=index) {
			
			counter++;
			current=current.next;
		}
		
		return current.data;
	}
	
	//loops through the list to get to the index and set the new data
	public E set(int index,E data) {
		
		if(index <0 || index>=numElements ||isEmpty()) 
			throw new IndexOutOfBoundsException();
		
		ListNode current = front;
		int counter = 0;
		
		while(counter!=index) {
			counter++;
			current=current.next;
		}
		
		E temp = current.data;
		current.data = data;
		
		return temp;
	}
	
	public String toString() {
		
		String toReturn = "";
		ListNode current = front;
		
		while(current!=null) {
			
			toReturn+=current.data + ", ";
			current = current.next;
		}
		
		return toReturn;
	}
	
	
	public class ListNode {
		
		private ListNode previous;
		private E data;
		private ListNode next;
		
			public ListNode(ListNode p, E d, ListNode n) {
				previous = p;
				data = d;
				next = n;
			}
	}
	
	public static void main(String[] args) {
	
		 ParkerZhang_DoublyLinkedList ints = new ParkerZhang_DoublyLinkedList<Integer>();
		 
		 
		 ints.addFront(2);
		 ints.addFront(1);
		 ints.addLast(3);
		 
		 
		 System.out.println(ints.toString());
		 
		 ints.remove(1);
		 
		 
		 System.out.println(ints.toString());
		 System.out.println(ints.get(0));

		 
	}
	
}





