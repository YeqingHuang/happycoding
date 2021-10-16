class SummaryRanges {
    Map<Integer, Integer> startMap; // key value is start, end
    Map<Integer, Integer> endMap; // key value is end, start
    Set<Integer> seen;
    
    public SummaryRanges() {
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        seen = new HashSet<>();
    }
    
    public void addNum(int val) {
        if (seen.contains(val)) return;
        
        if (seen.contains(val - 1) && seen.contains(val + 1)){
            int newStart = endMap.get(val - 1);
            int newEnd = startMap.get(val + 1);
            startMap.put(newStart, newEnd);
            endMap.put(newEnd, newStart);
            startMap.remove(val+1);
            endMap.remove(val-1);
        } else if (seen.contains(val - 1)) {
            int start = endMap.get(val - 1);
            startMap.put(start, val);
            endMap.put(val, start);
            endMap.remove(val - 1);
        } else if (seen.contains(val + 1)) {
            int end = startMap.get(val + 1);
            startMap.put(val, end);
            startMap.remove(val + 1);
            endMap.put(end, val);
        } else {
             // no interval is merged
            startMap.put(val, val);
            endMap.put(val, val);
        }
        seen.add(val);
    }
    
    public int[][] getIntervals() {
        int[][] ans = new int[startMap.size()][2];
        int i = 0;
        
        for (int key: startMap.keySet()) {
            ans[i] = new int[]{key, startMap.get(key)};
            i++;
        }
        // don't forget to sort by starting point
        Arrays.sort(ans, (a,b) -> a[0] - b[0]);
        return ans;
    }
}