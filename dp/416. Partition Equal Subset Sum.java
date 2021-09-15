class Solution {
    // let n be the length of nums, m be targetSum
    // space is O(mn), time is also O(mn)
    // in the worse case, no overlapping problems, 
    // i.e. dp matrix does not make it faster
    // then each call of dfs() can solve one dp[i][j]
    private Boolean[][] dp;
    
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        
        // now we want to choose some numbers so that
        // at a certain point, the sum reaches sum/2
        // How to use dp to solve this problem?
        // for each nums[i]:
        // Option1: choose it, currSum - nums[i], i+1, nums.length-1
        // Option2: ignore it, currSum, i+1, nums.length - 1
        // if currSum turns to 0, we achieve sum/2
        // if currSum < 0 OR i+1 == nums.length, stop here
        int targetSum = sum / 2;
        // if dp[i][j] is true, it means that we can achieve sum j
        // using numbers in the subarray [0,i] (either include nums[j] or not)
        dp = new Boolean[nums.length][targetSum+1];
        return dfs(nums, 0, targetSum);
    }
    
    private boolean dfs(int[] nums, int index, int targetSum) {
        if (targetSum == 0) return true;
        if (targetSum < 0 || index == nums.length) return false;
        if (dp[index][targetSum] != null) {
            return dp[index][targetSum];
        }
        boolean keep = dfs(nums, index+1, targetSum - nums[index]);
        boolean discard = dfs(nums, index+1, targetSum);
        boolean result = keep || discard;
        dp[index][targetSum] = result;
        return result;
    }
}