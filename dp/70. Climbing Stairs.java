class Solution {
    // top-down momoization
    Map<Integer, Integer> memo;
    
    public int climbStairs(int n) {
        memo = new HashMap<>();
        return climbHelper(n);
    }
    
    private int climbHelper(int n) {
        if (n <= 1) return 1;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        int ans = climbHelper(n-1) + climbHelper(n-2);
        memo.put(n, ans);
        return ans;
    } 
}

class Solution {
    // bottom-up dp
    public int climbStairs(int n) {
        if (n == 1) return 1;
        
        int[] dp = new int[n+1];
        // it takes 1 step when n = 0 or n = 1
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}