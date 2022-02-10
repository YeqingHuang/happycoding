class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        
        // step1: we move k steps ahead, and stops at head of the next chunk
        // step2: do the reversion
        return reverseHelper(head, k);
    }
    
    private ListNode reverseHelper(ListNode head, int k) {
        int step = 0;
        ListNode curr = head;
        while (step < k && curr != null) {
            curr = curr.next;
            step++;
        }
        if (step == k) {
            // now the tail of this chunk becomes the new head
            // the old head becomes the tail and points to curr
            // note that we should not change the sequence of the next two lines
            ListNode newHead = reverseChunk(head, k);
            head.next = reverseHelper(curr, k);
            return newHead;
        } else {
            return head; // k is bigger than the length of this chunk
        }
    }
    
    private ListNode reverseChunk(ListNode node, int k) {
        ListNode prev = null;
        ListNode curr = node;
        while (k > 0) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            k--;
        }
        return prev;
    }
}