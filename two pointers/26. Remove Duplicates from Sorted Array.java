class Solution {
    public int removeDuplicates(int[] nums) {
        // must be done in-place with O(1) space
        if (nums.length == 0) return 0;
        
        int i = 0;  // first available index is i + 1
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            } 
        }
        return i + 1;
    }
}