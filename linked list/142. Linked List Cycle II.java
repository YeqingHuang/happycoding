public class Solution {
    public ListNode detectCycle(ListNode head) {
        // hashset: just follow the pointer and move
        // if we find a node that has been seen before, it's a cycle starting node
        Set<ListNode> seen = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (seen.contains(curr)) {
                return curr;
            } else {
                seen.add(curr);
                curr = curr.next;
            }
        }
        return null;
    }  
}

public class Solution {
    // hare and tortoise,O(1) space, O(n) time
    // phase1: start a fast pointer and a slow pointer until they meet
    // phase2: start a third pointer from the beginning,
    //         when it meets the slow pointer, it's the answer
    // if there is no cycle, we will notice in phase1
    
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null; // no cycle
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode third = head;
        while (third != slow) {
            third = third.next;
            slow = slow.next;
        }
        return third;
    }
}