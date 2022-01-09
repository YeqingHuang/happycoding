class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // level order traversal, use a flag to indicate if next level should be reversed or not
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        boolean reversed = true; // for next level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i=0; i<levelSize; i++) {
                TreeNode curr = queue.poll();
                levelList.add(curr.val);
                if (curr.right != null) queue.offer(curr.right);
                if (curr.left != null) queue.offer(curr.left);
            }
            if (reversed) {
                Collections.reverse(levelList);
            }
            ans.add(levelList);
            reversed = !reversed;
        }
        return ans;
    }
}