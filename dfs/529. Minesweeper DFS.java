class Solution {
    // A safe cell is clicked:
    // 1) no mine in 8 neighbours, change it from 'E' to 'B'
    //    all of its adjacent unrevealed squares should be revealed recursively
    // 2) has at least 1 mine, change it to the corresponding number and stop
    // a bomb is clicked:
    // game over. Change it from 'M' to 'X', then return
    final int[][] dirs = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            dfs(board, x, y);
        }
        return board;
    }
    
    private void dfs(char[][] board, int i, int j) {
        if (i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j] != 'E') {
            // if this cell has been revealed, i.e. a number or 'B'
            // or it is a bomb, just return
            return;
        } 
        
        int mineCount = findNeighbourMines(board, i, j);
        if (mineCount > 0) {
            // we can stop here
            board[i][j] = (char)(mineCount + '0');
        } else {
            board[i][j] = 'B';
            for (int[] dir: dirs) {
                dfs(board, i+dir[0], j+dir[1]);
            }
        }
    }
    
    private int findNeighbourMines(char[][] board, int i, int j) {
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