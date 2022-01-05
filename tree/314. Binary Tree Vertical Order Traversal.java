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
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        List<NodeInfo> nodes = new ArrayList<>();
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(root, 0, 0));
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++) {
                NodeInfo curr = queue.poll();
                nodes.add(curr);
                if (curr.node.left != null) {
                    queue.add(new NodeInfo(curr.node.left, curr.row + 1, curr.col - 1));
                }
                if (curr.node.right != null) {
                    queue.add(new NodeInfo(curr.node.right, curr.row + 1, curr.col + 1));
                }
            }
        }
        
        Collections.sort(nodes, (a,b) -> a.col == b.col ? a.row - b.row : a.col - b.col);
        
        int currCol = nodes.get(0).col;
        List<Integer> oneColumn = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (NodeInfo info: nodes) {
            if (info.col == currCol) {
                oneColumn.add(info.node.val);
            } else {
                ans.add(new ArrayList<>(oneColumn));
                // reset variables
                oneColumn.clear();
                currCol = info.col;
                oneColumn.add(info.node.val);
            }
        }
        ans.add(new ArrayList<>(oneColumn));
        return ans;
    }
}

class Solution1 {
    
    class NodeInfo {
        TreeNode node;
        int col; 
        
        public NodeInfo(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }     
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        Map<Integer, List<Integer>> map = new HashMap<>(); // key is col
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(root,0));
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++) {
                NodeInfo curr = queue.poll();
                int currCol = curr.col;
                minCol = Math.min(currCol, minCol);
                maxCol = Math.max(currCol, maxCol);
                map.putIfAbsent(currCol, new ArrayList<>());
                map.get(currCol).add(curr.node.val);
                
                if (curr.node.left != null) {
                    queue.add(new NodeInfo(curr.node.left, curr.col - 1));
                }
                if (curr.node.right != null) {
                    queue.add(new NodeInfo(curr.node.right, curr.col + 1));
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int c = minCol; c <= maxCol; c++) {
            ans.add(map.get(c));
        }
        return ans;
    }
