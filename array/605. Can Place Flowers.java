class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // flowerbed itself is valid at the beginning
        // greedy: just find an available spot (i.e. three 0s) then plant it
        // a special case: at both ends, [0,0,1] and [1,0,0] are available spots
        if (n == 0) return true;
        if (flowerbed.length == 1) {
            return n == 1 && flowerbed[0] == 0;
        }
        
        int i = 0; // i where we want to plant
        while (n > 0 && i < flowerbed.length) {
            if (validSpot(flowerbed, i)) {
                flowerbed[i++] = 1;
                n--;
            } else {
                i++;
            }
        }
        return n == 0;
    }
    
    private boolean validSpot(int[] arr, int index) {
        if (index == 0) {
            return arr[index] == 0 && arr[index+1] == 0;
        }
        
        if (index == arr.length - 1) {
            return arr[index] == 0 && arr[index-1] == 0;
        }
        
        // normal case, has two neighbours
        return arr[index-1] == 0 && arr[index] == 0 && arr[index+1] == 0;
    }
}