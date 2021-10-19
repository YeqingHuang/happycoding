class Solution {
    // try every node as the root, there are n nodes
    // in each attempt, depth depends on the height of the tree, O(logn) to O(n)
    // so time complexity is O(nlogn) ~ O(n^2)
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        
        return pathSum(root.left, targetSum) + pathSum(root.right, targetSum) +
            pathWithRoot(root, targetSum);
    }
    
    private int pathWithRoot(TreeNode node, int targetSum) {
        if (node == null) return 0;
        
        int ans = 0;
        if (node.val == targetSum) {
            ans++;
        }
        return ans + pathWithRoot(node.left, targetSum - node.val) + 
            pathWithRoot(node.right, targetSum - node.val);
    }
}

class Solution1 {
    // prefix sum, use a hashmap to store
    // key is prefixSum, value is its count
    private int ans;
    private HashMap<Integer, Integer> map;
    
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        
        this.ans = 0;
        this.map = new HashMap<>();
        map.put(0,1); // important! we have an initial prefixSum 0
        backtracking(root, targetSum, 0);
        return ans;
    }
    
    private void backtracking(TreeNode node, int targetSum, int currSum) {
        if (node == null) return;
        
        // step1: update current accumulated sum and the map
        currSum += node.val;
        // step2: check if we contribute to the answer
        // in a path 1->2->4->5, current prefixSum is 12, target is 9
        // if we found 3 in the dictionary, we get a valid path 4->5 
        ans += map.getOrDefault(currSum - targetSum, 0);
        // step3: update map
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        // recursive call
        backtracking(node.left, targetSum, currSum);
        backtracking(node.right, targetSum, currSum);
        
        // restore
        int oldCount = map.get(currSum) - 1;
        map.put(currSum, oldCount);
    }
}