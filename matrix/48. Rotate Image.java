class Solution {
    public void rotate(int[][] matrix) {
        // reverse each row, with example 1
        // [3 2 1]                       [7 4 1]
        // [6 5 4]  -> flip by diagonal  [8 5 2]
        // [9 8 7]                       [9 6 3]
        
        // or first flip by diagonal, then reverse each row
        // [1 4 7]      [7 4 1]
        // [2 5 8]  ->  [8 5 2]
        // [3 6 9]      [9 6 3]
        
        int n = matrix.length;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                // a num at matrix[i][j] will be placed at matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        for (int[] row: matrix) {
            reverseArray(row);
        }
    }
    
    private void reverseArray(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}