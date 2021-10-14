class Solution {
    // O(n) time and O(n) space
    public int[] productExceptSelf(int[] nums) {
        // suppose nums = [1,2,3,4]
        // for example, for 2, leftProduct = 1, rightProduct = 12, ans = 1 * 12
        // leftProducts =  [1, 1, 2, 6]
        // rightProducts = [24,12,4, 1]
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        for (int i=1; i<n; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        right[n-1] = 1;
        for (int i=n-2; i>=0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }
        
        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

    // O(n) time and O(1) space
    public int[] productExceptSelf(int[] nums) {
        // use ans[] to store left[], then update it in the second pass
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i=1; i<n; i++) {
            ans[i] = ans[i-1] * nums[i-1];
        }
        
        int right = 1;
        for (int i=n-1; i>=0; i--) {
            ans[i] = ans[i] * right;
            right *= nums[i];
        }
        return ans;
    }
}