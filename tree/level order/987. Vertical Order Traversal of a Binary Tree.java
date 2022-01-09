class Solution {
    
    class NodeInfo {
        TreeNode node;
        int row;
        int col; 
        
        public NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }     
    }
    
    // diff with 314. Binary Tree Vertical Order Traversal
    // [1,2,3,4,6,5,7]
    // 314 requires from left to right when multiple nodes are on the same col and same row
    // 987 requires we sort by value, thus it should be [[4],[2],[1,5,6],[3],[7]]
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        Map<Integer, List<NodeInfo>> map = new HashMap<>(); // key is col
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        
        // level order traversal
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(root,0, 0));
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++) {
                NodeInfo curr = queue.poll();
                int currCol = curr.col;
                minCol = Math.min(currCol, minCol);
                maxCol = Math.max(currCol, maxCol);
                map.putIfAbsent(currCol, new ArrayList<>());
                map.get(currCol).add(curr);
                
                if (curr.node.left != null) {
                    queue.add(new NodeInfo(curr.node.left, curr.row + 1, curr.col - 1));
                }
                if (curr.node.right != null) {
                    queue.add(new NodeInfo(curr.node.right, curr.row + 1, curr.col + 1));
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int c = minCol; c <= maxCol; c++) {
            List<NodeInfo> nodes = map.get(c);
            Collections.sort(nodes, (a,b) -> a.row == b.row ? 
                             a.node.val - b.node.val : a.row - b.row);
            List<Integer> list = new ArrayList<>();
            for (NodeInfo info: nodes) {
                list.add(info.node.val);
            }
            ans.add(list);
        }
        return ans;
    }
}