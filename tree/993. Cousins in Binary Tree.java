class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        // if there are on different levels, not cousin
        // same level but same parent, not cousin
        // same level and different parents, cousin!
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean foundOne = false;
            for (int i=0; i<levelSize; i++) {
                TreeNode curr = queue.poll();
                // check siblings
                if (curr.left != null && curr.right != null) {
                    if ((curr.left.val == x && curr.right.val == y)
                        || (curr.left.val == y && curr.right.val == x)) {
                        return false;
                    } 
                } 
                // add to be processed in the next round
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
                // check cousins
                if (curr.val == x || curr.val == y) {
                    if (!foundOne) {
                        foundOne = true;
                    } else {
                        return true;
                    }
                }
            }
            
        }
        return false;
    }
}