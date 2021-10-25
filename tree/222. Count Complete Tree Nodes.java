class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        int leftDepth = findLeft(root);
        int rightDepth = findRight(root);
        if (leftDepth == rightDepth) {
            return (int) Math.pow(2, leftDepth) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    
    private int findLeft(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }
    
    private int findRight(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.right;
            depth++;
        }
        return depth;
    }   
}