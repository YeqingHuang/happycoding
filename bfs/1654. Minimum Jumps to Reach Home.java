class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        // Bezout's Identity : x*a+y*b = z*gcd(a,b)
        // all reachable positions must be a factor of gcd(a,b)
        // although not all z*gcd(a,b) can be reachable because it cannot jump backward twice in a row
        // even if we don't know this theory, we can use bfs
        int step = 0;
        int furthest = x + a + b;
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.offer(new Pair(0, 0)); // direction & position;
        
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.add(new Pair(0, 0));
        for (int pos : forbidden) {
            visited.add(new Pair(0, pos));
            visited.add(new Pair(1, pos));
            // why upper bound is a+b+max(x, max(forbidden))?
            furthest = Math.max(furthest, pos + a + b);
        }
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                Pair<Integer, Integer> curr = q.poll();
                int direction = curr.getKey();
                int pos = curr.getValue();
                if (pos == x) return step;

                Pair<Integer, Integer> forward = new Pair<>(0, pos + a);
                Pair<Integer, Integer> backward = new Pair<>(1, pos - b);
                if (pos + a <= furthest && !visited.contains(forward)) {
                    q.offer(forward);
                    visited.add(forward);
                }
                if (direction == 0 && pos-b >= 0 && !visited.contains(backward)) {
                    // if dir == 1, we cannot move backward
                    q.offer(backward);
                    visited.add(backward);
                }
            }
            step++;
        }
        return -1;
    }
}