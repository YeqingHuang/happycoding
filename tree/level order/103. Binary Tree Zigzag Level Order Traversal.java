class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        Queue<TreeNode> queue = new LinkedList<>();
        boolean reverseLevel = false;
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0; i<levelSize; i++) {
                TreeNode curr = queue.poll();
                list.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            if (reverseLevel) {
                Collections.reverse(list);
            }
            ans.add(list);
            reverseLevel = !reverseLevel;
        }
        return ans;
    }
}

class Solution2 {
    // similar to method1, bfs
    // the only difference is that we use a linkedlist to addLast(), addFirst()
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        Queue<TreeNode> queue = new LinkedList<>();
        boolean reverseLevel = false;
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i=0; i<levelSize; i++) {
                TreeNode curr = queue.poll();
                if (!reverseLevel) {
                    list.addLast(curr.val);
                } else {
                    list.addFirst(curr.val);
                }

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            ans.add(list);
            reverseLevel = !reverseLevel;
        }
        return ans;
    }
}