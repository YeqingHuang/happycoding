class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // if at some point, it no longer satisfies
        // expanding has no benefits, we shrink from left
        // [1,2,5,13,10]
        // [1,2,10,130,1300], suppose k = 80
        // when j = 0, ans += 1
        // when j = 1, ans += 2, [2] and [1,2]
        // when j = 2, ans += 3, [5], [2,5], [1,2,5]
        // now j = 3, prefix 130 > 80, we begin to move i
        // i = 1, after removing the first number prefix = 130
        // i = 2, now prefix = 65 < 80, the new one are [13] [5,13]
        // we can find the rule: ans += the length of the subarray
        if (k <= 1 || nums == null || nums.length == 0) return 0;
        
        int i = 0, j = 0;
        int product = 1;
        int ans = 0;
        while (j < nums.length) {
            // the tricky part is how to prevent i going beyond j
            // suppose nums[j] itself is bigger than k
            // when we increment i to i = j, product becomes 1 and i becomes j+1
            // now ans += j - (j+1) + 1, i.e. ans += 0
            // we then increment j by 1
            product *= nums[j];
            while (product >= k) {
                product /= nums[i++];
            }
            ans += j - i + 1;
            j++;
        }
        return ans;
    }
}