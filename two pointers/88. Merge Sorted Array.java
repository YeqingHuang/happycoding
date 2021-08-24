class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // if we begin from the left
        // we have to use additional space to store those nums that might be overwrite
        // we can fill in the biggest from right side of nums1 to avoid this issue
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i];
                i--;
            } else {
                nums1[k--] = nums2[j];
                j--;
            }
        }
        // if j < 0, we are done
        while (j >= 0) {
            nums1[k--] = nums2[j];
            j--;
        }
    }
}