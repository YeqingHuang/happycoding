class Solution {
    // recursion and memoization
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet(wordDict);
        Boolean[] canForm = new Boolean[s.length()];
        return helper(s,0,set,canForm);
    }
    
    private boolean helper(String s, int start, Set<String> set, Boolean[] canForm) {
        if (start == s.length()) return true;
        if (canForm[start] != null) return canForm[start];
        
        // we need to find out if the suffix can be formed with the set
        for (int end = start; end < s.length(); end++) {
            if (set.contains(s.substring(start, end+1)) && helper(s, end+1, set, canForm)) {
                canForm[start] = true;
                return true;
            }
        }
        canForm[start] = false;
        return false;
    }
}

class Solution {
    // dp
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet(wordDict);
        int n = s.length();
       
        // dp[i] stands for if we can use the word set
        // to form a substring of s [0,i-1]
        boolean[] dp = new boolean[n+1];
        dp[0] = true;  // an empty string can be formed
        
        
        for (int i=1; i<=n; i++) {
            // we are going to expand the word by one char each time
            for (int j=0; j<i; j++) {
                // try to find the potential breakpoint
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
