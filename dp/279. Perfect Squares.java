class Solution {
    // bottom-up dp: 
    // time O(n*sqrt(n)), space O(n)
    public int numSquares(int n) {
        int upper =(int) Math.sqrt(n);
        if (upper * upper == n) return 1;
        
        // dp[i] stands for the minStep to reach the cell i
        int[] dp = new int[n+1];
        for (int i=0; i<=n; i++) {
            dp[i] = i;
        }
        
        List<Integer> options = findOptions(n);
        for (int i=1; i<=n; i++) {
            for (int option: options) {
                if (i-option >= 0) {
                    dp[i] = Math.min(dp[i], dp[i-option]+1);
                } else {
                    break;
                }
            }
        }
        return dp[n];
    }

    private List<Integer> findOptions(int n) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i*i <= n) {
            ans.add(i*i);
            i++;
        }
        return ans;
    }
}

class Solution {
    // a similar version
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n);
        
        int numofPS = (int) Math.sqrt(n);
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=numofPS; j++) {
                int square = j * j;
                if (i == square) {
                    dp[i] = 1;
                } else if (i > square) {
                    dp[i] = Math.min(dp[i], dp[i-square] + 1);
                }
            }
        }
        return dp[n];
    }
}