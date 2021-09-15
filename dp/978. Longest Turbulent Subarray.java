class Solution {
    // O(n) time , O(n) space
    public int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] increasing = new int[arr.length];
        int[] decreasing = new int[arr.length];
        Arrays.fill(increasing, 1);
        Arrays.fill(decreasing, 1);
        int maxLen = 1;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > arr[i-1]) {
                increasing[i] = decreasing[i-1] + 1;
            } else if (arr[i] < arr[i-1]) {
                decreasing[i] = increasing[i-1] + 1;
            } // else increasing[i] and decreasing[i] are 1
            maxLen = Math.max(maxLen, Math.max(increasing[i], decreasing[i]));
        }
        return maxLen;
    }

    // O(n) time, O(1) space
    public int maxTurbulenceSize1(int[] arr) {
        int prevPair = 0; // compare arr[i-1] with arr[i-2]
        int currPair = 0; // compare arr[i] with arr[i-1]
        int len = 1;
        int maxLen = 1;
        for (int i=1; i<arr.length; i++) {
            currPair = Integer.compare(arr[i], arr[i-1]); // 1:increasing, -1: decreasing
            if (currPair * prevPair == -1) {
                len++;
            } else if (currPair == 0) {
                len = 1;
            } else {
                len = 2; // either it's increasing or decreasing
            }
            maxLen = Math.max(maxLen, len);
            prevPair = currPair;
        }
        return maxLen;
    }
}