class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        // as long as it reaches a point where sum < target changes to sum >= target
        // we find a local optimal answer, then try to shrink from left
        // nums = [1,1,1,20,20,40] and target = 30
        // firstly, we get i=0 and j=4, so ans = 5
        // then we shrink from left, i=3, j=4, so ans = 2
        // then we further shrink, but i=4, j=4 no longer satisfies
        // we again expand, i=4, j=5, ans = 2
        // finally we reach i=5, j=5, ans = 1
        if (target == 1) return 1;
        
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int i=0, j=0;
        while (j<nums.length) {
            sum += nums[j];
            while (sum >= target) {
                minLen = Math.min(minLen, j-i+1);
                if (minLen == 1) return minLen;
                sum -= nums[i++];
            }
            j++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}