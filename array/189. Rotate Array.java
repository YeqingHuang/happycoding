class Solution {
    // O(n) space
    public void rotate(int[] nums, int k) {
        if (k == 0) return;
        
        // first half [0,len - k - 1] will be shifted to the right by k
        // second half [len - k, len - 1] shifted to the left by len - k
        int n = nums.length;
        k = k % n;
        int[] ans = new int[n];
        for (int i=0; i<n-k; i++) {
            ans[i+k] = nums[i];
        }
        for (int i=n-k; i<n; i++) {
            ans[i-n+k] = nums[i];
        }
        
        for (int i=0; i<n; i++) {
            nums[i] = ans[i];
        }
    }

    // O(1) space
    public void rotate(int[] nums, int k) {
        // reverse method to solve it without using extra space
        // [1,2,3,4,5,6], k=2
        // [6,5,4,3,2,1], reverse completely
        // [5,6,4,3,2,1], reverse first k elements
        // [5,6,1,2,3,4], reverse last n-k elements
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }
    
    private void reverse(int[] nums, int start, int end) {
        int i=start, j = end;
        while (i < j) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            i++;
            j--;
        }
    }
}