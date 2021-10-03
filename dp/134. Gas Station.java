class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // naive method: try every station O(n^2)
        int n = gas.length;
        for (int start=0; start<n; start++) {
            if (gas[start] < cost[start]) {
                continue;
            }
            int tank = 0;
            for (int i=start; i<start+n; i++) {
                int index = i % n;
                tank = tank + gas[index] - cost[index];
                if (tank < 0) break;
                if (i == start + n - 1) return start;
            }
        }
        return -1;
    }
}

class Solution {
    // time complexity O(n)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // suppose we explore a route and tank falls below 0 from i to i+1
        // now we should use i+1 as the new start, not start+1
        // reason: we know that we cannot reach i+1 from 0, i.e. sum of delta < 0
        // suppose there is some station j inbetween that satisfy sum of delta > 0
        // then sum of delta [0,j] must be negative
        // this is impossible because if delta[0,j] < 0, we cannot make it to j
        
        // suppose we find a station k and make it to n-1
        // we still need to check if the first part [0, k] works
        // if it won't work, return -1
        // no need to check those candidates between [k+1, n-1]
        // this is for the same reason, a later station can never be better
        int tank = 0;
        int start = 0;
        for (int i=0; i<gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                // try i+1 as the new start
                tank = 0;
                start = i+1;
            }
        }
        // now [start, n-1] is ok, check [0, start]
        for (int i=0; i<=start; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                return -1;
            }
        }
        return start;
    }
}