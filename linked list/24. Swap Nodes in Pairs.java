class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummyHead = new ListNode(0, head);
        ListNode curr = dummyHead;
        while (curr != null) {
            swapNextTwo(curr);
            // move two steps
            curr = curr.next;
            if (curr != null) curr = curr.next;
        }
        return dummyHead.next;
    }
    
    private void swapNextTwo(ListNode curr) {
        ListNode first = curr.next;
        if (first == null || first.next == null) return;
        ListNode second = first.next;
        
        curr.next = second;
        ListNode third = second.next;
        second.next = first;
        first.next = third;
    }
}

class Solution {
    // based on the same idea
    // recursive way is cleaner than iterative way
    public ListNode swapPairs(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head; 
        }
        
        ListNode first = head;
        ListNode second = head.next;
        ListNode third = head.next.next;
        
        second.next = first;
        first.next = swapPairs(third);
        return second;
    }
}