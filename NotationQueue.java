import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T>{
	
	int size;
	ArrayList<T>myQ;
	int items = 0;
	
	public NotationQueue(int size) {
		myQ = new ArrayList<T>(); //creates a new queue arraylist
		this.size = size; //sets the queue size
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		if (myQ.isEmpty())
			return true;
		else 
			return false;
	}

	/**
	 * Determines of the Queue is empty
	 * @return
	 */
	public boolean isFull() {
		//if the size of the queue is not the same as the full size
		if(!(myQ.size() == size))
			return false;
		else
			return true;		
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	public T dequeue() throws QueueUnderflowException{
		//throws exception if queue is empty
		if(isEmpty())
			throw new QueueUnderflowException("Queue is empty");
		
		//deletes and returns the first element 
		T first = myQ.get(0);
		myQ.remove(0);
		items--;
		return first;
		
	}

	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		return items;
		
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	public boolean enqueue(T e) throws QueueOverflowException{
		//throw exception if queue is full
		if(isFull())
		throw new QueueOverflowException("Queue is full");
		
		//add new element to the end of the queue
		myQ.add(e);
		items++;
		return true;
	}
	
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String queueString = "";
		for(int i = 0; i < myQ.size(); i++) {
			queueString += myQ.get(i);
		}
		
		return queueString;
		
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter) {
		String queueString = "";
		
		for(int i = 0; i < myQ.size(); i++) {
			queueString += myQ.get(i) + delimiter;
		}
		
		queueString = queueString.substring(0, queueString.length() - delimiter.length());	
		return queueString;
	}
	
	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> copyList = new ArrayList<T>();
		for(T copy: list) {
			copyList.add(copy);
		}
		
		for(T c: copyList) {
			try {
				enqueue(c);
			}
			catch(QueueOverflowException e) {
				list.remove(list.size());
			}
		}
	}
}
