import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;


public class PriorityQueue<T> {
    /**
     * Array based heap representation
     */
    private T[] heap;
    /**
     * The number of objects in the heap
     */
    private int size;
    /**
     * Comparator.
     */
    protected Comparator<T> cmp;
    
    private static double perc = 0.75;
    
    private int k;
    
    private boolean LimitedtoK = true;
    
    /**
     * Creates heap with a given capacity and comparator.
     * param capacity The capacity of the heap being created.
     * param cmp The comparator that will be used.
     */
    
    public PriorityQueue(int capacity, Comparator<T> cmp) {
    	 this(10,capacity,cmp);
    	 LimitedtoK = false;
    }
    
    
    @SuppressWarnings("unchecked")
	public PriorityQueue(int k,int capacity, Comparator<T> cmp) {
        if (capacity < 1) throw new IllegalArgumentException();
        
        this.heap = (T []) new Object[capacity+1];
        this.size = 0;
        this.cmp = cmp;
        this.k = k;
    }
    /**
     * Inserts an object in this heap.
     * throws IllegalStateException if heap capacity is exceeded.
     * param object The object to insert.
     */
    public void insert(T object) {
        // Ensure object is not null
        if (object == null) throw new IllegalArgumentException();
        // Check available space       
        if (size > heap.length * perc) resize(size * 2);
        // Place object at the next available position
        heap[++size] = object;
        // Let the newly added object swim
        
        swim(size);
        
        if (size > k && LimitedtoK) removeIndex(k + 1);
        	
        	
        
    }
    
    
	@SuppressWarnings("unchecked")
	public void resize(int capacity) {    	
    	T[] temp = heap.clone();
    	
    	heap = (T []) new Object[capacity+1];
    	
    	for (int i=1; i<=size ; i++){
    		
        	heap[i] = temp[i];
        }
    }
	
	public T removeIndex(int index) {  		
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();    
                    
			// Keep a reference to the root object
			T object = heap[index];
			// Replace root object with the one at rightmost leaf
			if (size > 1) heap[index] = heap[size];
			// Dispose the rightmost leaf
			heap[size--] = null;

			return object;  
            }
    
    public T remove(int id) {    	
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();
        
        for ( int i = 1; i <= size; i++) {
        
        try {
			if (Integer.parseInt(heap[i].getClass().getDeclaredMethod("getId").invoke(heap[i]).toString()) == id) { 
			// Keep a reference to the root object
			T object = heap[i];
			// Replace root object with the one at rightmost leaf
			if (size > 1) heap[i] = heap[size];
			// Dispose the rightmost leaf
			heap[size--] = null;
			
			T element = heap[i];
			// Sink the new root element
			sink(i);
			
			// If the object didn't sink , try swimming
			if (cmp.compare(heap[i],element) == 0)  swim(i);
      
			// Return the object removed
			return object;
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			
			e.printStackTrace();
		}
        }
        return null;
    }
    
    public T Max() {
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();
        // Keep a reference to the root object
        return heap[1];
        
        
    }
    /**
     * Removes the object at the root of this heap.
     * throws IllegalStateException if heap is empty.
     * return The object removed.
     */
    public T getMax() {
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();
        // Keep a reference to the root object
        T object = heap[1];
        // Replace root object with the one at rightmost leaf
        if (size > 1) heap[1] = heap[size];
        // Dispose the rightmost leaf
        heap[size--] = null;
        // Sink the new root element
        sink(1);
        // Return the object removed
        return object;
    }
    /**
     * Shift up.
     */
    private void swim(int i) {
    	
        while (i > 1) {  //if i root (i==1) return
            int p = i/2;  //find parent
            int result = cmp.compare(heap[i], heap[p]);  //compare parent with child
            if (result <= 0) return;    //if child <= parent return
            swap(i, p);                 //else swap and i=p
            i = p;
        }
    
    }
    /**
     * Shift down.
     */
    private void sink(int i){
        int left = 2*i, right = left+1, max = left;
        // If 2*i >= size, node i is a leaf
        while (left <= size) {
            // Determine the largest children of node i
            if (right <= size) {
                max = cmp.compare(heap[left], heap[right]) < 0 ? right : left;
            }
            // If the heap condition holds, stop. Else swap and go on.
            if (cmp.compare(heap[i], heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }
    
    /**
     * Interchanges two array elements.
     */
    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    public void print() {
        for (int i=1; i<=size; i++){
        	
            System.out.println(heap[i]);
        }
        System.out.println();
    }
    boolean empty(){
        return size == 0;
    }


	public void printMedian() {
		if (size%2 == 0) {
			try {
				System.out.println("Median is " + ((cmp.compare(heap[size/2], heap[(size/2)+1]) == 1)
				?heap[size/2].getClass().getDeclaredMethod("getLikes").invoke(heap[size/2]) :
				heap[size/2 +1].getClass().getDeclaredMethod("getLikes").invoke(heap[size/2+1])
					) + " Achieved by : " + 
					((cmp.compare(heap[size/2], heap[(size/2)+1]) == 1)
							?heap[size/2].getClass().getDeclaredMethod("getName").invoke(heap[size/2]) :
								heap[size/2 +1].getClass().getDeclaredMethod("getName").invoke(heap[size/2+1])
									)			
				
						);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		try {
			System.out.println("Median is " + 
				heap[(size+1)/2].getClass().getDeclaredMethod("getLikes").invoke(heap[(size+1)/2]) +
				" Achieved by : " + 
				heap[(size+1)/2].getClass().getDeclaredMethod("getName").invoke(heap[(size+1)/2]));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}


	public void clear() {
		
        	
          while(!empty()) System.out.println(getMax());
              
		
	}


	public int size() {
		return size;
	}

}
