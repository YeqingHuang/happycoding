class Solution {
    final int[][] dirs = new int[][] {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,1},{-1,1},{1,-1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        // if we use BFS, we need a queue to track which cells to be processed
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        board[click[0]][click[1]] = 'B';
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int mineCount = countMines(board, x, y);
            if (mineCount > 0) {
                board[x][y] = (char)(mineCount + '0'); // stop here
            } else {
                // add potential cells to the queue
                for (int[] dir: dirs) {
                    int i = x + dir[0];
                    int j = y + dir[1];
                    if (i >= 0 && i< board.length && j >= 0 && j < board[0].length && board[i][j] == 'E') {
                        queue.offer(new int[]{i,j});
                        // it's important that we modify it as 'B' when we add it
                        // if we modify it after we poll(), a cell may be added more than once
                        // thus TLE
                        board[i][j] = 'B';
                    }
                }
            }
        }
        return board;
    }
    
    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        for (int r=i-1; r<=i+1; r++) {
            for (int c=j-1; c<= j+1; c++) {
                if (r>=0 && r<board.length && c >=0 && c<board[0].length && board[r][c] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }
}