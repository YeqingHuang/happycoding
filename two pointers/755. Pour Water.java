class Solution {
    public int[] pourWater(int[] heights, int volume, int k) {
        // how do we know if a droplet go to left or right
        // we need to use O(1) to know min before index k
        // and use O(1) to know min after index k
        while (volume > 0) {
            dropOne(heights, k);
            volume--;
        }
        return heights;
    }
    
    private void dropOne(int[] h, int index) {
        int leftDest = findMinLeft(h, index);
        if (leftDest != index) {
            h[leftDest]++;
            return;
        }
        
        int rightDest = findMinRight(h, index);
        if (rightDest != index) {
            h[rightDest]++;
            return;
        }
        // no where to go
        h[index]++;
    }
    
    private int findMinLeft(int[] h, int index) {
        // a dropleft cannot overcome a local peak
        // once the height increases, it stops
        int ans = index;
        for (int i=index-1; i>=0; i--) {
            if (h[i] < h[i+1]) {
                ans = i;
            } else if (h[i] > h[i+1]) {
                break;
            } // else == , we still got chance
        }
        return ans;
    }
    
    private int findMinRight(int[] h, int index) {
        int ans = index;
        for (int i=index+1; i<h.length; i++) {
            if (h[i] < h[i-1]) {
                ans = i;
            } else if (h[i] > h[i-1]) {
                break;
            } // else == , we still got chance
        }
        return ans;
    }
}