class Solution {
    Map<Integer, List<TreeNode>> map;
    int maxHeight;
    public List<List<Integer>> findLeaves(TreeNode root) {
        // the most out layer has a height of 1
        // the second layer has a height of 2
        // if we use dfs to traverse the tree and store the height information is a map
        // we can use it to construct answer
        map = new HashMap<>();
        maxHeight = 0;
        findHeight(root);
        
        List<List<Integer>> ans = new ArrayList<>();
        for (int h = 1; h<= maxHeight; h++) {
            List<Integer> layer = new ArrayList<>();
            for (TreeNode node: map.get(h)) {
                layer.add(node.val);
            }
            ans.add(layer);
        }
        return ans;
    }
    
    private int findHeight(TreeNode node) {
        if (node == null) return 0;
        
        int height = 1 + Math.max(findHeight(node.left), findHeight(node.right));
        maxHeight = Math.max(maxHeight, height);
        map.putIfAbsent(height, new ArrayList<>());
        map.get(height).add(node);
        
        return height;
    }
}