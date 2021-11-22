class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        // recursive way, tricky part is case4
        // case1: key not found in the tree, just return root
        // case2: a leaf node, just delete it
        // case3: only has one child, replace it with its child value
        // case4: has two children, replace it with the smallest node in the right subtree
        if (root == null) return null;
        
        if (key < root.val) {
            // try to find it in the left subtree
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // current node should be deleted
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // case4
                TreeNode curr = root.right;
                while (curr.left != null) {
                    curr = curr.left;
                }
                // now curr stays at the smallest node in the right subtree
                root.val = curr.val;
                // we should delete this smallest node
                root.right = deleteNode(root.right, curr.val);
            }
        }
        return root;
    }
}

class Solution1 {
    public TreeNode deleteNode(TreeNode root, int key) {
        // iterative way, two steps:
        // 1. locate the target node
        // we also need to record its parent, therefore use prev and curr
        // 2. delete this target node
        // i.e. it's the root of this subtree
        if (root == null) return null;
        
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null && curr.val != key) {
            prev = curr;
            if (key > curr.val) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        if (prev == null) {
            // prev has never got moved, it's the root we want to delete
            return deleteRoot(root);
        } else if (curr == null) {
            // this key does not exist in the tree
            return root;
        } else {
            // curr.val == key
            if (prev.left == curr) {
                prev.left = deleteRoot(curr);
            } else {
                prev.right = deleteRoot(curr);
            }
            return root;
        }
    }
    
    private TreeNode deleteRoot(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        // it has two children, find minNode in its right subtree
        TreeNode minNode = findMinNode(root.right);
        minNode.left = root.left;
        root.left = null;
        return root.right;
    }
    
    private TreeNode findMinNode(TreeNode curr) {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
}