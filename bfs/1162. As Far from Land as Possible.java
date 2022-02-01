class Solution {
    public int maxDistance(int[][] grid) {
        // Manhattan distance is the steps taken to travel from an island to a water cell
        // start from the island cells simutaneously and use bfs to "infect" the water cells
        // when no more water cells can be infected, this process comes to an end
        int n = grid.length;
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if (grid[i][j] == 1) {
                     queue.offer(new int[]{i,j});
                } 
            }
        }
        
        if(queue.size() == 0 || queue.size() == n*n ) return -1;
        
        int[][] dirs={{0,1},{0,-1},{1,0},{-1,0}};
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int[] curr = queue.poll();
                for(int[] dir:dirs) {
                    int x = curr[0]+dir[0];
                    int y = curr[1]+dir[1];
                    if (x>=0 && y>=0 && x<n && y<n && grid[x][y] == 0) {
                        grid[x][y] = -1; // visited water cells
                        queue.offer(new int[]{x,y});
                    }
                }
            }
            dist++;
        }
        return dist-1;
    }
}