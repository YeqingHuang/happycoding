class Solution {
    public int largestRectangleArea(int[] heights) {
        // heights = [2,1,5,6,2,3]
        // use each height as the highest, and expand to the left and to the right
        // width = right - left + 1 (both inclusive)
        // we want O(1) instead of O(n) to find left and right
        // keep an increasing stack
        int n = heights.length;        
        int[] copied = new int[n+2];
        for (int i=1; i<n+1; i++) {
            copied[i] = heights[i-1];
        }
        heights = copied;
        
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // index
        for (int i=0; i<heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int popped = stack.pop();
                int left = stack.peek() + 1;
                int right = i - 1;
                maxArea = Math.max(heights[popped] * (right - left + 1), maxArea);
            }
            stack.push(i);
        }
        return maxArea;
    }
}