class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";
        if (n == 1) return s;
        
        int maxLen = 1;
        int start = 0;
        boolean[][] valid = new boolean[n][n];
        for (int i=0; i<n-1; i++) {
            valid[i][i] = true;
            if (s.charAt(i) == s.charAt(i+1)) {
                valid[i][i+1] = true;
                // dob't miss this step
                maxLen = 2;
                start = i;
            }
        }
        valid[n-1][n-1] = true;        
        
        // beginning from n = 3, general case
        // s.charAt(i) == s.charAt(j) and s.substring(i+1, j) is palindrome
        for (int len=3; len<=n; len++) {
            for (int i=0; i<= n-len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && valid[i+1][j-1]) {
                    valid[i][j] = true;
                    if (len > maxLen) {
                        maxLen = len;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}