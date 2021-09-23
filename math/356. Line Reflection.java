class Solution {
    // draw a vertical line x = k, such that
    // half of the points are on the left side of this line
    // the other half of points are "mirroring" them on the right side of the line
    public boolean isReflected(int[][] points) {
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point: points) {
            int x = point[0];
            int y = point[1];
            map.putIfAbsent(y, new HashSet<>());
            map.get(y).add(x);
        }
        
        // use the first entry to find the baseline
        Map.Entry<Integer, Set<Integer>> entry = map.entrySet().iterator().next();
        double baseX = findVerticalLine(entry.getValue());
        if (Double.compare(baseX, 1e8+1) == 0) {
            return false;
        }
        for (int key: map.keySet()) {
            double currX = findVerticalLine(map.get(key));
            if (Double.compare(currX, baseX) != 0) {
                return false;
            }
        }
        return true;
    }
    
    private double findVerticalLine(Set<Integer> set) {
        List<Integer> points = new ArrayList<>(set);
        Collections.sort(points);
        
        double median;
        int n = points.size();
        if (n % 2 == 1) {
            median = (double) points.get(n/2); 
        } else {
            // a virtual median
            median = (points.get(n/2-1) + points.get(n/2)) / 2.0;
        }
        
        for (int i=0; i<n/2; i++) {
            int j = n - i - 1; // symmetric index
            if (Double.compare(median - points.get(i), points.get(j) - median) != 0) {
                return 1e8+1;
            }
        }
        return median;
    }
}


class Solution1 {
    // a much cleaner version using twoSum
    // i.e all points should be paired such that p1.x + p2.x = constant value
    // for those points that falls on the vertical line, p3.x + p3.x = constant value
    // this constant value is derived from minX + maxX
    public boolean isReflected(int[][] points) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        HashSet<String> set = new HashSet<>();
        for (int[] point: points) {
            int x = point[0];
            int y = point[1];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            String str = x + "_" + y;
            set.add(str);
        }
        
        int twoSum = minX + maxX;
        for (int[] point: points) {
            // find the symmetric point for each point
            String paired = (twoSum - point[0]) + "_" + point[1];
            if (!set.contains(paired)) {
                return false;
            }
        }
        return true;
    }
}

class Solution2 {
    // a combination of solution1 and solution2
    // faster than 91.96% of Java online submissions 
    public boolean isReflected(int[][] points) {
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        
        for (int[] point: points) {
            int x = point[0];
            int y = point[1];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            map.putIfAbsent(y, new HashSet<>());
            map.get(y).add(x);
        }
        
        int twoSum = minX + maxX;
        for (int[] point: points) {
            int desiredX = twoSum - point[0];
            if (!map.get(point[1]).contains(desiredX)) {
                return false;
            }
        }
        return true;
    }
}