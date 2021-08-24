class Solution {
    public int longestMountain(int[] arr) {
        // naive approach, try every mountain as peak 
        if (arr.length <= 2) return 0;
        
        int longest = 0;
        for (int i=1; i<arr.length - 1; i++) {
            // peak is arr[i]
            int left = i;
            while (left-1 >= 0 && arr[left-1] < arr[left]) {
                left--;
            }
            int right = i;
            while (right+1 < arr.length && arr[right] > arr[right+1]) {
                right++;
            }
            if (left == i || right == i) continue;
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }
}