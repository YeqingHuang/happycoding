class Solution {
    public int maxPoints(int[][] points) {
        // thoughts: try every point as the starting point (x0,y0)
        // store the slope as key, value is count
        // the problem is how to express slope? two whole numbers a and b
        // if y1 - y0 = 10, x1 - x0 = 6, we store a string "5:3"
        int n = points.length;
        if (n == 1) return n;
        
        int maxCount = 1;
        for (int i=0; i<n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int maxCountForCurr = 0;  // does not include this point itself
            for (int j=i+1; j<n; j++) {
                int deltaX = points[j][0] - points[i][0];
                int deltaY = points[j][1] - points[i][1];
                String slope = getSlope(deltaX, deltaY);
                int count = map.getOrDefault(slope, 0) + 1;
                map.put(slope, count);
                maxCountForCurr = Math.max(count, maxCountForCurr);
            }
            maxCount = Math.max(maxCountForCurr + 1, maxCount);
        }
        return maxCount;
    }
    
    private String getSlope(int x, int y) {
        // x = 3, y = 5, return "5/3"
        // x = -6, y = -10, return "5/3"
        int divisor = findGCD(x, y);
        return y/divisor + ":" + x/divisor;
    }
    
    private int findGCD(int a, int b) {
        // find the largest integer that divides both a and b
        if (b == 0) return a;
        return findGCD(b, a % b);
    }
}