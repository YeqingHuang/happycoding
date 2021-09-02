class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // process by starting time
        // use some data structure to keep the ongoing meetings
        // when we add a new meeting, first check if there are any finished meetings
        // we want to quickly get the earliest ending meeting, use a minHeap for this
        // p.s once a meeting is added to the minHeap, its starting time no longer matters
        // so we only store ending time
        if (intervals.length <= 1) return intervals.length;
        
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int peakCount = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] interval: intervals) {
            int start = interval[0];
            int ending = interval[1];
            while (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }
            pq.offer(ending);
            peakCount = Math.max(peakCount, pq.size());
        }
        return peakCount;
    }

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