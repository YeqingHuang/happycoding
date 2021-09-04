class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        // this problem is twoSum
        int[] remainders = new int[60];
        for (int song: time) {
            remainders[song % 60]++;
        }
        
        int count = 0;
        if (remainders[0] >= 2) {
            count += remainders[0] * (remainders[0]-1) / 2;
        }
        for (int i=1; i<30; i++) {
            count += remainders[i] * remainders[60-i];
        }
        if (remainders[30] >= 2) {
            count += remainders[30] * (remainders[30]-1) / 2;
        }
        return count;
    }

    // a cleaner version to avoid calculating 3+57 and 57+3 twice
     public int numPairsDivisibleBy60(int[] time) {
        int remainders[] = new int[60];
        int count = 0;
        for (int t: time) {
            if (t % 60 == 0) { // check if a%60==0 && b%60==0
                count += remainders[0];
            } else { // check if a%60 + b%60==60
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++;
        }
        return count;
    }
}