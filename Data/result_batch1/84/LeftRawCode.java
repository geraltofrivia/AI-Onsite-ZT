// https://github.com/kdn251/interviews/tree/03fdcb2703ce72dc0606748733d0c13f09d41d21/leetcode/array/MaximumProductSubarray.java#L7-L27
public class TempClass {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int result = nums[0];
        int max = nums[0];
        int min = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(nums[i] * max, nums[i] * min), nums[i]);
            min = Math.min(Math.min(nums[i] * temp, nums[i] * min), nums[i]);
            
            if(max > result) {
                result = max;
            }
        }
        
        return result;
    }

}