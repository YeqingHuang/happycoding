class SnapshotArray { 
    int snapId;
    List<TreeMap<Integer, Integer>> maps;
    
    public SnapshotArray(int length) {
        snapId = 0;
        maps = new ArrayList<>();
        for (int i=0; i<length; i++) {
            maps.add(new TreeMap<>());
            // an edge case: if person calls snap() before set()
            // we must record the initial status for him, i.e. all values are 0
            maps.get(i).put(0,0);
        }
    }
    
    public void set(int index, int val) {
        TreeMap<Integer, Integer> indexMap = maps.get(index);
        indexMap.put(snapId, val);
    }
    
    public int snap() {
        // take a snap of the array and return snap_id (0 indexing based)
        // the recorded snap needs to be returned in get() method
        snapId++;
        return snapId - 1;
    }
    
    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> indexMap = maps.get(index);
        Integer floorKey = indexMap.floorKey(snap_id);
        return indexMap.get(floorKey);
    }
}