class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        backtracking(ans, root, targetSum, 0, new ArrayList<>());
        return ans;
    }
    
    private void backtracking(List<List<Integer>> ans, TreeNode node, int target, int sum, List<Integer> path) {
        // if we do ans.add(new ArrayList<>(path)) in the base case
        // answer will be doubled, because each leaf has two null children
        // we should do the check in advance when node is a leaf node
        if (node == null) return;
        
        path.add(node.val);
        if (sum + node.val == target && node.left == null && node.right == null) {
            ans.add(new ArrayList<>(path));
        } else {
            backtracking(ans, node.left, target, sum + node.val, path);
            backtracking(ans, node.right, target, sum + node.val, path);
        }
        path.remove(path.size()-1);
    }
}