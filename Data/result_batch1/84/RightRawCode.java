// https://github.com/ashishps1/awesome-leetcode-resources/tree/17cc5669f2bd5f2efe784ea0e1ce6f8e5fb5ec01/patterns/java/KadaneAlgorithm.java#L4-L16
public class TempClass {
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0]; // Start with the first element
        int maxSum = nums[0];     // Initialize maxSum with the first element

        // Traverse the array from the second element
        for (int i = 1; i < nums.length; i++) {
            // If currentSum is negative, reset to current element
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // Update maxSum if currentSum is greater
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }    

}