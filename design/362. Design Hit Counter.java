class HitCounter {
    Map<Integer, Integer> map;
    
    /** Initialize your data structure here. */
    public HitCounter() {
        map = new HashMap<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        map.put(timestamp, map.getOrDefault(timestamp, 0)+1);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int count = 0;
        int beginTime = Math.max(0, timestamp - 300);
        for (int i=beginTime+1; i<=timestamp; i++) {
            if (map.containsKey(i)) {
                count += map.get(i);
            }
        }
        return count;
    }
}

class HitCounter {
    // use a queue, only maintain records that happen in the last 300 minutes
    Queue<Integer> queue;
    
    /** Initialize your data structure here. */
    public HitCounter() {
        queue = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }
        queue.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }
        return queue.size();
    }
}