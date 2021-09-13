class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w: words) {
            map.put(w, map.getOrDefault(w,0)+1);
        }
        
        // minHeap: first sort by value
        // if value is the same, then sort by key
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a,b) ->
             a.getValue().equals(b.getValue()) ? 
             b.getKey().compareTo(a.getKey()) : 
             Integer.compare(a.getValue(), b.getValue()));
        
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<String> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }
}