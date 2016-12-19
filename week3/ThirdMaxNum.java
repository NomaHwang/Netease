import java.util.SortedSet;
import java.util.TreeSet;


public class ThirdMaxNum {
    public int thirdMax(int[] nums) {
        int n = nums.length;
        SortedSet<Integer> top3 = new TreeSet<Integer>();
        for(int num : nums){
        	if(top3.size() < 3){
        		top3.add(num);
        	}
        	else{
        		if(num <= top3.first()){
        			continue;
        		}
        		else{        			
        			top3.add(num);
        			if(top3.size() == 4){
        			    top3.remove(top3.first());
        			}
        		}
        	}
        }
        if(top3.size() < 3){
        	return top3.last();
        }
        return top3.first();
	} 
    
   public static void main(String[] args){
	   ThirdMaxNum tmn = new ThirdMaxNum();
	   int[] nums = {1,2,2,5,3,5};
	   int n = tmn.thirdMax(nums);
	   System.out.println(n);
   }
};
