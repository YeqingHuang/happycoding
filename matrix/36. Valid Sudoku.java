class Solution {
    public boolean isValidSudoku(char[][] board) {
        // check each row
        Set<Character> seen = new HashSet<>();
        for (int i=0; i<9; i++) {
            seen.clear();
            for (int j=0; j<9; j++) {
                if (board[i][j] != '.') {
                    if (seen.contains(board[i][j])) {
                        return false;
                    } else {
                        seen.add(board[i][j]);
                    }
                }
            }
        }
        
        for (int j=0; j<9; j++) {
            seen.clear();
            for (int i=0; i<9; i++) {
                if (board[i][j] != '.') {
                    if (seen.contains(board[i][j])) {
                        return false;
                    } else {
                        seen.add(board[i][j]);
                    }
                }
            }
        }
        
        Set<Character>[] boxes = new Set[9];
        for (int k =0; k<9; k++) {
            boxes[k] = new HashSet<>();
        }
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                int index = i/3 * 3 + j/3;
                if (board[i][j] != '.') {
                    if (boxes[index].contains(board[i][j])) {
                        return false;
                    } else {
                        boxes[index].add(board[i][j]);
                    }
                }
            }
        }
        return true;
    }
}


class Solution {
    // a much cleaner way(though string + string is more expensive)
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] != '.') {
                    char c = board[i][j];
                    int boxIndex =  i/3 * 3 + j/3;
                    if (!set.add(c + " row " + i) || !set.add(c + " col " + j)
                        || !set.add(c + " box " + boxIndex)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}