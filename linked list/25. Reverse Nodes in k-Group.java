class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        
        // we move k steps ahead, and stops at the reversed head
        // then do the reversion
        // this new head is the tail of the next reversed part
        return reverseHelper(head, k);
    }
    
    private ListNode reverseHelper(ListNode head, int k) {
        int count = 0;
        ListNode curr = head;
        while (count < k && curr != null) {
            count++;
            curr = curr.next;
        }
        if (count == k) {
            // note that curr is pointing to (k+1)th node
            ListNode newHead = partlyReverse(head, k);
            head.next = reverseHelper(curr, k);
            return newHead;
        } else {
            return head; // not long enough to reverse this part
        }
    }
    
    private ListNode partlyReverse(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        while (k > 0) {
            ListNode temp = curr.next;
            curr.next = prev;
            // move two pointers
            prev = curr;
            curr = temp;
            // change k
            k--;
        }
        return prev;
    }
}