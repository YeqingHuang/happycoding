class MyCalendar {
    TreeMap<Integer, Integer> map;
    
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        if (map.containsKey(start)) return false;
        
        Integer prevStart = map.floorKey(start);
        Integer nextStart = map.ceilingKey(start);
        if (prevStart != null && map.get(prevStart) > start) {
            return false; // overlap with the previous interval
        }
        if (nextStart != null && nextStart < end) {
            return false; // overlap with the next interval
        }
        
        map.put(start, end);
        return true;
    }
}