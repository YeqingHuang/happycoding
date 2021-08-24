class Solution {
    // note that it's important to check arr[i] > arr[i-1] && arr[i] > arr[i+1]
    // otherwise time complexity would become O(n^2) instead of O(n)
    public int longestMountain(int[] arr) {
        if (arr.length <= 2) return 0;
        
        int longest = 0;
        for (int i=1; i<arr.length - 1; i++) {
            if (arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                // arr[i] is a peak
                int left = i - 1;
                while (left - 1 >= 0 && arr[left-1] < arr[left]) {
                    left--;
                }
                int right = i + 1;
                while (right + 1 < arr.length && arr[right] > arr[right+1]) {
                    right++;
                }
                longest = Math.max(longest, right - left + 1);
            }
        }
        return longest;
    }
}