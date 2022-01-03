class Solution {
    public int largestComponentSize(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int maxVal = 0;
        for (int num: nums) {
            maxVal = Math.max(num, maxVal);
        }
        DisjointSets ds = new DisjointSets(maxVal);
        // key is a number, value is its first factor
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num: nums) {
            List<Integer> primeFactors = findPrimeFactors(num);
            // map the number to its first prime factor
            Collections.sort(primeFactors);
            map.put(num, primeFactors.get(0));
            // merge the groups that contain the prime factors
            for (int i=0; i<primeFactors.size()-1; i++) {
                ds.union(primeFactors.get(i), primeFactors.get(i+1));
            }
        }
        
        // find the max set's size
        int maxCount = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num: nums) {
            Integer parent = ds.find(map.get(num));
            countMap.put(parent, countMap.getOrDefault(parent, 0) + 1);
            maxCount = Math.max(maxCount, countMap.get(parent));
        }
        return maxCount;
    }
    
    private List<Integer> findPrimeFactors(int num) {
        // return a list of prime factors
        Set<Integer> set = new HashSet<>();
        int factor = 2;

        while (factor * factor <= num) {
            if (num % factor == 0) {
                set.add(factor);
                num = num / factor;
            } else {
                factor++;
            }
        }
        set.add(num); // don't forget to add num
        return new ArrayList<>(set);
    }
    
    class DisjointSets {
        private int[] parent;
        private int[] size;
        
        public DisjointSets(int n) {
            parent = new int[n+1];
            size = new int[n+1];
            for (int i=0; i<n+1; i++) {
                parent[i] = i; // let each node point to itself
                size[i] = 1; // initially, node i is in a set of size = 1
            }
        }
        
        public int find(int x) {
            // return the parent value of this number x
            if (parent[x] != x) {
                //  parent[x] == x, i.e. points to itself
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public int union(int x, int y) {
            // merge the two sets that x and y belongs to
            // return the parent value of the merged set
            int parentX = find(x);
            int parentY = find(y);
            
            if (parentX == parentY) {
                // they are in the same set, no need to merge
                return parentX;
            }
            
            // let the set which uses parentX as the root points to parentY
            parent[parentX] = parentY;
            size[parentY] += size[parentX];
            return parentY;
        }
    }
}