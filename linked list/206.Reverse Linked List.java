class Solution {
    // iterative
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            // rebuild next pointer
            ListNode temp = curr.next;
            curr.next = prev;
            // move prev and curr
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    // recursive
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode second = head.next;
        ListNode newHead = reverseList(second);
        second.next = head;
        head.next = null;
        
        return newHead;
    }
}