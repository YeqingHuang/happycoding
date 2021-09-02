class Solution {
    public int minOperations(int[] nums) {
        // [1,5,2,4,1]
        // each nums[i] should be at least equal to i+1
        // when we come to nums[2] = 2, the bar is raised by the previous number
        // now the current number should be at least 6
        int operations = 0;
        int prev = nums[0];
        for (int i=1; i<nums.length; i++) {
            int desired = Math.max(nums[i], prev+1);
            operations += desired - nums[i];
            prev = desired;
        }
        return operations;
    }
}