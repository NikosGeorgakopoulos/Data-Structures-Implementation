
import java.util.Comparator;

final class IntegerComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1.intValue() < o2.intValue()){
			return -1;
		}
		else if(o1.intValue() == o2.intValue()){
			return 0;
		}
		else{
			return 1;
		}
	}
	
}
