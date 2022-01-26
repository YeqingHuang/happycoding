class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // edge case
        if (source == destination) return true;
        
        // we can use bfs
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge: edges) {
            int a = edge[0], b = edge[1];
            map.putIfAbsent(a, new ArrayList<>());
            map.get(a).add(b);
            map.putIfAbsent(b, new ArrayList<>());
            map.get(b).add(a);
        }
        
        if (!map.containsKey(source) || !map.containsKey(destination)) {
            return false;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (map.containsKey(curr)) {
                List<Integer> neighbours = map.get(curr);
                for (int neigh: neighbours) {
                    if (neigh == destination) return true;
                    if (!visited.contains(neigh)) {
                        queue.add(neigh);
                        visited.add(neigh);
                    }
                }
            }
        }
        return false;
    }
}