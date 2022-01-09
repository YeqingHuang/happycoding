class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        // bfs is easier to understand
        // we calculate curr.getInteger() * level if isInteger()
        // otherwise we add its element getList() to the queue
        int sum = 0;
        int level = 1;
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                NestedInteger curr = queue.poll();
                if (curr.isInteger()) {
                    sum += curr.getInteger() * level;
                } else {
                    queue.addAll(curr.getList());
                }
            }
            level++;
        }
        return sum;
    }
}

class Solution1 {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }
    
    private int dfs(List<NestedInteger> list, int level) {
        int sum = 0;
        for (NestedInteger curr: list) {
            if (curr.isInteger()) {
                sum += curr.getInteger() * level;
            } else {
                sum += dfs(curr.getList(), level + 1);
            }
        }
        return sum;
    }
}