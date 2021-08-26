class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        // i points to the last filled in index
        // j is used to iterate
        int i = 0;
        int prevCount = 1;
        for (int j=1; j<nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
                prevCount = 1;
            } else if (prevCount == 1) {
                // we can still add one more
                nums[++i] = nums[j];
                prevCount++;
            }
        }
        return i + 1;
    }
}