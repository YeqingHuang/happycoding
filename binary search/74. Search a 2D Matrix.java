class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        if (target < matrix[0][0] || target > matrix[matrix.length-1][matrix[0].length-1]) {
            return false;
        }
        int row = searchInCol(matrix, target);
        return searchInRow(matrix[row], target);
    }
    
    private int searchInCol(int[][] matrix, int target) {
        // find the row index where row[0] is equal to target or smaller than target
        int low = 0;
        int high = matrix.length -1;
        while (low < high - 1) {
            int mid = (low + high)/2;
            if (matrix[mid][0] == target) {
                return mid;
            } else if (matrix[mid][0] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if (matrix[high][0] <= target) {
            return high;
        } else if (matrix[low][0] <= target) {
            return low;
        }
        return -1;
    }
    
    private boolean searchInRow(int[] arr, int target) {
        // common binary search
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}