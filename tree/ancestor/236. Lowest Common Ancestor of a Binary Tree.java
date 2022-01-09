class Solution {
    TreeNode ancestor;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findNodes(root, p, q);
        return ancestor;
    }
    
    private int findNodes(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return 0;
        int count = findNodes(node.left, p, q) + findNodes(node.right, p, q);
        
        if (node == p || node == q) count++;
        if (count == 2 && ancestor == null) ancestor = node;
        return count;
    }
}