class Solution {
    public int maxArea(int[] height) {
        // begin from both side, move pointers to the middle
        // if left is lower, the next one may benefit(becomes higher), so we move left
        // if we move right, the restriction is still here, 
        // and width becomes smaller, no benefit at all
        int maxArea = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int left = height[i];
            int right = height[j];
            maxArea = Math.max(maxArea, Math.min(left, right) * (j-i));
            if (left < right) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
}