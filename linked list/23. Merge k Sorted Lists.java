class Solution {
    // suppose there are k lists and N nodes
    // each operation of a node takes O(logk)
    // so space is O(k), time is O(nlogk)
    public ListNode mergeKLists(ListNode[] lists) {
        // imagine there are k pointers for the k linked lists
        // we compare k values at a time, pick one to create a node
        // then move the pointer of the picked one
        // this could be implemented by maintain a minHeap of size k
        int k = lists.length;
        if (k == 0) return null;
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        for (ListNode head: lists) {
            if (head != null) minHeap.add(head);
        }
        
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while (!minHeap.isEmpty()) {
            ListNode popped = minHeap.poll();
            if (popped.next != null) {
                minHeap.add(popped.next);
            }
            curr.next = popped;
            curr = curr.next;
        }
        return dummyHead.next;
    }
}