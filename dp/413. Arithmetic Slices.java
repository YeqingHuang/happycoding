class Solution {
    // O(n) time and O(n) space
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length <= 2) return 0;
        
        // we use nums[i] - nums[i-1] == nums[i-1] - nums[i-2] to check
        // once we find a triplet, dp[i] = 1
        // suppose i++ constructs a new triplet, then dp[i+1] = 2, (one with len = 3, one with len = 4)
        // then suppose the next one does not satisfy, we set dp[i+2] = 0
        int[] dp = new int[nums.length];
        for (int i=2; i<nums.length; i++) {
            if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 0;
            }
        }
        
        int ans = 0;
        for (int count: dp) {
            ans += count;
        }
        return ans;
    }
}