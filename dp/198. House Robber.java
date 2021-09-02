class Solution {
	// O(n) time, O(n) space
    public int rob(int[] nums) {
        // visit house i, gain = dp[i-2] + nums[i]
        // skip house i, gain = dp[i-1];
        int n = nums.length;
        
        int[] dp = new int[n+1];
        dp[1] = nums[0];
        for (int i=2; i<=n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[n];
    }
}

class Solution {
	// O(n) time, O(1) space
    public int rob(int[] nums) {
        int takePrev = 0, skipPrev = 0;
        for (int num: nums) {
            int takeCurr = skipPrev + num;
            int skipCurr = Math.max(skipPrev, takePrev);
            
            takePrev = takeCurr;
            skipPrev = skipCurr;
        }
        return Math.max(takePrev, skipPrev);
    }
}