class Solution {
    
    private int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        // every path has a root, the length is its maxDepthLeft + maxDepthRight
        // when we do dfs, we compare it with the global maxDiameter
        // also, when we go upper, i.e. this node no longer serves as the root
        // find Math.max(depthLeft, depthRight) + 1, so it is the one-side maxDepth
        ans = 0;
        dfs(root);
        return ans;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int maxLeft = dfs(node.left);
        int maxRight = dfs(node.right);
        ans = Math.max(ans, maxLeft + maxRight);
        return 1 + Math.max(maxLeft, maxRight);
    }
}