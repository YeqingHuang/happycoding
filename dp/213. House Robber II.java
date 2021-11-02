class Solution {
   
    public int rob(int[] nums) {
        // suppose there are n houses
        // you either choose index 0 to n-2, or index 1 to n-1
        int n = nums.length;
        if (n == 1) return nums[0];
        
        int startFirst = findMax(nums, 0, n-2);
        int startSecond = findMax(nums, 1, n-1);
        return Math.max(startFirst, startSecond);
    }
    
    // O(1) space
    private int findMax(int[] nums, int left, int right) {
        int prevTake = 0, prevSkip = 0;
        for (int i=left; i<=right; i++) {
            int currTake = prevSkip + nums[i];
            int currSkip = Math.max(prevTake, prevSkip);
            
            prevTake = currTake;
            prevSkip = currSkip;
        }
        return Math.max(prevTake, prevSkip);
    }

    // O(n) space
    private int findMax1(int[] nums, int left, int right) {
        int len = right - left + 1;
        int[] dp = new int[len+2];
        for (int i=2; i<len+2; i++) {
            // left is either 0 or 1, this is a shift,so we use i+left
            dp[i] = Math.max(dp[i-2] + nums[i+left-2], dp[i-1]);
        }
        return dp[len+1];
    }
}