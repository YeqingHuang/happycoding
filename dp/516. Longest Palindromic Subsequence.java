class Solution {
    public int longestPalindromeSubseq(String s) {
        // dp[i][j] represents the longest subsequence's length begining with i and ending with j
        // when i = j, dp[i][j] = 1
        // for general cases, say s = "bacdab"
        // if s.charAt(0) == s.charAt(5), dp[0][5] = 2 + dp[1][4]
        // otherwise, dp[0][5] = max(dp[0][4], dp[1][5])
        int n = s.length();
        if (n == 1) return 1;
        
        int[][] dp = new int[n][n];
        for (int i=0; i<n; i++) {
            dp[i][i] = 1;
        }
        
        // fill the top-right triangle
        for (int len = 2; len <= n; len++) {
            for (int i=0; i<= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
}
