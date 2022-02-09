class Solution {    
    private int[][] dp;
    
    public int twoEggDrop(int n) {
        // what's the pattern?
        // case1: the first egg breaks, we narrow down the range to [0,i-1]
        // since we only have one egg now, we cannot afford to break it
        // we have to switch to linear, i.e. at most i-1 operations
        // case2: the first egg does not break, now the range is [i+1, n]
        // in this case, we recursively call to find the answer
        
        // since we use recursion, we'd better introduce memoization
        // i.e. dp[i][j] stands for how many operations it takes
        // when there are i floors and j eggs left
        int eggs = 2;
        dp = new int[n+1][eggs+1];
        return dropHelper(n, eggs);
    }
    
    private int dropHelper(int floors, int eggs) {
        if (eggs == 1) return floors; // we cannot take the risk
        if (floors <= 1) return floors;
        
        if (dp[floors][eggs] > 0) return dp[floors][eggs];
        
        int moves = floors;
        for (int i=1; i<=floors; i++) {
            int broken = dropHelper(i-1, eggs - 1);
            int safe = dropHelper(floors - i, eggs);
            // we drop at floor i, this step takes 1 move
            // we also take both cases into consideration, it's Math.max(broken, safe)
            moves = Math.min(moves, 1 + Math.max(broken, safe));
        }
        dp[floors][eggs] = moves;
        return moves;
    }  
}