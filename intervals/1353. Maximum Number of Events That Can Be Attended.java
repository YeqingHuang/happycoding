class Solution {
    public int maxEvents(int[][] events) {
        // note: you don't need to attend an event from beginning to end
        // thoughts:
        // we should attend the events that end earlier, otherwise we don't have the chance
        // but we also need to sort by begin time in order to loop the days
        // consider [1,5],[1,5],[1,2],[3,3],[1,2]
        // correct sequence is [1,2],[1,2],[3,3],[1,5],[1,5]
        // therefore we sort by begining, on day1, we add 4 meetings
        // when a new day comes, we add events that begin on that day
        // as long as the pq is not empty, we have events to consume
        // when we consume, pick the ones that end earlier, therefore, this is a minHeap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(events, (a,b) -> a[0] - b[0]);

        int endDay = 0;
        for (int[] event: events) {
            endDay = Math.max(endDay, event[1]);
        }
        
        int attended = 0;
        int i = 0;
        for (int day = events[0][0]; day <= endDay; day++) {
            // remove those events that have ended
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }
            // add events that begin on this day
            while (i < events.length && events[i][0] == day) {
                pq.offer(events[i][1]);
                i++;
            }
            // choose to attend a meeting
            if (!pq.isEmpty()) {
                pq.poll();
                attended++;
            }
        }
        return attended;
    }
}