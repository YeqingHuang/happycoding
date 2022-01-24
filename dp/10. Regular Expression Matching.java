class Solution {
    public boolean isMatch(String s, String p) {
        // bottom-up dp
        // dp[i][j] = true means s.substring(0,i) matches p.substring(0, j) 
        if (s == null || p == null) return false;
        
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        
         // s= "aab", p = "c*a*b"
        // the first '*' means that it can be 0 'c', so p becomes "a*b" and it matches "aab"
        for (int j=0; j<p.length(); j++) {
            if (p.charAt(j) == '*' && dp[0][j-1]) {
                dp[0][j+1] = true;
            }
        }
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    // we find a match when expanding the substrings by one char
                    dp[i+1][j+1] = dp[i][j];     
                }
                
                if (p.charAt(j) == '*') {
                    // '*' Matches zero or more of the preceding element
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        // '*' has no use, it should only be considered as emtpy
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        // 3 ways to interprate this '*'(empty is still a choice)
                        // multiple, single, or empty
                         dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1];
                    }
                }
            }
        }
        return dp[m][n];
    }
}