class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // how to reflect the cost of building wells?
        // each node/house can have a virtual node which is the well
        // the edge weight between the well(i.e.water supply) and the house is well's cost
        // then this is a Minimum Spanning Tree problem
        // we don't need to connect all virtual nodes, but we need to connect the houses
        
        // Prim's Algorithm
        // keep a set of connected nodes and a set of unconnected nodes
        // we expand the connected nodes by selecting the cheapest edge so far
        // as long as the selected edge won't cause a cycle
        
        // for each node, we keep a list of pairs to represent its edges 
        // pair's key is the destination, value is the cost
        
        // initialize the graph
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i=0; i<n+1; i++) {
            graph.add(new ArrayList<>());
        }
        
        // process the given pipes
        for (int i = 0; i < pipes.length; i++) {
            int h1 = pipes[i][0];
            int h2 = pipes[i][1];
            int cost = pipes[i][2];
            graph.get(h1).add(new Pair<Integer, Integer>(h2, cost));
            graph.get(h2).add(new Pair<Integer, Integer>(h1, cost));
        }
        
        PriorityQueue<Pair<Integer, Integer>> edgeHeap = new PriorityQueue<>(
            (a,b) -> a.getValue() - b.getValue());
        // add the virtual edge to represent the wells
        // we must build at least one well, so we need to add them to the heap
        for (int i = 0; i < wells.length; i++) {
            // suppose all virtual nodes have a serial number of 0
            // the dest is the (i+1)th house, and the cost is wells[i]
            Pair<Integer, Integer> virtualEdge = new Pair<>(i+1, wells[i]);
            graph.get(0).add(virtualEdge);
            edgeHeap.add(virtualEdge);
        }
        
        // begin the selection process
        int totalCost = 0;
        Set<Integer> selected = new HashSet<>();
        selected.add(0); // build a well
        while (selected.size() < n+1) {
            Pair<Integer, Integer> edge = edgeHeap.poll();
            int nextHouse = edge.getKey();
            int cost = edge.getValue();
            if (selected.contains(nextHouse)) continue;
            
            selected.add(nextHouse);
            totalCost += cost;
            for (Pair<Integer, Integer> nextEdge: graph.get(nextHouse)) {
                if (!selected.contains(nextEdge.getKey())) {
                    edgeHeap.add(nextEdge);
                }
            }
        }
        return totalCost;
    }
}