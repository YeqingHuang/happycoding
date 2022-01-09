// method1: recursive
class Solution {
    int sum; 
    public int rangeSumBST(TreeNode root, int low, int high) {
        sum = 0;
        checkNodes(root, low, high);
        return sum;
    }
    
    private void checkNodes(TreeNode node, int low, int high) { 
        if (node.val >= low && node.val <= high) {
           sum += node.val;
        }
        if (node.left != null && node.val > low) {
            checkNodes(node.left, low, high);
        }
        if (node.right != null && node.val < high) {
            checkNodes(node.right, low, high);
        }
    }
}

// method2: iterative
class Solution {
        
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;

        // use a stack for dfs
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.val >= low && node.val <= high) {
                    sum += node.val;
                }
                if (node.val > low) {
                    stack.push(node.left); // we can explore its left subtree
                }
                if (node.val < high) {
                    stack.push(node.right); // explore its right subtree
                }
            }
        }
        return sum;
    }
}