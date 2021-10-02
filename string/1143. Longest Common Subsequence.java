class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        
        // dp[i][j] means text1.substring(0,i) and text2.substring(0,j)
        // has a common sequence which length is dp[i][j]
        // (not necessarily select text1.charAt(i-1) or text2.charAt(j-1))
        // if i=0, then text1 is now an empty string, similar for j=0
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // consider "abcd" and "acbc", the last char 'd' != 'c'
                    // the answer either comes from "abc" and "acbc", i.e.3
                    // or "abcd" and "acb", i.e. 2
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}