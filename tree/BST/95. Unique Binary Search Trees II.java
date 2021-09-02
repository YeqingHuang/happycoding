class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        } else {
            return getTrees(1, n);
        }
    }
    
    private List<TreeNode> getTrees(int low, int high) {
        List<TreeNode> ans = new ArrayList<>();
        
        // base case is not low = high, it's low > high, i.e. null node
        if (low > high) {
            ans.add(null);
            return ans;
        }
        
        for (int rootVal = low; rootVal <= high; rootVal++) {
            List<TreeNode> leftSubtrees = getTrees(low, rootVal-1);
            List<TreeNode> rightSubtrees = getTrees(rootVal+1, high);
            // cartesian product, generate trees according to the two lists
            for (TreeNode leftNode: leftSubtrees) {
                for (TreeNode rightNode: rightSubtrees) {
                    TreeNode root = new TreeNode(rootVal);
                    root.left = leftNode;
                    root.right = rightNode;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}