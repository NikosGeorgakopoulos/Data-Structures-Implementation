public class HeapSort<T> 
{ 
	
	protected ObjectComparator<T> cmp ;
	private int range;
    public HeapSort(int range,String PrimaryValue, String SecondaryValue) {
		cmp = new ObjectComparator<T>(PrimaryValue, SecondaryValue);
		this.range = range;
	}

	public void sort(T arr[]) 
    {                     
                 
        // Build heap (rearrange array) 
        for (int i = range / 2 - 1; i >= 0; i--) 
            heapify(arr, range, i); 
  
        // One by one extract an element from heap 
        for (int i=range-1; i>=0; i--) 
        { 
            // Move current root to end 
            T temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
    } 
  
    void heapify(T arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && cmp.compare(arr[l], arr[largest]) < 0) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && cmp.compare(arr[r], arr[largest]) < 0) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            T swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    }

} 