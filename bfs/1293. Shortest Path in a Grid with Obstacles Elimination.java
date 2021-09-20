class Solution {
    final static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    
    public int shortestPath(int[][] grid, int k) {
        // thoughts:
        // 1. shortest path problem: bfs
        // 2. how to reflect k obstacles? keep the num of remaining chances we have
        //    when we meet 1, if we don't remove it, we go no where, therefore remove it
        // NOTE: we may arrive at the same cell with different paths
        // if the path is different, then k may be different, visited is a 3D instead of 2D
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][][] visited = new boolean[m][n][k+1];
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0][k] = true;
        queue.add(new int[]{0,0,k});
        int step = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                int remaining = curr[2];
                if (x == m-1 && y == n - 1) return step;
                
                for (int[] dir: dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY][remaining]) {
                        // we can visit this cell
                        if (grid[newX][newY] == 0) {
                            for (int index = remaining; index >= 0; index--) {
                                visited[newX][newY][index] = true;
                            }
                            queue.add(new int[]{newX, newY, remaining});
                        } else if (grid[newX][newY] == 1 && remaining > 0) {
                            for (int index = remaining-1; index >= 0; index--) {
                                visited[newX][newY][index] = true;
                            }
                            queue.add(new int[]{newX, newY, remaining-1});
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }    
}