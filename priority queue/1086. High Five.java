class Solution {
    Map<Integer, PriorityQueue<Integer>> map;
    
    public int[][] highFive(int[][] items) {
        // For each IDi, there will be at least five scores
        map = new HashMap<>();
        
        for (int[] item: items) {
            int id = item[0];
            int score = item[1];
            map.putIfAbsent(id, new PriorityQueue<Integer>());
            map.get(id).offer(score);
            if (map.get(id).size() > 5) {
                map.get(id).poll();
            }
        }
        
        int[][] result = new int[map.size()][2];
        int i = 0;
        for (int key: map.keySet()) {
            int total = 0;
            PriorityQueue<Integer> pq = map.get(key);
            while (!pq.isEmpty()) {
                total += pq.poll();
            }
            result[i++] = new int[]{key, total / 5};
        }
        return result;
    }
}