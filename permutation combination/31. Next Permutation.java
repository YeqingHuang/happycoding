class Solution {
    public void nextPermutation(int[] nums) {
        // [3,4,2,6,5,1] -> [3,4,5,1,2,6] so [2,6,5,1] becomes [5,1,2,6]
        // what's the PATTERN?
        // check from right to left
        // 1) if it's always increasing, then no more permutation -> reverse the array
        // 2) somewhere it drops. If peak is at index i, then the part from i-1 till the end needs re-arrangement
        // 3) who can replace nums[i-1]? -> the next greater num (5 replaces 2)
        // 4) how to find it? -> start again from rightmost number until we find it
        // 5) how to do the re-arrangement in place? two steps:
        //    swap nums[i-1] with next greater num, [2,6,5,1] becomes [5,6,2,1]
        //    reverse the part from nums[i] till the end, [5,6,2,1] becomes [5,1,2,6]
        int i = nums.length - 1;
        while (i > 0) {
            // not strict-increasing, use prev >= curr
            if (nums[i-1] >= nums[i]) {
                i--;
            } else {
                break;
            }
        }
        if (i == 0) {
            reversePart(nums, 0);
            return;
        }
        
        // i-1 is the starting point of the subarray we need to modify
        int index = findNextGreater(nums, i-1);
        int temp = nums[i-1];
        nums[i-1] = nums[index];
        nums[index] = temp;
        
        reversePart(nums, i);
    }
    
    private int findNextGreater(int[] nums, int index) {
        // return the index instead of the value
        int target = nums[index];
        for (int i=nums.length - 1; i>index; i--) {
            if (nums[i] > target) {
                return i;
            }
        }
        return -1;
    }
    
    private void reversePart(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}