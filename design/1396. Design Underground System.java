class UndergroundSystem {
    // only consider the completed records when calling getAverageTime
    // use a hashmap to store, key is "start-end", value is a list of outT - inT, id does not matter
    // how to store those imcomplated records?
    // use a another hashmap, key is is, value is time. When we call checkout, remove it from the map
    Map<String, List<Integer>> records;
    Map<Integer, Integer> inTime;
    Map<Integer, String> inStation;
    
    public UndergroundSystem() {
        records = new HashMap<>();
        inTime = new HashMap<>();
        inStation = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        inTime.put(id, t);
        inStation.put(id, stationName);
    }
    
    public void checkOut(int id, String stationName, int t) {
        int commuteTime = t - inTime.get(id);
        String fromTo = inStation.get(id) + "-" + stationName;
        records.putIfAbsent(fromTo, new ArrayList<>());
        records.get(fromTo).add(commuteTime);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        int total = 0;
        for (int record: records.get(key)) {
            total += record;
        }
        return (double) total / records.get(key).size();
    }
}

class UndergroundSystem1 {
    // instead of calculating sum every time, we can directly store sum and count in the first map
    // also, it does not make sense to put inRecords in two seperate maps, use one instead
    // based on this two reasons, we should use Pair
    
    HashMap<String, Pair<Integer, Integer>> records; // start-end :{TotalTime, Count}
    HashMap<Integer, Pair<String, Integer>> inRecords; // id: {inStation, Time}
    
    public UndergroundSystem() {
        records = new HashMap<>();
        inRecords = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        inRecords.put(id, new Pair<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> in = inRecords.get(id);
        String route = in.getKey() + "-" + stationName;
        int commuteTime = t - in.getValue();
        if (records.containsKey(route)) {
            int currTotal = records.get(route).getKey();
            int currCount = records.get(route).getValue();
            records.put(route, new Pair<>(currTotal + commuteTime, currCount + 1));
        } else {
            records.put(route, new Pair<>(commuteTime, 1));
        }           
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        Pair<Integer, Integer> record = records.get(route);
        return (double) record.getKey()/record.getValue();
    }
}