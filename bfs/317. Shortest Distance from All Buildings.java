class Solution {
    
    private int[][] grid;
    private int m, n;
    
    public int shortestDistance(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        List<int[][]> dist = new ArrayList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    dist.add(findDistMatrix(i, j));
                }
            }
        }
        
        int minSum = Integer.MAX_VALUE;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 0) {
                    // this could be a spot to build the building
                    int sum = 0;
                    for (int k = 0; k<dist.size(); k++) {
                        int currDist = dist.get(k)[i][j];
                        if (currDist == Integer.MAX_VALUE) {
                            // (i,j) is not suitable for the kth building
                            sum = Integer.MAX_VALUE;
                            break;
                        } else {
                            sum += currDist;
                        }
                    }
                    minSum = Math.min(sum, minSum);
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? - 1: minSum;
    }
    
    private int[][] findDistMatrix(int x, int y) {
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        
        // start from (x,y), use bfs to mark every cell in the dp matrix
        int[][] dp = new int[m][n];
        for (int[] row: dp) {
            // initially, every cell is unreachable
            // consider [[1,2,0]], 0 can never be visited
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new int[]{x,y});
        int step = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];
                dp[currX][currY] = step;
                
                // now check its 4 neighbours
                for (int[] dir: dirs) {
                    int newX = currX + dir[0];
                    int newY = currY + dir[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n 
                        && !visited[newX][newY]) {
                        // even if it's 1 or 2, we mark it as visited
                        visited[newX][newY] = true;
                        if (grid[newX][newY] == 0) {
                            queue.add(new int[]{newX, newY});
                        }
                    }
                }
            }
            step++;
        }        
        return dp;
    }
}