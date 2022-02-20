class Solution {
    int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        // if one node appears in the max path:
        // case1: it serves as the root:
        //        sum = node.val + leftChildSum + rightChildSum
        // case2: it's on the left/right subpath and still needs to go up
        //        val = node.val + Math.max(leftChildSum, rightChildSum)
        findMax(root);
        return maxSum;
    }
    
    private int findMax(TreeNode node) {
        // do two things:
        // 1) return node.val + Math.max(leftChildSum, rightChildSum)
        // 2) update the global maxSum
        if (node == null) return 0;
        
        // if findMax(node.left) or findMax(node.right) is negative
        // there's no benefit to include it, just use 0
        int leftSum = Math.max(0, findMax(node.left));
        int rightSum = Math.max(0, findMax(node.right));
        maxSum = Math.max(maxSum, node.val + leftSum + rightSum);
        return node.val + Math.max(leftSum, rightSum);
    }
}