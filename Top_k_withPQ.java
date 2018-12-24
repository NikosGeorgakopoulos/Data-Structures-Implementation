import java.io.BufferedReader;
import java.io.FileReader;

public class Top_k_withPQ
{
  
public Top_k_withPQ( String Path, int k )
   {
	   
	   
	   Path= EvaluatePath(Path);  	  	   	
 	 
 	
 	 PriorityQueue<Song> Kqueue = new PriorityQueue<Song>(k,10, new ObjectComparator<Song>("getLikes","getName"));

 	try {
 		
        BufferedReader in = new BufferedReader(new FileReader(Path));
        String str;
        while ((str = in.readLine()) != null) {            	
        	String [] values = str.split(" ");        	
        	Song s = ProcessLine(values);        	
        	Kqueue.insert(s);        	
            }
        		in.close();
	            } 
    				
    				catch(Exception e) {
	                e.printStackTrace();
	            }
 	
 	 
    
    System.out.println("The top " + k + " Songs on the list are :");
    Kqueue.clear();   
    
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


