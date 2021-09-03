class SparseVector {
    Map<Integer, Integer> map;
    
    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int product = 0;
        Map<Integer, Integer> smallerMap, biggerMap;
        if (vec.map.size() < map.size()) {
            smallerMap = vec.map;
            biggerMap = map;
        } else {
            smallerMap = map;
            biggerMap = vec.map;
        }
        
        for (int index: smallerMap.keySet()) {
            if (biggerMap.containsKey(index)) {
                product += smallerMap.get(index) * biggerMap.get(index);
            }
        }
        return product;
    }
}

class SparseVector1 {
    List<int[]> list;
    
    SparseVector(int[] nums) {
        list = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                list.add(new int[]{i, nums[i]});
            }
        }
    }
    
    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        List<int[]> other = vec.list;        
        int product = 0;
        int i = 0, j = 0;
        while (i < list.size() && j < other.size()) {
            if (list.get(i)[0] < other.get(j)[0]) {
                i++;
            } else if (list.get(i)[0] > other.get(j)[0]) {
                j++;
            } else {
                product += list.get(i)[1] * other.get(j)[1];
                i++;
                j++;
            }
        }
        return product;
    }
}
