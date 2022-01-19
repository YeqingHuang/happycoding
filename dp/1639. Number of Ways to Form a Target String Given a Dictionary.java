class Solution {
    Integer[][] memo;
    int[][] freqAtIndex;
    int m, n;
    int MOD = (int) 1e9+7;
    public int numWays(String[] words, String target) {
        // thoughts: this is a dp problem
        // but we need to pre-process the words in order to use it wisely
        // the index matters, so we can store the information of 
        // how many chars are available at each index, e.g. index = 0, {'a':1, 'b':1, 'c':1}
        m = words[0].length();
        n = target.length();
        memo = new Integer[m][n];
        freqAtIndex = new int[26][m];
        
        for (String word: words) {
            for (int i=0; i<m; i++) {
                freqAtIndex[word.charAt(i)-'a'][i]++;
            }
        }
        return helper(target, 0, 0);
    }
    
    private int helper(String s, int k, int start) {
        if (start == n) {
            return 1; // a valid string s is formed
        }
        if (k == m) {
            return 0; // reach the end of the words but cannot form an answer
        }
        if (memo[k][start] != null) {
            return memo[k][start];  // already calculated
        }
        
        char needed = s.charAt(start);
        // the options are 1) skip it 2) use it
        long ans = helper(s, k+1, start); // Skip k_th index of words
        if (freqAtIndex[needed - 'a'][k] > 0) {
            ans += (long) helper(s, k+1, start+1) * freqAtIndex[needed - 'a'][k];
            ans %= MOD;
        }
        memo[k][start] = (int) ans;
        return (int) ans;
    }
}