class Solution {
    public int minJumps(int[] arr) {
        // we can preprocess the array, so that
        // for each cell, we know what are the options for the next step
        // then it's like jumping in a graph -> bfs
        if (arr.length == 1) return 0;
        
        Map<Integer, List<Integer>> map = buildGraph(arr);
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(0);
        visited.add(0);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int currIndex = queue.poll();
                List<Integer> neighbours = map.get(arr[currIndex]);
                for (int neigh: neighbours) {
                    if (neigh == arr.length - 1) {
                        return step + 1;
                    }
                    if (!visited.contains(neigh)) {
                        queue.add(neigh);
                        visited.add(neigh);
                    }
                }

                // this is the most important line
                map.get(arr[currIndex]).clear();
                
                int[] delta = {-1, 1};
                for (int d: delta) {
                    int nextIndex = currIndex + d;
                    if (nextIndex >= 0 && nextIndex < arr.length) {
                        if (nextIndex == arr.length - 1) {
                            return step + 1;
                        }
                        if (!visited.contains(nextIndex)) {
                            queue.add(nextIndex);
                            visited.add(nextIndex);
                        }
                    }
                }
            }
            step++;
        }
        return step;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0; i<arr.length; i++) {
            map.putIfAbsent(arr[i], new LinkedList<>());
            map.get(arr[i]).add(i);
        }
        return map;
    }
}