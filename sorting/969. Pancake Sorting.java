class Solution {
    List<Integer> operations;
    
    public List<Integer> pancakeSort(int[] arr) {
        // [5,3,1,4,2] -> [2,4,1,3,5] -> [4,2,1,3,5] -> [3,1,2,4,5]
        // recursive approach: find the biggest one in the unsorted subarray
        // suppose it's index i, first flip (0,i) to get it to the head
        // then flip (0, k) where k is the last unsorted index
        // repeat this process until k = 0
        operations = new ArrayList<>();
        for (int k = arr.length - 1; k > 0; k--) {
            flipArray(arr, k); // the range is from [0,k]
        }
        return operations;
    }
    
    private void flipArray(int[] arr, int k) {        
        // we focus on the subarray [0,k]
        // step1: find the biggest
        int index = -1;
        int max = 0;
        for (int i = 0; i<= k; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        if (index == k) return;
        
        // step2: flip it to the head
        reverseArray(arr, 0, index);
        operations.add(index + 1);
        
        // step3: flip it to the tail
        reverseArray(arr, 0, k);
        operations.add(k + 1);
    }
    
    private void reverseArray(int[] arr, int i, int j) {
        while (i<j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}