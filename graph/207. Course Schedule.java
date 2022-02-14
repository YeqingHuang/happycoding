class Solution {
    // number of courses is |V|, number of dependencies(edges) |E|
    // then time complexity of topological sort is O(|E| + |V|)
    // buidling step takes O(|E|), queue stage takes  O(|E| + |V|)
    
    // space complexity is O(|E| + |V|)
    // the two maps take O(|E| + |V|), the queue takes O(|V|)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // when do we know it's impossible?
        // there's a cycle, i.e. ans.size() < numsCourses
        
        Map<Integer, List<Integer>> outMap = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int[] p: prerequisites) {
            int from = p[1], to = p[0];
            outMap.putIfAbsent(from, new ArrayList<>());
            outMap.get(from).add(to);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
        }
        
        List<Integer> ans = new ArrayList<>(); // a valid way to take the courses
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (!indegree.containsKey(i)) {
                queue.offer(i); // this course has no dependency
            }
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            ans.add(curr); // we will take this course
            if (outMap.containsKey(curr)) {
                List<Integer> neighbours = outMap.get(curr);
                for (int course: neighbours) {
                    indegree.put(course, indegree.get(course) - 1);
                    if (indegree.get(course) == 0) {
                        queue.offer(course);
                    }
                }
            }
        }
        
        return ans.size() == numCourses;
    }
}


class Solution {
    // DFS & backtracking
    // time complexity of topological sort is O(|E| + |V|^2)
    // space complexity O(|E| + |V|)
    // outmap takes O(|E| + |V|), two arrays takes O(2|V|), recursion may take up O(|V|)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // how to detect cycles?
        // step1: use a hashmap to store the adjacent list
        // step2: several status of each node
        //       1) unvisited 2) being processed 3) processed
        //       2) is the nodes that we pass by on the path but have not marked as processed yet
        // if at some point, we meet a node that's being processed, there's a cycle
        
        Map<Integer, List<Integer>> outMap = new HashMap<>();
        for (int[] p: prerequisites) {
            int from = p[1], to = p[0];
            outMap.putIfAbsent(from, new ArrayList<>());
            outMap.get(from).add(to);
        }
        
        boolean[] processing = new boolean[numCourses];
        boolean[] processed = new boolean[numCourses];
        for (int i=0; i<numCourses; i++) {
            if (!validGraph(i, outMap, processing, processed)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean validGraph(int course, Map<Integer, List<Integer>> map, boolean[] processing, boolean[] processed) {
        if (processed[course]) return true; // already pass the examination
        if (processing[course]) return false; // we meet a node which is being processed
        if (!map.containsKey(course)) return true; // cannot go anywhere, ok
        
        // general case
        processing[course] = true;
        // check each of the next neighbour
        if (map.containsKey(course)) {
            for (int neighbour: map.get(course)) {
                boolean result = validGraph(neighbour, map, processing, processed);
                if (!result) return false;
            }
        }
        
        // the transition state is over
        processing[course] = false;
        processed[course] = true;
        return true;
    }
}