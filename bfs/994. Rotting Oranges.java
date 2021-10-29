class Solution {
    public int orangesRotting(int[][] grid) {
        // step1: count the fresh oranges and collect rotten ones
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int fresh = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i,j});
                }
            }
        }
        
        if (fresh == 0) return 0;
        // step2: bfs
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int[] curr = queue.poll();
                for (int[] dir: dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == 1 && !visited.contains(x*n + y)) {
                            // this one will be affected
                            queue.add(new int[]{x,y});
                            visited.add(x*n + y);
                        }
                    }
                }
            }
            step++;
        }
        // the last time we use step++ is unnecessary, so we return step-1
        return visited.size() == fresh ? step-1 : -1;
    }
}