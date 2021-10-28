class Solution {
    // k intervals, the priority queue would has a size of k in the worst case
    // space O(k), time O(klogk)
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length <= 1) return intervals.length;
        
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        // use a queue to store the ending time
        // when we process a new meeting, first check if there are any meetings that have ended
        // if yes, pop them out
        // then add this meeting and capture current size
        int peakSize = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] interval: intervals) {
            int start = interval[0];
            int end = interval[1];
            while (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }
            pq.offer(end);
            peakSize = Math.max(peakSize, pq.size());
        }
        return peakSize;
    }

    // sorting takes O(2klog2k)
    public int minMeetingRooms1(int[][] intervals) {
        // mix and sort all points together
        // when it's a starting point, + 1; when it's an ending point, -1
        // for [1,8],[8,9] we must -1 and +1, otherwise we will have a wrong answer
        if (intervals.length <= 1) return intervals.length;
        
        int[][] points = new int[intervals.length*2][2];
        int i = 0;
        for (int[] interval: intervals) {
            points[i] = new int[]{interval[0], 1};
            points[i+1] = new int[]{interval[1], -1};
            i += 2;
        }
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        int count = 0;
        int peakCount = 0;
        for (int[] point: points) {
            count += point[1];
            peakCount = Math.max(peakCount, count);
        }
        return peakCount;
    }
}