class RandomizedSet {
    // thoughts:
    // insert and remove seems to be working with just a set
    // but getRandom needs us to have an arraylist structure
    // insert can be set.add() and arraylist.add()
    // however, remove() cannot be O(1), we need to know the index
    // therefore, convert the set to a hashmap, key is val, value is its index in the arraylist
    // when we remove(), replace the current element with the last element, then remove last element
    private Map<Integer, Integer> map;
    private List<Integer> numList;
    
    public RandomizedSet() {
        map = new HashMap<>();
        numList = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        numList.add(val);
        map.put(val, numList.size()-1);
        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.remove(val);
        if (index != numList.size() - 1) {
            int lastVal = numList.get(numList.size() - 1);
            numList.set(index, lastVal);
            map.put(lastVal, index);
        }
        numList.remove(numList.size() - 1);
        return true;
    }
    
    public int getRandom() {
        int max = numList.size() - 1;
        Random r = new Random();
        int index = r.nextInt(max+1);
        return numList.get(index);
    }
}