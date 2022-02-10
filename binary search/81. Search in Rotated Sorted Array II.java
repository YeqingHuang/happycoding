class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high - 1) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[low] < nums[mid]) {
                // left half is sorted
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid;
                } else {
                    low = mid;
                }
            } else if (nums[low] > nums[mid]) {
                // right half is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid;
                } else {
                    high = mid;
                }
            } else {
                low++;
            }
        }
        return nums[high] == target || nums[low] == target;
    }
}