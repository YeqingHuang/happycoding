class Solution {
    private TreeNode ancestor;
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // the returned node must be the ancestor of ALL the deepest leaves
        if (root.left == null && root.right == null) return root;
        
        Set<TreeNode> deepestLeaves = getDeepestLeaves(root);
        findAncestor(root, deepestLeaves);
        return ancestor;
    }
    
    private int findAncestor(TreeNode node, Set<TreeNode> targets) {
        if (node == null) return 0;
        
        int count = 0;
        if (targets.contains(node)) {
            count++;
        }
        count += findAncestor(node.left, targets);
        count += findAncestor(node.right, targets);
        if (count == targets.size() && ancestor == null) {
            ancestor = node;
        }
        return count;
    }
    
    private Set<TreeNode> getDeepestLeaves(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Set<TreeNode> nodesAtLevel = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            nodesAtLevel.clear();
            for (int i=0; i<size; i++) {
                TreeNode curr = queue.poll();
                nodesAtLevel.add(curr);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return nodesAtLevel;
    }
}

class Solution {
    int maxDepth;
    TreeNode ancestor;
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        this.maxDepth = 0;
        helper(root, 0);
        return ancestor;
    }
    
    private int helper(TreeNode node, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        
        // we add an additional level of null nodes, it won't change the answer
        if (node == null) return depth;
        
        int leftD = helper(node.left, depth + 1);
        int rightD = helper(node.right, depth + 1); 
        if (leftD == maxDepth && rightD == maxDepth) {
            ancestor = node;
        }
        
        return Math.max(leftD, rightD);
    }
}