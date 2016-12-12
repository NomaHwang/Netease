import java.util.*;

class Pair<T extends Comparable<T>,U>{
	private T first;
	private U second;
	
	public Pair(T first, U second){
		this.first = first;
		this.second = second;
	}
	
	public T getFirst(){
		return first;
	}
	
	public U getSecond(){
		return second;
	}
	
	public void setFirst(T n){
		first = n;
	}
	
	public void setSecond(U n){
		second = n;
	}

	public int compareTo(Pair<T, U> other){
		return first.compareTo(other.getFirst());
	}
}

public class GenericProg {
	public static <T extends Comparable<T>> T getMin(T[] nums){
		if(nums == null || nums.length == 0){
			return null;
		}
		T min = nums[0];
		for(int i = 1; i < nums.length; ++i){
			if(min.compareTo(nums[i]) < 0){
				min = nums[i];
			}
		}
		return min;
	}
	
	public static void main(String[] args){
		LinkedList<Pair<Integer, String>> llist = new LinkedList<Pair<Integer, String>>();
		llist.add(new Pair<Integer, String>(2,"hello"));
		llist.add(new Pair<Integer, String>(2,"world"));
		llist.add(new Pair<Integer, String>(3, "hi"));
		
		Iterator<Pair<Integer, String>> iter = llist.iterator();
		
		System.out.print(iter.next().getSecond());
	
	}
}
