class TweetCounts {
    // there maybe more than one recordTweet at the same time
    // we cannot just use a sortedSet, use a sortedMap instead
    private Map<String, TreeMap<Integer, Integer>> records;
    
    public TweetCounts() {
        records = new HashMap<>();
    }
    
    public void recordTweet(String tweetName, int time) {
        records.putIfAbsent(tweetName, new TreeMap<>());
        TreeMap<Integer, Integer> tweetMap = records.get(tweetName);
        tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);
    }
    
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        if (!records.containsKey(tweetName)) return null;
        
        int interval = 60;
        if (freq.equals("hour")) {
            interval = 3600;
        } else if (freq.equals("day")) {
            interval = 24 * 3600;
        }
        
        List<Integer> ans = new ArrayList<>();
        int size = (endTime - startTime) / interval + 1;
        int[] buckets = new int[size];
        TreeMap<Integer, Integer> timestamps = records.get(tweetName);
        // our target is a subMap, i.e. intersection
        for (int time: timestamps.subMap(startTime, endTime + 1).keySet()) {
            int index = (time - startTime) / interval;
            buckets[index] += timestamps.get(time);
        }
        
        for (int count: buckets) {
            ans.add(count);
        }
        return ans;
    }
}