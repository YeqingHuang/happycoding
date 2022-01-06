class Solution {
    // naive method: TLE
    public int largestIsland(int[][] grid) {
        // all 1s, return n*n; all 0s, return 1
        // there must be some zero that's adjacent to a one
        // we only choose these zeros as the candidates and treat them as starting point
        int n = grid.length;
        Set<Integer> candidates = new HashSet<>(); // store i*n + j
        int countZero = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 0) {
                    countZero++;
                    if (adjacentOne(grid, i, j)) {
                        candidates.add(i * n + j);
                    }
                }
            }
        }
        if (countZero == 0 || countZero == 1) return n*n;
        if (countZero == n*n) return 1;
        
        // normal case
        int maxArea = 0;
        for (int candidate: candidates) {
            int r = candidate / n;
            int c = candidate % n;
            maxArea = Math.max(maxArea, findArea(copyMatrix(grid), r, c));
        }
        return maxArea;
    }
    
    private int[][] copyMatrix(int[][] grid) {
        int[][] copied = new int[grid.length][grid[0].length];
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                copied[i][j] = grid[i][j];
            }
        }
        return copied;
    }
    
    private int findArea(int[][] grid, int i, int j) {
        // we only explore the shape that's connected by this cell (r,c)
        // and return its area (using bfs)
        int count = 1;
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
        grid[i][j] = 2; // mark as visited
        
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
    
    private boolean adjacentOne(int[][] grid, int i, int j) {
        int n = grid.length;
        if (i - 1 >= 0 && grid[i-1][j] == 1) return true;
        if (i + 1 < n && grid[i+1][j] == 1) return true;
        if (j - 1 >= 0 && grid[i][j-1] == 1) return true;
        if (j + 1 < n && grid[i][j+1] == 1) return true;
        return false;
    }
}


class Solution1 {
    Map<Integer, Integer> map; // shapeNo, shapeTotalArea
    
    public int largestIsland(int[][] grid) {
        // step1: refer to 200. Number of Islands and 695. Max Area of Island
        // mark each island with a shapeNo and calculate their areas
        // step2: check the candidates, i.e. the 0s
        // it can at most connect 4 shapes and merge them into one shape
        int n = grid.length;
        map = new HashMap<>();
        Set<Integer> candidates = new HashSet<>();
        int shapeNo = 2; // will increment
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    int area = markShape(grid, i, j, shapeNo);
                    map.put(shapeNo, area);
                    shapeNo++;
                } else if (grid[i][j] == 0) {
                    candidates.add(i * n + j); // add this zero as candidate
                } // else already marked with a shapeNo
            }
        }
        
        if (candidates.isEmpty()) {
            return n * n; // all 1s
        }
        
        int maxArea = 0;
        for (int candidate: candidates) {
            int connectedArea = getConnectedArea(grid, candidate/n, candidate%n);
            maxArea = Math.max(connectedArea, maxArea);
        }
        return maxArea;
    }
    
    private int getConnectedArea(int[][] grid, int i, int j) {
        Set<Integer> connected = new HashSet<>();
        int totalArea = 1; // the cell itself has an area of 1
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
        
        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
                        int shapeNo = grid[x][y];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
                   &&  shapeNo != 0 && !connected.contains(shapeNo)) {
                totalArea += map.get(shapeNo);
                connected.add(shapeNo);
            }
        }
        return totalArea;
    }
    
    private int markShape(int[][]grid, int i, int j, int shapeNo) {
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
        int count = 1;
        grid[i][j] = shapeNo;
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
                    grid[x][y] = shapeNo;
                    count++;
                }
            }
        }
        return count;
    }
}