class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // we can always insert it as a leaf
        if (root == null) {
            return new TreeNode(val);
        }
        
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            if (val < curr.val) {
                prev = curr;
                curr = curr.left;
            } else {
                prev = curr;
                curr = curr.right;
            }
        }
        TreeNode newNode = new TreeNode(val);
        if (val < prev.val) {
            prev.left = newNode;
        } else {
            prev.right = newNode;
        }
        return root;
    }

    // recursive version
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}