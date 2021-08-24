class Solution {
    public void sortColors(int[] nums) {
        if (nums.length <= 1) return;
        
        // suppose we use a pointer i to iterate
        // nums[i] is the number we'd like to check
        // when i meets right pointer, we can stop
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                nums[left] = 0;
                left++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
                // we cannot i++, we still need to check
            } else {
                i++;
            }
        }
        
        // set the part from left to right as 1s
        for (int k=left; k<= right; k++) {
            nums[k] = 1;
        }
    }

    public void sortColors(int[] nums) {
        if (nums.length <= 1) return;
    
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                // use swap when num == 0, don't need the additional set step
                swap(nums, left, i);
                left++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
                // we cannot i++, we still need to check
            } else {
                i++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}