class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        
        // s = "leetcode" is true because dp[4] is true and "code" is in the set
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int end=1; end<=s.length(); end++) {
            for (int i=0; i<end; i++) {
                if (dp[i] && set.contains(s.substring(i, end))) {
                    dp[end] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}