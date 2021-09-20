// O(n) space and O(1) time
class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal, antiDiagonal;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }
    
    public int move(int row, int col, int player) {
        /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
        int val = (player == 1) ? 1 : -1;
        // change the row and the col
        rows[row] += val;
        cols[col] += val;

        // change the two diagonal lines 
        if (row == col) {
            diagonal += val;
        }
        if (col == rows.length - row - 1) {
            antiDiagonal += val;
        }
        
        // check win
        int n = rows.length;
        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n 
           || Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
            return player;
        }
        return 0;
    }
}