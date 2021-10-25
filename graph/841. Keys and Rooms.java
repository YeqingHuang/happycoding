class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // bfs, whatever keys we collected
        // we just put them into a queue(next available room)
        // when there is no more keys, i.e. queue is empty
        // check the size of the visited set
        List<Integer> startKeys = rooms.get(0);
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(startKeys);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            visited.add(curr);
            for (int key: rooms.get(curr)) {
                if (!visited.contains(key)) {
                    queue.add(key);
                }
            } 
        }
        return visited.size() == rooms.size();
    }
}