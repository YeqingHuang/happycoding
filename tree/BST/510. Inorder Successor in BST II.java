class Solution {
    public Node inorderSuccessor(Node node) {
        // case1: has a right subtree -> leftmost child in its right subtree
        // case2: has no right subtree -> go up until current node becomes a left child
        // case3: if case2 is not possible, then this is the biggest node, return null
        if (node == null) return node;
        
        if (node.right != null) {
            return findSmallestFromRight(node);
        }
        
        Node curr = node;
        Node parent = node.parent;
        while (parent != null) {
            if (parent.left == curr) {
                return parent;
            } else {
                curr = parent;
                parent = curr.parent;
            }
        }
        return null;
    }
    
    private Node findSmallestFromRight(Node node) {
        Node prev = null;
        Node curr = node.right;
        while (curr != null) {
            prev = curr;
            curr = curr.left;
        }
        return prev;
    }
}

class Solution {
    // a much cleaner version
    public Node inorderSuccessor(Node node) {
        if (node == null) return node;
        
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        
        Node curr = node;
        Node parent = node.parent;
        while (node.parent != null && node != node.parent.left) {
            node = node.parent;
        }
        return node.parent; // this also covers case3
    }
}