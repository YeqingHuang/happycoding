class Solution {
    
    class Dest{
        String destination;
        double value;
        
        public Dest(String destination, double value) {
            this.destination = destination;
            this.value = value;
        }
    }
    
    Map<String, List<Dest>> map;
        
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // graph problem
        // if from or to does not exist, return -1
        // if from == to, return 1
        // general case: check if we can find a path from a to b
        map = new HashMap<>(); // adjacent list
        for (int i = 0; i<equations.size(); i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            double value = values[i];
            map.putIfAbsent(from, new ArrayList<>());
            map.putIfAbsent(to, new ArrayList<>());
            map.get(from).add(new Dest(to, value));
            map.get(to).add(new Dest(from, 1/value));
        }
        
        double[] ans = new double[queries.size()];
        int k = 0;
        for (List<String> query: queries) {
            ans[k++] = findPath(query.get(0), query.get(1));
        }
        return ans;
    }
    
    private double findPath(String from, String to) {
        if (!map.containsKey(from) || !map.containsKey(to)) {
            return -1.0;
        }
        if (from.equals(to)) return 1.0;
        
        Set<String> visited = new HashSet<>();
        Queue<Dest> queue = new LinkedList<>();
        visited.add(from);
        queue.add(new Dest(from, 1.0));
        while (!queue.isEmpty()) {
            Dest curr = queue.poll();
            String currStr = curr.destination;
            double currVal = curr.value;
            if (currStr.equals(to)) {
                return currVal;
            }
            for (Dest next: map.get(currStr)) {
                if (!visited.contains(next.destination)) {
                    queue.add(new Dest(next.destination, currVal * next.value));
                    visited.add(next.destination);
                }
            }
        }
        return -1.0;
    }
}