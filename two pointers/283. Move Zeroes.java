class Solution {
    public void moveZeroes(int[] nums) {
        // must be done in-place
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }
        for (int k = i; k< nums.length; k++) {
            nums[k] = 0;
        }
    }
}