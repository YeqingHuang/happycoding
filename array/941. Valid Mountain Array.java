class Solution {
    public boolean validMountainArray(int[] arr) {
        // 1. length >= 3
        // 2. only one max value (not multiple)
        // 3. numbers before it should be monotonically increasing
        //    numbers after it should be monotonically decreasing
        if (arr.length < 3) return false;
        
        int maxIndex = 0;
        int maxCount = 1;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
                maxCount = 1;
            } else if (arr[i] == arr[maxIndex]) {
                maxCount++;
            }
        }
        if (maxCount > 1 || maxIndex == 0 || maxIndex == arr.length - 1) {
            return false;
        }
        
        for (int i=1; i<maxIndex; i++) {
            // this part should be increasing
            if (arr[i] <= arr[i-1]) {
                return false;
            }
        }
        for (int i=maxIndex+1; i<arr.length; i++) {
            if (arr[i] >= arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    // one pass solution
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) return false;
        
        // simulate how we clime a mountain
        // once we meet a local peak, we should check if it's decreasing
        if (arr[1] <= arr[0]) return false;
        
        boolean increasing = true;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                return false; // we don't allow same values
            } 
            
            if (increasing) {
                // we meet a local peak, change the flag
                if (arr[i] < arr[i-1]) increasing = false;
                // else still increasing, just keep going
            } else {
                // it should be decreasing now
                if (arr[i] > arr[i-1]) return false;
            }
        }
        // one edge case: we don't want the whole array to be increasing
        return !increasing;
    }
}