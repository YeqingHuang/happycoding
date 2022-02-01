class Solution {
    private int[] nums;
    
    public TreeNode bstFromPreorder(int[] preorder) {
        this.nums = preorder;
        return helper(0, nums.length-1);
    }
    
    private TreeNode helper(int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(nums[start]);
        
        TreeNode root = new TreeNode(nums[start]);
        int leftEnd = findLeftEnd(start, end);
        root.left = helper(start+1, leftEnd);
        root.right = helper(leftEnd+1, end);
        
        return root;
    }
    
    private int findLeftEnd(int start, int end) {
        int index = start;
        while (index + 1 <= end && nums[index+1] < nums[start]) {
            index++;
        }
        return index;
    }
}

class Solution {
    private int index;
    
    public TreeNode bstFromPreorder(int[] preorder) {
        // we don't want to use findLeftEnd helper function
        // how to reduce time complexity from O(n^2) to O(n)?
        // maintain a global index, i.e. which value we are processing now
        this.index = 0;
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode helper(int[] nums, int lower, int upper) {
        if (index == nums.length) return null; // done
        
        if (nums[index] < lower || nums[index] > upper) return null; // we should stop
        
        int rootVal = nums[index++];
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(nums, lower, rootVal);
        root.right = helper(nums, rootVal, upper);
        return root;
    }
}