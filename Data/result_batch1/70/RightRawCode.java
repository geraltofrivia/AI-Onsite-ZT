// https://github.com/ashishps1/awesome-leetcode-resources/tree/17cc5669f2bd5f2efe784ea0e1ce6f8e5fb5ec01/patterns/java/TwoPointers.java#L5-L18
public class TempClass {
    public void moveZeroesTwoPointers(int[] nums) {
        int left = 0; // Pointer for placing non-zero elements

        // Iterate with right pointer
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                // Swap elements if right pointer finds a non-zero
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++; // Move left pointer forward
            }
        }
    }

}