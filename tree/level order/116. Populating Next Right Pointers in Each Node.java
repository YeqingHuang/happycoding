class Solution {
    // time complexity O(n), space compelxity O(1)
    public Node connect(Node root) {
        // we process the nodes of the next level at current level
        // two cases:
        // 1) leftChildren.next = rightChildren
        // 2) this node's rightChildren.next = next node's leftChildren
        //    node.right.next = node.next.left
        if (root == null) return null;
        
        Node head = root;
        // note that we do the operations for the next level
        // therefore, we need to make sure head.left is not null
        while (head.left != null) {
            // do the connection
            Node curr = head;
            while (curr != null) {
                curr.left.next = curr.right;
                // if this is the last node of the next level, we don't have case2
                if (curr.next != null) curr.right.next = curr.next.left;
                curr = curr.next;
            }
            // move head
            head = head.left;
        }
        return root;
    }
}

// time complexity O(n), space complexity O(n)
// because the queue will store at most half of the n nodes
class Solution {
    public Node connect(Node root) {
        // level-order traversal: easier to understand
        if (root == null) return null;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++) {
                Node curr = queue.poll();
                if (i != levelSize - 1) {
                    curr.next = queue.peek();
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return root;
    }
}