class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        
        // it's much easier if we convert listnode to array
        // and make use of the indexes to form the answer
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        
        return convertToBST(nums, 0, nums.size()-1);
    }
    
    private TreeNode convertToBST(List<Integer> nums, int left, int right) {
        if (left > right) return null;
        if (left == right) return new TreeNode(nums.get(left));
        
        int mid = (left + right)/2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = convertToBST(nums, left, mid-1);
        root.right = convertToBST(nums, mid+1, right);
        
        return root;
    }
}