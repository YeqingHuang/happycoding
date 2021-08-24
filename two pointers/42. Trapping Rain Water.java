class Solution1 {
    // bruteforce: time O(n^2)
    public int trap(int[] height) {
        int area = 0;
        for (int i=1; i<height.length-1; i++) {
            int leftH = findLeft(i, height);
            int rightH = findRight(i, height);
            area += Math.min(leftH, rightH) - height[i];
        }
        return area;
    }
    
    private int findLeft(int index, int[] arr) {
        int max = arr[index];
        for (int i=0;i<index; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
    
    private int findRight(int index, int[] arr) {
        int max = arr[index];
        for (int i=index+1; i<arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}

class Solution2 {
    // 空间换时间 time O(n) space O(n)
    public int trap(int[] height) {
        // check water height for each pillar
        // suppose we use additional space to find the maxHeight on its left and right
        // then water height = Math.min(leftHeight, rightHeight) - height[i]
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i=1; i<n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        rightMax[n-1] = height[n-1];
        for (int i=n-2; i>=0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        
        // now we can calculate
        int total = 0;
        for (int i=1; i<n-1; i++) {
            total += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return total;
    }
}

class Solution3 {
    // further improve space complexity to O(1)
    public int trap(int[] height) {
        int i = 0, j = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int area = 0;
        
        while (i < j) {
            // update left height and right height
            leftMax = Math.max(height[i], leftMax);
            rightMax = Math.max(height[j], rightMax);

            // decide which height to use
            if (leftMax < rightMax) {
                // use left, still need to compare it with current height
                area += Math.max(0, leftMax - height[i]);
                i++;
            } else {
                // rightMax is the restriction, still need to compare with current height
                area += Math.max(0, rightMax - height[j]);
                j--;
            }
        }
        return area;
    }
}