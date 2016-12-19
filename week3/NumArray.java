import java.util.ArrayList;


public class NumArray {

	private ArrayList<Integer> sums = new ArrayList<Integer>();
	
    public NumArray(int[] nums) {
        int sum = 0;
        sums.add(0);
        for(int num : nums){
        	sum += num;
        	sums.add(sum);
        }
    }

    public int sumRange(int i, int j) {
        return sums.get(j + 1) - sums.get(i);
    }
    
    public static void main(String[] args){
    	int[] nums = {-2, 0, 3, -5, 2, -1};
    	NumArray na = new NumArray(nums);
    	
    	System.out.println(na.sumRange(0,2));
    	System.out.println(na.sumRange(2,5));
    	System.out.println(na.sumRange(0,5));
    }
}