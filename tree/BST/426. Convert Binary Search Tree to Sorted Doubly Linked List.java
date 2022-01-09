class Solution {
    // re-use the head and the tail
    // when we finish recursion, they point to the real head and tail
    Node head, tail;
    
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        
        connectHelper(root);
        // close DLL
        tail.right = head;
        head.left = tail;
        return head;
    }
    
    private void connectHelper(Node node) {
        if (node == null) return;
        
        // dfs: inorder
        // current node's left should be the tail of left subtree
        // current node's right should be the head of the right subtree        
        connectHelper(node.left);
        
        if (tail != null) {
            tail.right = node;
            node.left = tail;
        } else {
            // no left subtree, node itself is the smallest
            head = node;
        }
        tail = node;
        
        connectHelper(node.right);
    } 
}