class Solution {
    public int nextGreaterElement(int n) {
        // 1) consider 556, next greater is 565 -> refer to 31. Next Permutation
        // 2) consider 11111, or 321, or 9, no answer -> non-inscreasing or has length = 1
        if (n < 10) return -1;   
        
        String strNum = String.valueOf(n);
        int[] digits = new int[strNum.length()];
        for(int i=0; i<digits.length; i++) {
            digits[i] = strNum.charAt(i) - '0';
        }
        if (!increasing(digits)) return -1;
        
        getNextPermutation(digits);
        // note that the revised array may overflow
        long ans = 0;
        for (int digit: digits) {
            ans = ans * 10 + digit;
        }
        if (ans > Integer.MAX_VALUE) return -1;
        return (int) ans;
    }
    
    private void getNextPermutation(int[] digits) {
        // consider 342651, next greater is 345126
        // the peak is at index 3, i.e.number 6, then number at index 2 needs to be replaced
        // how can replace it? the next bigger digit, i.e. number 5 at index 4
        // after we swap them, we get 34 5 621
        // the last task is to reverse the right part, i.e. 34 5 126
        int i = digits.length - 1;
        while (i>0 && digits[i-1] >= digits[i]) {
            i--;
        }
        // now the peak is at i, we need to replace digits[i-1]
        int index = findNextBigger(digits, i-1);
        swap(digits, i-1, index);
        reversePart(digits, i, digits.length - 1);
    }
    
    private int findNextBigger(int[] arr, int start) {
        for (int i=arr.length - 1; i > start; i--) {
            if (arr[i] > arr[start]) {
                return i;
            }
        }
        return -1; // won't reach
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private void reversePart(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
  
    private boolean increasing(int[] digits) {
        for (int i=1; i<digits.length; i++) {
            if (digits[i] > digits[i-1]) {
                return true;
            }
        }
        return false;
    }
}