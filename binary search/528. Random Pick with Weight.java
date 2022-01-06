class Solution {
    // naive solution, TLE
    List<Integer> pool;
    
    public Solution(int[] w) {
        pool = new ArrayList<>();
        for (int i=0; i<w.length; i++) {
            for (int j=1; j<=w[i]; j++) {
                pool.add(i);
            }
        }
    }
    
    public int pickIndex() {
        // int randomNumber = random.nextInt(max + 1 - min) + min;
        Random r = new Random();
        int index = r.nextInt(pool.size());
        return pool.get(index);
    }
}

class Solution1 {
    // w[] = {2,5,3,4}, prefix[] = {2,7,10,14}
    // then get random val random.nextInt(14)+1, idx is in range [1,14]
    // idx in [1,2] return 0
    // idx in [3,7] return 1
    // idx in [8,10] return 2
    // idx in [11,14] return 3

    int[] prefix;
    public Solution(int[] w) {
        prefix = new int[w.length];
        prefix[0] = w[0];
        for (int i=1; i<w.length; i++) {
            prefix[i] = prefix[i-1] + w[i];
        }
    }
    
    public int pickIndex() {
        int total = prefix[prefix.length-1];
        // random.nextInt(max + 1 - min) + min;
        Random random = new Random();
        int num = random.nextInt(total + 1 - 1) + 1;
        return binarySearch(num);
    }
    
    private int binarySearch(int target) {
        // return the leftmost i where prefix[i] is equal to or bigger than target
        int low = 0;
        int high = prefix.length - 1;
        while (low < high -1) {
            int mid = (low + high) / 2;
            if (prefix[mid] == target) {
                return mid;
            } else if (prefix[mid] < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return prefix[low] >= target ? low : high;
    }
}