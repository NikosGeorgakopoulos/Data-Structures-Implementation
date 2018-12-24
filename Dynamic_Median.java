import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;


public class Dynamic_Median
{
 
public Dynamic_Median( String Path)
   {
	   Song Median;
	   
	   Path= EvaluatePath(Path); 
	  
 	 
 	 PriorityQueue<Song> MaxValues = new PriorityQueue<Song>(10, new ReverseObjectComparator<Song>("getLikes","getName"));
 	 PriorityQueue<Song> MinValues = new PriorityQueue<Song>(10, new ObjectComparator<Song>("getLikes","getName"));

 	try {
 		int activeCount = 0;
        BufferedReader in = new BufferedReader(new FileReader(Path));
        String str;
        while ((str = in.readLine()) != null) {            	
        	String [] values = str.split(" ");        	
        	Song s = ProcessLine(values);
        	InsertSong(s,MaxValues,MinValues);
        	rebalance(MaxValues,MinValues); 
        	Median = getMedian(MaxValues,MinValues);
        	activeCount++;
        	if (activeCount == 5) {
        		printMedian(Median);
        		activeCount = 0;
        	}
        	      	
	         }
        		in.close();
	            } 
    				
    				catch(Exception e) {
	                e.printStackTrace();
	            }   
     
   }

   

	private static void printMedian(Song median) {
	System.out.println("Median is " + median.getLikes() + " Achieved by: "+ median.getName());
	
}



	private static Song getMedian(PriorityQueue<Song> high, PriorityQueue<Song> low) {
		System.out.println(high.size());
		System.out.println(low.size());
		high.print();
		System.out.println("---------------------------------------");
		low.print();
		PriorityQueue<Song> larger = low.size() > high.size() ? low : high;
		PriorityQueue<Song> smaller = low.size() > high.size() ? high : low;
		
		if (larger.size() == smaller.size()) {
			try {
				return Integer.parseInt(low.Max().getClass().getDeclaredMethod("getLikes").invoke(low.Max()).toString()) >
				Integer.parseInt(high.Max().getClass().getDeclaredMethod("getLikes").invoke(high.Max()).toString()) ? low.Max() : high.Max();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return larger.Max();
		}
		return null;
}



	private static void rebalance(PriorityQueue<Song> high, PriorityQueue<Song> low) {
	PriorityQueue<Song> larger = low.size() > high.size() ? low : high;
	PriorityQueue<Song> smaller = low.size() > high.size() ? high : low;
	
	if (larger.size() - smaller.size() >= 2){
		smaller.insert(larger.getMax());
	}
}

	private static void InsertSong(Song s, PriorityQueue<Song> high, PriorityQueue<Song> low) {
	try {
		if (low.size() == 0 || s.getLikes() < Integer.parseInt(low.Max().getClass().getDeclaredMethod("getLikes").invoke(low.Max()).toString())) {
			low.insert(s);
		}
		else {
			high.insert(s);
		}
	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
			| SecurityException e) {
		
		e.printStackTrace();
	}
	
}

	private static String EvaluatePath(String path) {
	String prefix = path.substring(path.length() - 4);
	if(!prefix.equals(".txt")) return path +".txt";
	return path;
    }

	private static Song ProcessLine(String[] values) throws WrongInputException{
    try {
	int id = Integer.parseInt(values[0]);
	if (id < 1 || id > 9999) throw new WrongInputException("ID must be between 1-99");
	
	int likes = Integer.parseInt(values[values.length -1]);
	String title = "";
	for (int i = 1; i < values.length -1 ; i++ ) {
		title += values[i] + " ";
	}
	if (title.length() > 80) throw new WrongInputException("Title cannot be longer than 80 characters");
	return new Song (id,title,likes);
    }
   catch(WrongInputException e){
	   e.printStackTrace();
	   System.exit(1);
   }
	return null;
} 
} 


