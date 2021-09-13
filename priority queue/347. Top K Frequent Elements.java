class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // top k, use minHeap
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        for (int key: map.keySet()) {
            pq.offer(new int[]{key, map.get(key)});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        int[] ans = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            ans[i++] = pq.poll()[0];
        }
        return ans;
    }
}