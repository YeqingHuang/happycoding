class Solution {
    public int search(int[] nums, int target) {
        // no matter where is the break point, at least half of them is sorted
        // how do we know which half is the normal half?
        // nums[mid] > nums[low] -> left half is normal
        // nums[high] > nums[mid] -> right half is normal
        int low = 0;
        int high = nums.length - 1;
        while (low < high - 1) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[low] < nums[mid]) {
                // left half is sorted
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid;
                } else {
                    low = mid;
                }
            } else {
                // right half is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
        }
        if (nums[high] == target) return high;
        if (nums[low] == target) return low;
        return -1;
    }
}