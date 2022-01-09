class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // sort by from location, if they are the same, sort by to location
        // put them in a priority queue, the first comes should be added first
        // the first left passengers should be popped first
        Arrays.sort(trips, (a,b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        int taken = 0;
        
        for (int[] trip: trips) {
            int passengers = trip[0];
            int from = trip[1];
            while (!pq.isEmpty() && pq.peek()[2] <= from) {
                int[] getoff = pq.poll();
                taken -= getoff[0];
            }
            if (taken + passengers > capacity) {
                return false;
            } else {
                taken += passengers;
                pq.offer(trip);
            }
        }
        return true;
    }

    public boolean carPooling1(int[][] trips, int capacity) {
        // we can use bucket sort because 0 <= fromi < toi <= 1000
        int[] time = new int[1001];
        
        // first assume they can all fit in
        for (int[] trip: trips) {
            time[trip[1]] += trip[0];
            time[trip[2]] -= trip[0];
        }
        // then compare the array with the capacity
        int taken = 0;
        for (int passengers: time) {
            taken += passengers;
            if (taken > capacity) return false;
        }
        return true;
    }
}