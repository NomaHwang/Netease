import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution{
	public int[] twoSum(int[] nums, int target) {       
        Map<Integer, Integer> hashtb = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for(int i = 0; i < nums.length; ++i){
        	if(hashtb.containsKey(target - nums[i])){
        		res[0] = i;
        		res[1] = hashtb.get(target - nums[i]);
        		break;
        	}
        	else{
        		hashtb.put(nums[i], i);
        	}
        }
        return res;
    }
}

public class TwoSum {
	public static void main(String[] args){
		Solution sol = new Solution();
		int[] nums = {2, 7, 11, 25};
		int target = 9;
		int[] res = sol.twoSum(nums, target);
		for(int num : res){
			System.out.print(num + ", ");
		}
		System.out.println();
	}
	
}
