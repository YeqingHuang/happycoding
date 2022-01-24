class Solution {
    public int numberOfCleanRooms(int[][] room) {
        // it's ok we visit the same cell as long as the directions are different
        int m = room.length;
        int n = room[0].length;
        boolean[][][] visited = new boolean[m][n][4];
        
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1},{-1, 0}};
        int currDir = 0;
        int count = 0;
        int i = 0, j = 0; // starting point
       
        while (!visited[i][j][currDir]) {
            // we can explore this cell
            // but we only increment count if this is the first-time visit
            if (!visited[i][j][0] && !visited[i][j][1] && !visited[i][j][2] && !visited[i][j][3]) {
                count++;
            }
            visited[i][j][currDir] = true;
            
            // check if we can continue with this direction
            int x = i + dirs[currDir][0];
            int y = j + dirs[currDir][1];
            if (x >=0 && x<m && y >=0 && y <n && room[x][y] == 0) {
                // this is a cell without obstacle, make the move
                i = x;
                j = y;
            } else {
                currDir = (currDir + 1) % 4;
            }
        }
        return count;
    }
}