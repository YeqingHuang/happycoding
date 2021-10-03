class Solution {
    public boolean canJump(int[] nums) {
        int farest = nums[0];
        for (int curr = 1; curr<nums.length; curr++) {
            if (curr > farest) {
                return false;
            }
            farest = Math.max(farest, curr + nums[curr]);
            if (farest >= nums.length - 1) {
                return true;
            }
        }
        return true; // nums.length = 1
    }
}