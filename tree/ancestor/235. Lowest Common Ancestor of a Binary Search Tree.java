class Solution {
    TreeNode ancestor;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findLCA(root, p, q);
        return ancestor;
    }
    
    private int findLCA(TreeNode node, TreeNode p, TreeNode q) {
        int count = 0;
        if (node == p || node == q) count++;
        if (node.left != null) count += findLCA(node.left, p, q);
        if (node.right != null) count += findLCA(node.right, p, q);
        if (count == 2 && ancestor == null) {
            ancestor = node;
        }
        return count;
    }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // how to make use of bst?
        int parentVal = root.val;
        int pVal = p.val;
        int qVal = q.val;
        
        // either go to find in the left subtree or right subtree
        // or this node is the answer
        if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}