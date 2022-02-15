class Solution {
    public int maxDistToClosest(int[] seats) {
        // the special case is the two available seats at both ends
        // step1: find leftMost occupied index and rightMost occupied index
        // step2: find the maxSpace between [leftMost, rightMost]
        // step3: the answer will be the max of the three: leftMost - 0, n - rightMost, maxSpace/2
        int n = seats.length;
        int left = 0;
        while (left < n && seats[left] == 0) {
            left++;
        }
        int right = n - 1;
        while (right >= 0 && seats[right] == 0) {
            right--;
        }
        
        int maxDist = Math.max(left, n - 1 - right);
        if (left == right) {
            // only one person seating in the row
            return maxDist;
        } else {
            int maxMiddle = findMaxSpaceInMiddle(seats, left, right);
            return Math.max(maxDist, (maxMiddle + 1) / 2);
        }
    }
    
    private int findMaxSpaceInMiddle(int[] seats, int start, int end) {
        if (start == end || start == end - 1) {
            return 0;
        }        
        
        int maxSpace = 0;
        int prev = start; // index of the previous 1
        for (int i=start+1; i<=end; i++) { 
            if (seats[i] != 0) {
                maxSpace = Math.max(i - prev - 1, maxSpace);
                prev = i;
            }
        }
        return maxSpace;
    }
}