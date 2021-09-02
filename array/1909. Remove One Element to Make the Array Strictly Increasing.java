class Solution {
    public boolean canBeIncreasing(int[] nums) {
        if (nums.length <= 2) return true;
        
        // nums = [1,2,10,5,7]
        // when we find 5 <= 10, there are two options:
        // remove 5, still invalid; remove 10, OK
        
        // nums = [1,6,10,5,12]
        // remove 5, OK; remove 10, 1->6->5 becomes invalid
        
        // conclusion: when we find a drop
        // if nums[i] > nums[i-2], just like case1, we remove nums[i-1]
        // if nums[i] <= nums[i-2], we remove nums[i], juse like case2
        boolean used = false;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] <= nums[i-1]) {
                if (used) {
                    return false;
                } else {
                    if (i >= 2 && nums[i] <= nums[i-2]) {
                        // remove nums[i]
                        nums[i] = nums[i-1];
                    } // else remove nums[i-1], just go on
                    used = true;
                }
            }
        }
        return true;
    }
}