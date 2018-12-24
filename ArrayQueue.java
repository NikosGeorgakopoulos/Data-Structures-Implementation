
public class ArrayQueue<T> implements Queue<T> {

	
	private T[] theArray;	
	private int currentSize;	
	private int front;	
	private int back;

	private static final int DEFAULT_CAPACITY = 10;

	
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		setTheArray((T[]) new Object[DEFAULT_CAPACITY]);
		currentSize = 0;
		front = 0;
		back = -1;
	}

	
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	
	public T dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("ArrayQueue dequeue error");
		T returnValue = getTheArray()[front];
		front = increment(front);
		currentSize--;
		return returnValue;
	}

	
	public void enqueue(T x) {
		if (currentSize == getTheArray().length)
			doubleQueue();
		back = increment(back);
		getTheArray()[back] = x;
		currentSize++;
	}

	
	private int increment(int x) {
		if (++x == getTheArray().length)
			x = 0;
		return x;
	}

	
	@SuppressWarnings("unchecked")
	private void doubleQueue() {
		T[] newArray;
		newArray = (T [] ) new Object[getTheArray().length * 2];
		
		for (int i = 0; i < currentSize; i++, front = increment(front))
			newArray[i] = getTheArray()[front];
		setTheArray(newArray);
		front = 0;
		back = currentSize - 1;
	}

	public void print() {
		
		
		if (isEmpty()) {
			//System.out.println("Empty queue\n");
			return;
		}

		System.out.println("The queue is: ");
		for (int i = front; i != back; i = increment(i)) {			
				
			System.out.println(getTheArray()[i]);
			
		}
		System.out.print(getTheArray()[back] + "\n");
	}


	public int getsize() {
		return currentSize;
	}


	public T[] getTheArray() {
		return theArray;
	}


	public void setTheArray(T[] theArray) {
		this.theArray = theArray;
	}


	
}
