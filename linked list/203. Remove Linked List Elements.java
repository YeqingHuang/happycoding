class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        
        ListNode prev = dummyHead;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == val) {
                // remove curr node
                prev.next = curr.next;
                curr = curr.next; 
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode removeElements1(ListNode head, int val) {
        if (head == null) return null;
        
        if (head.val == val) {
            return removeElements(head.next, val);
        } else {
            head.next = removeElements(head.next, val);
            return head;
        }
    }
}