class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // we need to find some partition in nums1 and nums2
        // nums1: .... x1 | x2, .... 
        // nums2: .... y1 | y2, ....
        // the candidate comes from one of x1, x2, y1, y2 OR the average of two of them
        
        // n1 = length sum of two left subarrays
        // n2 = length sum of two right subarrays
        // n1 = n2 or Math.abs(n1 - n2) = 1
        // once the partition in nums1 is fixed, the partition in nums2 is also fixed
                
        // what's the property of the correct partition1?(i.e. p1)
        // x1 <= y2 and y1 <= x2
        // if x1 > y2, move p1 to the left; if x2 < y1, move p1 to the right
        
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) return findMedianSortedArrays(nums2, nums1);        

        // when there are k numbers, there are k+1 ways to place partition
        // partition index is the start of the second part, [low, p1-1] [p1, high)
        int low = 0, high = m;
        while (low <= high) {            
            int p1 = (low + high) / 2;
            int p2 = (m + n + 1) / 2 - p1; 
            
            int x1 = getLeftNum(nums1, p1);
            int x2 = getRightNum(nums1, p1);
            int y1 = getLeftNum(nums2, p2);
            int y2 = getRightNum(nums2, p2);
            // compare the values
            if (x1 <= y2 && y1 <= x2) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(x1, y1) + Math.min(x2, y2)) / 2.0;
                }
                return Math.max(x1,y1);
            } else if (x1 > y2) {
                high = p1 - 1;
            } else {
                low = p1 + 1;
            }
        }
        return -1; // won't reach this line
    }
    
    private int getLeftNum(int[] nums, int index) {
        if (index == 0) {
            // i.e out of boundary, this value does not exist
            return Integer.MIN_VALUE; 
        }
        return nums[index-1];
    }
    
    private int getRightNum(int[] nums, int index) {
        if (index == nums.length) {
            // i.e out of boundary, this value does not exist
            return Integer.MAX_VALUE;
        }
        return nums[index];
    }
}