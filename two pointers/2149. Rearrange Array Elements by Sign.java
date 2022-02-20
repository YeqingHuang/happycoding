class Solution {
    public int[] rearrangeArray(int[] nums) {
        // +, -, + , -, + , -,...
        // the order in which they were present in nums should be preserved
        int[] ans = new int[nums.length];
        int pos = 0; // first available index to place a positive number
        int neg = 1;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0) {
                ans[pos] = nums[i++];
                pos += 2;
            } else {
                ans[neg] = nums[i++];
                neg += 2;
            } 
        }
        return ans;
    }
}