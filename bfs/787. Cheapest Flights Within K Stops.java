class Solution {
    // below method will TLE
    class Destination {
        int dest;
        int price;
        
        public Destination(int dest, int price) {
            this.dest = dest;
            this.price = price;
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // k+1 = step, once step is out of bound, we return -1
        // bfs: how to keep the cost on different route?
        Map<Integer, List<Destination>> map = new HashMap<>();
        for (int[] flight: flights) {
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(new Destination(flight[1], flight[2]));
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, 0}); // 0 is accumulated cost
        Set<String> visited = new HashSet<>();
        visited.add(serialize(src, 0));
        int step = 0;
        int minCost = Integer.MAX_VALUE;
        while (!queue.isEmpty() && step < k+1) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int[] curr = queue.poll();
                if (!map.containsKey(curr[0])) {
                    continue; // no outbound flights
                }
                for (Destination nextStop: map.get(curr[0])) {
                    int newCost = curr[1] + nextStop.price;
                    if (nextStop.dest == dst) {
                        minCost = Math.min(minCost, newCost);
                    }
                    String newStatus = serialize(nextStop.dest, newCost);
                    if (!visited.contains(newStatus)) {
                        queue.add(new int[]{nextStop.dest, newCost});
                        visited.add(newStatus);
                    }
                }
            }
            step++;
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }
    
    private String serialize(int stop, int cost) {
        StringBuilder sb = new StringBuilder();
        sb.append(stop).append(",").append(cost);
        return sb.toString();
    }
}