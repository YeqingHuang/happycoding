class Solution {
    // top-down
    HashMap<Integer, Boolean> memo;
        
    public boolean winnerSquareGame(int n) {
        // we want to achieve a status that Bob loses
        // if there are x stones left, we want to have dfs(x - k*k) = false
        // where k > 0 && k*k < x
        // there will be overlapping subproblems, so use memoization
        memo = new HashMap<>();
        memo.put(0, false);
        return dfs(n);
    }
    
    private boolean dfs(int remaining) {
        if (memo.containsKey(remaining)) {
            return memo.get(remaining);
        }
        
        int upper = (int) Math.sqrt(remaining);
        for (int k=1; k<=upper; k++) {
            if (!dfs(remaining - k*k)) {
                memo.put(remaining, true);
                return true;
            }
        } 
        // we've tried all the possible moves, still cannot return true
        memo.put(remaining, false);
        return false;
    }
}

class Solution {
    // bottom up
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n+1];
        // dp[0] is false, because we cannot take 0
        for (int num=1; num<=n; num++) {
            for (int k=1; k*k <= num; k++) {
                if (dp[num - k*k] == false) {
                    dp[num] = true;
                    break;
                }
            }
        }      
        return dp[n];
    }
}