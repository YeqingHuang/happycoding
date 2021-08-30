class Solution {
    public boolean exist(char[][] board, String word) {
        // dfs, try every cell as the starting point
        int m = board.length;
        int n = board[0].length;
        // instead of using additional O(mn) space
        // we can modify every used cell as "*" to prevent revisiting
        boolean[][] used = new boolean[m][n];
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                boolean result = search(board, word, 0, i, j, used);
                if (result) return true;
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, String word, int start, int i, int j, boolean[][] used) {
        if (start == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (used[i][j] || board[i][j] != word.charAt(start)) {
            return false;
        }
        
        used[i][j] = true;
        boolean result = search(board, word, start+1, i+1, j, used) ||
                search(board, word, start+1, i-1, j, used) ||
                search(board, word, start+1, i, j+1, used) ||
                search(board, word, start+1, i, j-1, used);
        used[i][j] = false;
        return result;
    }
}