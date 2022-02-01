class Solution {
    public int longestOnes(int[] nums, int k) {
        // sliding window:
        // in the window there could be at most k zeros
        // therefore, we keep a counter
        int count = 0;
        int i = 0, j = 0;
        int maxLen = 0;
        while (j < nums.length) {
            if (nums[j] == 0) count++;
            while (count > k) {
                // shrink from left
                if (nums[i] == 0) count--;
                i++;
            }
            // now it satisfies count <= k
            maxLen = Math.max(j - i + 1, maxLen);
            j++;
        }
        return maxLen;
    }
}