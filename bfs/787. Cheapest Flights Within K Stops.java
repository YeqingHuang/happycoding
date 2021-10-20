class Solution {
    private int[][] matrix;
    private Map<Pair<Integer, Integer>, Integer> costMap;
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. When to stop? 
        // either we running out of flights or running out of steps
        // 2. bfs: how to keep the cost on different routes?
        // something to consider: if A -> B has a cost of 100 with 1 flight
        // while we explore, A -> B has a cost of 400 with 2 flights
        // there is no point to continue on the this path
        // only when A -> B has a cost of less than 100 worth adding it to the queue
        this.matrix = new int[n][n];
        this.costMap = new HashMap<>();
            
        for (int[] flight: flights) {
            // matrix[i][j] == 0 means no flight from i to j
            matrix[flight[0]][flight[1]] = flight[2];
        }
        // (src,0) means get to src with 0 stops, value is the cost
        costMap.put(new Pair<Integer, Integer>(src, 0), 0);  
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        int step = 0;
        int minCost = Integer.MAX_VALUE;
        while (!queue.isEmpty() && step < k+1) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int curr = queue.poll();
                for (int next=0; next<n; next++) {
                    if (next != curr && matrix[curr][next] > 0) {
                        int toCurr = costMap.getOrDefault(new Pair<Integer, Integer>(curr, step), Integer.MAX_VALUE);
                        int toNext = costMap.getOrDefault(new Pair<Integer, Integer>(next, step + 1), Integer.MAX_VALUE);
                        int price = matrix[curr][next];
                        if (toCurr + price < toNext) {
                            // this is worthy 
                            costMap.put(new Pair<Integer, Integer>(next, step + 1), toCurr + price);
                            queue.add(next);
                            // check if this is an answer
                            if (next == dst) {
                                minCost = Math.min(minCost, toCurr + price);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }
}


class Solution {
    // dfs, also needs memoization
    private int[][] matrix;
    private Map<Pair<Integer, Integer>, Long> costMap;
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // we can also solve using DFS + pruning
        this.matrix = new int[n][n];
        this.costMap = new HashMap<>();
            
        for (int[] flight: flights) {
            matrix[flight[0]][flight[1]] = flight[2];
        }
        
        long ans = findMinCost(src, dst, k);
        return ans < Integer.MAX_VALUE ? (int) ans : -1;
    }
    
    private long findMinCost(int curr, int dest, int stops) {
        // dest found! cost of this part is 0
        if (curr == dest) return 0;
        // cannot continue exploring
        if (stops < 0) return Integer.MAX_VALUE;
        // check if we've already calculated
        Pair<Integer, Integer> status = new Pair<Integer, Integer>(curr, stops);
        if (costMap.containsKey(status)) return costMap.get(status);
        
        // general case
        long ans = Integer.MAX_VALUE;
        for (int next=0; next<matrix[0].length; next++) {
            int currCost = matrix[curr][next];
            if (currCost > 0) {
                // there's a flight from curr to next
                ans = Math.min(ans, findMinCost(next, dest, stops-1) + currCost);
            }
        }
        costMap.put(status, ans);
        return ans;
    }
}