class Solution {
	// O(n) time, O(n) space
    public int rob(int[] nums) {
        // for each house: two options: take it or ignore it
        int n = nums.length;
        int[] dp = new int[n+2];
        for (int i=2; i<n+2; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i-2], dp[i-1]);
        }
        return dp[n+1];
    }
}

class Solution {
	// O(n) time, O(1) space
    public int rob(int[] nums) {
        int prevTake = 0;
        int prevSkip = 0;
        for (int num: nums) {
            int currTake = prevSkip + num;
            int currSkip = Math.max(prevTake, prevSkip);
            
            prevTake = currTake;
            prevSkip = currSkip;
        }
        return Math.max(prevTake, prevSkip);
    }
}