class Solution {
    // method1: O(m+n)
    public boolean searchMatrix(int[][] matrix, int target) {
        // at each position, go up -> smaller, go right -> bigger
        // we can start from bottom-left corner and decide where to go
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int i = matrix.length - 1;
        int j = 0;
        while (i>= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }

    // method2: worst case time complexity O(mlogn)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        if (target < matrix[0][0] || target > matrix[m-1][n-1]) {
            return false;
        }
        
        // check the first number of each row
        // if it satisfies, do binary search
        int i = 0;
        while (i<m) {
            if (matrix[i][0] == target) {
                return true;
            } else if (matrix[i][0] < target) {
                // we can search in this row
                if (searchNum(matrix[i], target)) {
                    return true;
                } else {
                    i++;
                }
            } else {
                // future rows won't satisfy
                return false; 
            }
        }
        return false;
    }
    
    private boolean searchNum(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return true;
            else if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return nums[low] == target || nums[high] == target;
    }
}