import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {
	
	int size;
	ArrayList<T> myS = new ArrayList<T>();
	int items = 0;
	
	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	public NotationStack(int size) {
		this.size = size;
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		if(myS.isEmpty())
			return true;
		else 
			return false;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		//if the size of the queue is not the same as the full size
		return size == items;
	}
	

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	public T pop() throws StackUnderflowException{		
		//throws exception if stack is empty
		if(isEmpty())
			throw new StackUnderflowException("Stack is empty");
		else {
			T stackTop = top();
			myS.remove(myS.size()-1);
			items--;
			return stackTop;
		}
		
	}	
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	public T top() throws StackUnderflowException{
		//throws exception if queue is empty
		if(isEmpty())
			throw new StackUnderflowException("Stack is empty");
		else //return the top element
			return myS.get(myS.size()-1);		
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return myS.size();
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	public boolean push(T e) throws StackOverflowException{
		//throw exception if queue is full
		if(isFull())
			throw new StackOverflowException("Stack is full");
		else {
			//add new element to the end of the queue
			myS.add(e);
			items++;
		}
	
		return true;
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String stackString = "";
		for(int i = 0; i < myS.size(); i++) {
			stackString += myS.get(i);
		}
		
		return stackString;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter) {
		String stackString = "";
		for(int i = 0; i < myS.size(); i++) {
			stackString += myS.get(i) + delimiter;
		}
		
		stackString = stackString.substring(0, stackString.length() - delimiter.length());
		return stackString; 
	}
	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyList = new ArrayList<T>();
		for(T copy: list) {
			copyList.add(copy);
		}
		
		for(T c: copyList) {
			try {
				push(c);
			}
			catch(StackOverflowException e) {
				list.remove(list.size());
			}
		}
		
	}
 
}
