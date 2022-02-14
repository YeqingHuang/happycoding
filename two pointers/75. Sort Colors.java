class Solution {
    public void sortColors(int[] nums) {
        // move 0 to the front and move 2 to the end
        if (nums.length <= 1) return;
        
        int left = 0; // points to the first avaible index for 0
        int right = nums.length - 1; // points to the first avaible index for 2
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                nums[left++] = 0;
                i++;
            } else if (nums[i] == 2) {
                // we have not checked nums[j] yet
                // we cannot say nums[j--] = 2;
                swap(nums, i, right);
                right--;
                // we will check the swapped num in the next round
            } else {
                i++;
            }
        }
        
        for (int k = left; k <= right; k++) {
            nums[k] = 1;
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}