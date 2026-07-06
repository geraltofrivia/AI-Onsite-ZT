// https://github.com/kdn251/interviews/tree/03fdcb2703ce72dc0606748733d0c13f09d41d21/leetcode/two-pointers/MoveZeros.java#L10-L27
public class TempClass {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        
        int index = 0;
        for(int num : nums) {
            if(num != 0) {
                nums[index] = num;
                index++;
            }
        }
        
        while(index < nums.length) {
            nums[index] = 0;
            index++;
        }
    }

}