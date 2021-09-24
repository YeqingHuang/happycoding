class Solution {
    
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // we don't want the min number of stops
        // we want the min number of routes!
        if (source == target) return 0;
        
        // the option comes when two routes intersects
        // when there's no option to switch, just explore along routes[i]
        // use a map, where key is stop, value is a list of routes' index that arrive at this stop
        // we don't want to take a bus twice, use a boolean array to record it
        int n = routes.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            // bus number is i
            for (int stop: routes[i]) {
                map.putIfAbsent(stop, new HashSet<>());
                map.get(stop).add(i);
            }
        }
        
        boolean[] taken = new boolean[n];
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{source, 0}); // 0 is the number of buses taken so far
        visited.add(source);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currStop = curr[0];
            int count = curr[1];
            if (currStop == target) {
                return count;
            }
            for (int bus: map.get(currStop)) {
                if (!taken[bus]) {
                    // we will try to take this bus and check all its stop
                    taken[bus] = true;
                    for (int possibleStop: routes[bus]) {
                        if (!visited.contains(possibleStop)) {
                            visited.add(possibleStop);
                            queue.offer(new int[]{possibleStop, count + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}