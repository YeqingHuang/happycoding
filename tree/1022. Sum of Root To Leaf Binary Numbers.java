class Solution {
    int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        // for each path before we reach the leaf
        // once we push a new node, the value becomes value << 1 + node.val
        // once we go up one leve and pop a node, the previous value is restored
        findSum(root, 0);
        return sum;
    }
    
    public void findSum(TreeNode node, int value) {
        int updatedValue = (value << 1) + node.val;

        if (node.left == null && node.right == null) {
            sum += updatedValue;
        }
        
        if (node.left != null) findSum(node.left, updatedValue);
        if (node.right != null) findSum(node.right, updatedValue);
    }
}

class Solution1 {
    public int sumRootToLeaf(TreeNode root) {
        // iterative dfs: use a stack
        // we not only need TreeNode, but also need accumuted value
        // so we use a pair
        int sum = 0;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair(root, 0));
        
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> curr = stack.pop();
            TreeNode node = curr.getKey();
            int updatedValue = (curr.getValue() << 1) | node.val;
            
            if (node.left == null && node.right == null) {
                sum += updatedValue;
            } else {
                if (node.left != null) stack.push(new Pair(node.left, updatedValue));
                if (node.right != null) stack.push(new Pair(node.right, updatedValue));
            }
        }
        return sum;
    }
}