class Solution {
    // bfs
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = bfs(i, j, grid);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
    
    private int bfs(int i, int j, int[][] grid) {
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
        grid[i][j] = 2; // mark as visited
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir: dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
                   && grid[x][y] == 1) {
                    queue.offer(new int[]{x,y});
                    grid[x][y] = 2;
                    count++;
                }
            }
        }
        
        return count;
    }
}

class Solution {
    // dfs
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                int area = dfs(i, j, grid);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }
    
    private int dfs(int i, int j, int[][] grid) {
        if (i<0 || i>= grid.length || j<0 || j >= grid[0].length) {
            return 0; // out of bound
        }
        if (grid[i][j] == 0) return 0;
        
        grid[i][j] = 0; // mark as visited
        return 1 + dfs(i-1, j, grid) + dfs(i+1, j, grid) + dfs(i, j-1, grid) + dfs(i, j+1, grid);
    }
}