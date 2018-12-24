import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

 final class ObjectComparator<T> implements Comparator<T> {

	private String PrimaryValue,SecondaryValue;
	
	public ObjectComparator(String PrimaryValue, String SecondaryValue) {
		this.PrimaryValue = PrimaryValue;
		this.SecondaryValue = SecondaryValue;
	}
	@Override
	public int compare(T o1 , T o2) {
		if(getPrimaryValue(o1) < getPrimaryValue(o2)){
			return -1;
		}
		else if(getPrimaryValue(o1) == getPrimaryValue(o2)) {
		return getSecondayValue(o1).compareTo(getSecondayValue(o2)) * -1;
		}
		else {
		return 1;
		}
		
	}
	
		private int getPrimaryValue(T t) {
		
		try {
			return Integer.parseInt(t.getClass().getDeclaredMethod(PrimaryValue).invoke(t).toString());			
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			
			e.printStackTrace();
		}
		return -1; 
	}
    	
    	private String getSecondayValue(T t) {
    		
    		try {
    			return t.getClass().getDeclaredMethod(SecondaryValue).invoke(t).toString();			
    			
    		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
    				| SecurityException e) {
    			
    			e.printStackTrace();
    		}
    		return " "; 
    	}
	
	
}
