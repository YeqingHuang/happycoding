class Solution {
    public boolean canReach(int[] arr, int start) {
        // 1. you can jump back and forth, but the step is fixed
        // 2. the ending points are not fixed(any possible index with value 0 is ok)

        // BFS: each position i is a node in a graph
        // once we reach a node with value = 0, we can stop and return true
        // if every reachable node is visited(we enter a cycle), return false
        // no need to use another adjacent list, just use arr to jump
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length]; // detect cycle
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (arr[curr] == 0) return true;
            
            int pos1 = curr - arr[curr];
            int pos2 = curr + arr[curr];
            if (pos1 >= 0 && !visited[pos1]) {
                queue.offer(pos1);
                visited[pos1] = true;
            }
            if (pos2 < arr.length && !visited[pos2]) {
                queue.offer(pos2);
                visited[pos2] = true;
            }
        }
        return false;
    }
}