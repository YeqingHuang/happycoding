class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        if (nums.length == 0) return ans;
        
        ans[0] = findLeftMost(nums, target);
        ans[1] = findRightMost(nums, target);
        return ans;
    }
    
    private int findLeftMost(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                // check the left part
                high = mid;
            } else {
                low = mid;
            }
        }
        if (nums[low] == target) {
            return low;
        } else if (nums[high] == target) {
            return high;
        } else {
            return -1;
        } 
    }
    
    private int findRightMost(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (nums[mid] <= target) {
                // check the right part
                low = mid;
            } else {
                high = mid;
            }
        }
        if (nums[high] == target) {
            return high;
        } else if (nums[low] == target) {
            return low;
        } else {
            return -1;
        } 
    }
}