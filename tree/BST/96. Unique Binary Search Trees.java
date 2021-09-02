class Solution {
    Map<Integer, Integer> map;
    
    public int numTrees(int n) {
        // suppose n = 5 and we choose num = 2 as the root
        // BST rule: 1 must be in its left subtree, 3,4,5 must be in its right subtree
        // use i as root.val, left child value can choose from [1,i-1], i.e. i-1 choices
        // right child value can choose from [i+1, n], i.e. n-i choices
        
        // we can tell there are subproblems that might be repeatedly calculated 
        // use a hashmap to store: key is value of root, value is number of unique trees
        map = new HashMap<>();
        return findHelper(n);
    }
    
    private int findHelper(int n) {
        if (n <= 1) return 1;
        
        if (map.containsKey(n)) {
            return map.get(n);
        }
        
        int count = 0;
        for (int i=1; i<=n; i++) {
            // let root.val = i
            int leftCount = findHelper(i-1);
            int rightCount = findHelper(n-i);
            count += leftCount * rightCount;
        }
        
        map.put(n, count);
        return count;
    }
}

class Solution1 {
    // a cleaner version
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            for (int rootVal = 1; rootVal <= i; rootVal++) {
                dp[i] += dp[rootVal - 1] * dp[i - rootVal];
            } 
        }
        return dp[n];
    }
}