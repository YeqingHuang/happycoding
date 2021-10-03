class Solution {
    // O(kn) time, O(n) space
    public int jump(int[] nums) {
        // dp[i] stands for how many steps it takes to reach index i
        int n = nums.length;
        int[] dp = new int[n];
        for (int i=0; i<n; i++) {
            dp[i] = i;
        }
        
        for (int i=0; i<n; i++) {
            int farest = Math.min(n - 1, i + nums[i]);
            for (int k=i+1; k<=farest; k++) {
                dp[k] = Math.min(dp[k], dp[i]+1);
            }
            if (farest == n - 1) {
                break; // future updates won't beat this one
            }
        }
        return dp[n-1];
    }
}