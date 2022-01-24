class Solution {
    public int minAreaRect(int[][] points) {
        // suppose we use a hashMap to store the vertical line
        // in example1, a key is x=1, the value is a list of sorted points y=1, y=3
        // another key is x=3, the value is a list of sorted points y=1, y=3
        // for key x=2 , it cannot form a vertical line because there's only one point
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point: points) {
            map.putIfAbsent(point[0], new HashSet<>());
            map.get(point[0]).add(point[1]);
        }
        
        List<Integer> availableX = new ArrayList<>();
        for (int key: map.keySet()) {
            if (map.get(key).size() >= 2) {
                availableX.add(key);
            }
        }
        
        int minArea = Integer.MAX_VALUE;
        for (int i=0; i<availableX.size() - 1; i++) {
            for (int j=i+1; j<availableX.size(); j++) {
                int deltaX = Math.abs(availableX.get(i) - availableX.get(j));
                int minDeltaY = findMinDeltaY(map.get(availableX.get(i)), map.get(availableX.get(j)));
                
                if (minDeltaY != Integer.MAX_VALUE) {
                    minArea = Math.min(minArea, deltaX * minDeltaY);
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
    
    private int findMinDeltaY(Set<Integer> set1, Set<Integer> set2) {
        // step1: find the intersection of two sets
        // there should be at least two numbers to proceed to the next step
        List<Integer> intersection = new ArrayList<>();
        for (int num: set1) {
            if (set2.contains(num)) {
                intersection.add(num);
            }
        }
        Collections.sort(intersection);
        
        // step2: among these y points, find the pair that has the min distance
        int minDist = Integer.MAX_VALUE;
        for (int i=0; i<intersection.size()-1; i++) {
            minDist = Math.min(minDist, intersection.get(i+1) - intersection.get(i));
        }
        return minDist;
    }
}