class Solution {
    // O(logn)
    public int findPeakElement(int[] nums) {
        // because nums[-1] = nums[n] = -∞ and we can return any of the peaks
        // binary search work under these assumptions
        if (nums.length == 1) return 0;
        
        int low = 0;
        int high = nums.length - 1;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
                return mid;
            } else if (nums[mid] < nums[mid+1]) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return nums[low] < nums[high] ? high: low;
    }

    // a clean O(n) solution
    // as we scan to the right, if we haven't return
    // it already satisfies curr > prev in the previous round
    // we only need to check if curr > next
    public int findPeakElement2(int[] nums) {
        int n = nums.length;
        
        for (int i=0; i<n-1; i++) {
            if (nums[i] > nums[i+1]) return i;
        }
        return n-1;
    }
}