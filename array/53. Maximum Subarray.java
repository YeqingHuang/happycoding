class Solution {
    public int maxSubArray(int[] nums) {
        // clarify if we have the option of not choosing any numbers
        // case like [-1], shall we return -1 or 0?
        int maxSum = nums[0];
        int currSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (currSum > 0) {
                currSum += nums[i];
            } else {
                currSum = nums[i];
            }
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}