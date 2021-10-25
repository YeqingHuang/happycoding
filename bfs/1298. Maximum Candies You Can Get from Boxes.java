class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        // 1. for those found boxes that cannot be opened yet
        // we should also store them in case we can open them later(get the keys)
        // 2. modify the status array when we get keys
        Set<Integer> opened = new HashSet<>();
        Set<Integer> collectedNotOpened = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int box: initialBoxes) {
            queue.add(box);
            collectedNotOpened.add(box);
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (status[curr] == 1 && !opened.contains(curr)) {
                // we can open it!
                opened.add(curr);
                collectedNotOpened.remove(curr);
                // collect the keys
                for (int num: keys[curr]) {
                    status[num] = 1;
                    if (collectedNotOpened.contains(num)) {
                        queue.add(num);
                    }
                }
                // collect the boxes
                for (int num: containedBoxes[curr]) {
                    collectedNotOpened.add(num);
                    queue.add(num);
                }
            }
        }
        
        int ans = 0;
        for (int box: opened) {
            ans += candies[box];
        }
        return ans;
    }
}

class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        // we don't need to maintain opened set, since the constraint mentions that
        // all values in keys[i] are unique
        // all values in containedBoxes[i] are unique
        // each box is contained in one box at most
        Set<Integer> collectedNotOpened = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int box: initialBoxes) {
            queue.add(box);
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // either we open it, or we put it in collectedNotOpened
            if (status[curr] == 1) {
                // we can open it
                ans += candies[curr];
                // collect the keys
                for (int num: keys[curr]) {
                    status[num] = 1;
                    if (collectedNotOpened.contains(num)) {
                        queue.add(num);
                        collectedNotOpened.remove(num);
                    }
                }
                // collect the boxes
                for (int num: containedBoxes[curr]) {
                    queue.add(num);
                }
            } else {
                collectedNotOpened.add(curr);
            }
        }
        return ans;
    }
}