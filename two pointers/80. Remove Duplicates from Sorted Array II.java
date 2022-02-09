class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        
        int i = 1; // first available pos
        int j = 1; // iterate array
        int count = 1; // nums[0] has a freq of 1
        while (j<nums.length) {
            // step1: check
            if (nums[j] == nums[j-1]) {
                count++;
            } else {
                count = 1;
            }
            // step2: keep it unchanged or overwrite
            if (count <= 2) {
                nums[i] = nums[j];
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i;
    }
}