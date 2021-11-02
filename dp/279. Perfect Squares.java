class Solution {
    public int numSquares(int n) {
        // the first thing is to know the square upper bound
        int upper = (int) Math.sqrt(n);
        if (upper * upper == n) return 1;
        
        int[] dp = new int[n+1];
        for (int i=1; i<=n; i++) {
            dp[i] = i; // suppose we only use number 1
        }
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=upper; j++) {
                int square = j*j;
                if (i - square == 0) {
                    dp[i] = 1;
                } else if (i - square > 0) {
                    dp[i] = Math.min(dp[i], dp[i-square] + 1);
                }
            }
        }
        return dp[n];
    }
}