class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // try to use each 1 as the center of the cross
        // order = min(left, right, up, down), this step takes O(2n)
        // overall, brute force takes O(n^3)
        
        // how to use dp? we can use the same matrix but do it four times
        // to improve the time complexity to O(n^2)
        Set<Integer> zeroCells = new HashSet<>();
        for (int[] cell: mines) {
            zeroCells.add(cell[0] * n + cell[1]);
        }
        
        int[][] dp = new int[n][n];
        int count;
        
        // check each row, 1st pass left to right, 2nd pass right to left
        for (int i=0; i<n; i++) {
            count = 0;
            for (int j=0; j<n; j++) {
                count = zeroCells.contains(i*n + j) ? 0: count+1;
                dp[i][j] = count;
            }
            
            count = 0;
            for (int j=n-1; j>=0; j--) {
                count = zeroCells.contains(i*n + j) ? 0: count+1;
                dp[i][j] = Math.min(count, dp[i][j]);
            }
        }
        
        // check each column, 1st pass up to down, 2nd pass down to up
        for (int j=0; j<n; j++) {
            count = 0;
            for (int i=0; i<n; i++) {
                count = zeroCells.contains(i*n + j) ? 0: count+1;
                dp[i][j] = Math.min(count, dp[i][j]);
            }
            count = 0;
            for (int i=n-1; i>=0; i--) {
                count = zeroCells.contains(i*n + j) ? 0: count+1;
                dp[i][j] = Math.min(count, dp[i][j]);
            }
        }
        
        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
    }
}