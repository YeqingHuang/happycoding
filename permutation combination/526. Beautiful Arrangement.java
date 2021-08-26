class Solution {
    int count; // MUST BE put here
    
    public int countArrangement(int n) {
        // two optimizations:
        // 1) check while we generate a path, if it's not valid, break
        // 2) we don't need to actually store the path, just modify used boolean array
        if (n <= 2) return n;
        
        count = 0;
        boolean[] used = new boolean[n+1]; // used[0] has no use
        dfs(1, used); // 1 stands for current index(1-indexed)
        return count;
    }
    
    private void dfs(int index, boolean[] used) {
        int n = used.length - 1;
        if (index == n + 1) {
            count++;
            return;
        }
        
        for (int num = 1; num <= n; num++) {
            if (!used[num] && (num % index == 0 || index % num == 0)) {
                // we decide to put num at position index - 1
                used[num] = true;
                dfs(index + 1, used);
                used[num] = false;
            }
        }
    }
}