class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // count the number of nodes with indegree = 0
        // as long as a node's indegree > 0, it is reachable from some other node
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i=0; i<n; i++) {
            inMap.put(i, 0); // node, indegree
        }
        for (List<Integer> edge: edges) {
            int dest = edge.get(1);
            inMap.put(dest, inMap.get(dest) + 1);
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (inMap.get(i) == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    // a much faster solution
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> ans = new ArrayList<>();
        boolean[] destinations = new boolean[n];
        for (List<Integer> edge: edges) {
            destinations[edge.get(1)] = true;
        }
        for (int i=0; i<n; i++) {
            if (!destinations[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}