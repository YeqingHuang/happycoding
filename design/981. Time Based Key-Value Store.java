class TimeMap {
    
    Map<String, TreeMap<Integer, String>> map;
    
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        TreeMap<Integer,String> currMap = map.get(key);
        Integer lowerKey = currMap.floorKey(timestamp);
        return lowerKey == null ? "" : currMap.get(lowerKey); 
    }
}


class TimeMap1 {
    // one assumption is that timestamp in set() is strictly increasing
    // so we can also use arraylist and binary search instead of a treemap
    Map<String, List<Pair<Integer, String>>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        return binarySearch(map.get(key), timestamp);
    }
    
    private String binarySearch(List<Pair<Integer, String>> list, int target) {
        // Collections.sort(list, (a,b) -> a.getKey() - b.getKey());
        int low = 0, high = list.size() - 1;
        while (low < high - 1) {
            int mid = low + (high - low)/2;
            Pair<Integer, String> pair = list.get(mid);
            if (pair.getKey() == target) {
                return pair.getValue();
            } else if (pair.getKey() < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (list.get(high).getKey() <= target) {
            return list.get(high).getValue();
        } else if (list.get(low).getKey() <= target) {
            return list.get(low).getValue();
        }
        return "";
    }
}