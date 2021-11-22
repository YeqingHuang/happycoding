class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 300. Longest Increasing Subsequence
        // dp: each num forms a subsequence of len = 1
        // we increase this len by O(n^2)
        
        // the tricky part is to retrieve the subsequence
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 1) {
            ans.add(nums[0]);
            return ans;
        }
        
        Arrays.sort(nums);
        int[][] dp = new int[nums.length][2]; //{maxLen, prev index}
        for (int i=0; i<nums.length; i++) {
            dp[i] = new int[]{1,-1};
        }
        
        
        int maxLen = 1;
        int endingIndex = 0;
        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                // i is the right pointer, j is the left pointer
                if (nums[i] % nums[j] == 0) {
                    if (dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1; // local maxLen for nums[i]
                        dp[i][1] = j; // this len comes from nums[j]
                    }
                    if (dp[i][0] > maxLen) {
                        maxLen = dp[i][0];
                        endingIndex = i;
                    }
                }
            }
        }
        
        // construct the ans
        while (endingIndex != -1) {
            ans.add(nums[endingIndex]);
            endingIndex = dp[endingIndex][1];
        }
        Collections.reverse(ans);
        return ans;
    } 
}