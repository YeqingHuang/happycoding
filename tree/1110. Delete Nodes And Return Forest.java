class Solution {
    private List<TreeNode> ans;
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // thoughts: 
        // 1. it's always easier to solve tree problems recursively
        // 2. if we find a node has no parent, add it to the result
        ans = new ArrayList<>();
        
        Set<Integer> toBeDeleted = new HashSet<>();
        for (int value: to_delete) {
            toBeDeleted.add(value);
        }
        
        if (!toBeDeleted.contains(root.val)) {
            ans.add(root);
        }
        dfs(root, toBeDeleted);
        return ans;
    }
    
    private TreeNode dfs(TreeNode node, Set<Integer> set) {
        // postorder
        if (node == null) return null;
        
        node.left = dfs(node.left, set);
        node.right = dfs(node.right, set);
        if (set.contains(node.val)) {
            if (node.left != null) {
                ans.add(node.left);
            }
            if (node.right != null) {
                ans.add(node.right);
            }
            return null; // i.e. it breaks here
        }
        return node;
    }
}