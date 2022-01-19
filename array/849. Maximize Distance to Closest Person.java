class Solution {
    public int maxDistToClosest(int[] seats) {
        // the special case is the two available seats at both ends
        int n = seats.length;
        
        int i = 0;
        while (i < n) {
            if (seats[i] == 1) {
                break; // find the index of the leftMost 1
            } else {
                i++;
            }
        }
        
        int j = n - 1;
        while (j >= 0) {
            if (seats[j] == 1) {
                break; // find the index of the rightMost 1
            } else {
                j--;
            }
        }
        
        int maxDist = 1;
        
        if (seats[0] == 0) {
            maxDist = Math.max(maxDist, i);
        }
        if (seats[n-1] == 0) {
            maxDist = Math.max(maxDist, n - 1 -j);
        }
        int maxMiddle = findMaxMiddle(seats, i, j);

        return Math.max(maxDist, maxMiddle/2);
    }
    
    private int findMaxMiddle(int[] arr, int start, int end) {
        // if it's [0,0,0,1,1,0,0]
        // then there is no max middle, return 0
        if (start == end || start == end - 1) {
            return 0;
        }
        
        int dist = 0;
        int prevOne = start;
        for (int i=start+1; i<=end; i++) {
            if (arr[i] == 1) {
                dist = Math.max(dist, i - prevOne);
                prevOne = i;
            }
        }
        return dist;
    }
}