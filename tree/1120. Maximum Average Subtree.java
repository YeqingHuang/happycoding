/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    double maxAverage;
    
    public double maximumAverageSubtree(TreeNode root) {
        // we need current count and total to calculate average
        // how to let each node carry these two info?
        recursiveFinder(root);
        return maxAverage;
    }
    
    private int[] recursiveFinder(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] leftSubtree = recursiveFinder(node.left);
        int[] rightSubtree = recursiveFinder(node.right);
        int sum = node.val + leftSubtree[0] + rightSubtree[0];
        int count = 1 + leftSubtree[1] + rightSubtree[1];
        
        maxAverage = Math.max(maxAverage, (double) sum/count);
        return new int[]{sum, count};
    }
}