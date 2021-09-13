class Solution {
    final int[][] DIRS = {{1,-2}, {2,-1}, {2,1}, {1,2}, {-1,2}, {-2,1}, {-2,-1},{-1,-2}};
    
    public int minKnightMoves(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[601][601];
        queue.add(new int[]{0,0});
        visited[300][300] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++) {
                int[] curr = queue.poll();
                if (curr[0] == x && curr[1] == y) {
                    return step;
                }
                for (int[] dir: DIRS) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if (r >= -300 && r <= 300 && c >= -300 && c <= 300 && !visited[r+300][c+300]) {
                        queue.add(new int[]{r,c});
                        visited[r+300][c+300] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}