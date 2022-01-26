class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        // thoughts:
        // given head.val, there are two cases:
        // 1) it matches root.val, the task size has decremented by 1
        //    we move to head.next and try with root.left and root.right
        // 2) the two values do not match, the task size is unchanged
        //    we keep head and try root.left and root.right
        // note that when case 1) happens, case 2) is still an option
        
        // base case
        if (head == null) return true;
        if (root == null) return false;
        
        return matchHere(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }
    
    private boolean matchHere(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        
        if (head.val != root.val) return false;
        return matchHere(head.next, root.left) || matchHere(head.next, root.right);
    }
}