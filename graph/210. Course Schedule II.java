class Solution {
    // dfs
    static int unvisited = 0;
    static int beingProcessed = 1;
    static int processed = 2;
    boolean hasCycle;
    Map<Integer, Integer> status; //(node, enum status)
    Map<Integer, List<Integer>> outMap; // adjacent list
    List<Integer> ans;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        hasCycle = false;
        status = new HashMap<>();
        outMap = new HashMap<>();
        ans = new ArrayList<>();
        // initialize status
        for (int i=0; i<numCourses; i++) {
            status.put(i, unvisited);
        }
        // get the edges
        for (int[] p: prerequisites) {
            int to = p[0];
            int from = p[1];
            outMap.putIfAbsent(from, new ArrayList<>());
            outMap.get(from).add(to);
        }
        
        // if a node has not been visited, call dfs helper
        for (int i=0; i<numCourses; i++) {
            if (status.get(i) == unvisited) {
                dfs(i);
            }
        }
        
        if (hasCycle) return new int[0];
        // get the final result in reverse order
        // first added should be visited last
        int[] result = new int[numCourses];
        for (int i=0; i<numCourses; i++) {
            result[i] = ans.get(numCourses - i - 1);
        }
        return result;
    }
    
    private void dfs(int course) {
        if (hasCycle) return;
        
        status.put(course, beingProcessed);
        
        if (outMap.containsKey(course)) {
            List<Integer> neighbours = outMap.get(course);
            for (int nei: neighbours) {
                if (status.get(nei) == unvisited) {
                    dfs(nei);
                } else if (status.get(nei) == beingProcessed) {
                    hasCycle = true;
                    return;
                } // else already processed, ok
            }
        }
        
        status.put(course, processed);
        ans.add(course);
    }
}

class Solution1 {
    // bfs
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> inMap = new HashMap<>(); // course number, its dependency count
        Map<Integer, List<Integer>> outMap = new HashMap<>(); // course number, a list of next course that can be taken
        for (int[] p: prerequisites) {
            int second = p[0];
            int first = p[1];
            inMap.put(second, inMap.getOrDefault(second, 0) + 1);
            outMap.putIfAbsent(first, new ArrayList<>());
            outMap.get(first).add(second);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (!inMap.containsKey(i)) {
                // this course has no dependency
                queue.offer(i);
            }
        }
        
        int i = 0;
        int[] ans = new int[numCourses];
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            ans[i++] = curr;
            if (outMap.containsKey(curr)) {
                // if curr course is an isolated one(no in, no out), then it does not exist in outMap
                for (int nextCourse: outMap.get(curr)) {
                    inMap.put(nextCourse, inMap.get(nextCourse)-1);
                    if (inMap.get(nextCourse) == 0) {
                        queue.add(nextCourse);
                    }
                }
            }
        }
        
        if (i != numCourses) return new int[0];
        return ans;
    }
}