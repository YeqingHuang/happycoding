class Solution {
    Map<Integer, Integer> map;
    public int getKth(int lo, int hi, int k) {
        map = new HashMap<>();
        
        // we want the kth smallest, use a maxHeap
        // if we want to find the kth largest, use a minHeap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->
            a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        
        for (int num=lo; num <=hi; num++) {
            int ops = findPowerValue(num);
            pq.add(new int[]{num, ops});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek()[0];
    }
    
    private int findPowerValue(int num) {
        if (num == 1) return 0;
        
        if (map.containsKey(num)) return map.get(num);
        
        int ans = 0;
        if (num % 2 == 0) {
            ans = 1 + findPowerValue(num / 2);
        } else {
            ans = 1 + findPowerValue(3 * num + 1);
        }
        map.put(num, ans);
        return ans;
    }
}