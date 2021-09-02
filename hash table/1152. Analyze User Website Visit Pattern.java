class Solution {
    class Record {
        int timestamp;
        String website;
        
        public Record(int timestamp, String website) {
            this.timestamp = timestamp;
            this.website = website;
        }
    }
    
    Map<String, List<Record>> map;
    Map<String, Integer> freqMap;
    
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // find a subsequence of length 3 that's most popular
        map = new HashMap<>();
        for (int i=0; i<username.length; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Record(timestamp[i], website[i]));
        }
        
        freqMap = new HashMap<>();
        for (String user: map.keySet()) {
            getSubsequence(map.get(user));
        }
        
        // If there is more than one pattern with the same largest score,
        // return the lexicographically smallest such pattern
        String popularPattern = "|"; // "|" is bigger than 'z'
        int maxCount = 0;
        for (String pattern: freqMap.keySet()) {
            int count = freqMap.get(pattern);
            if (count > maxCount || (count == maxCount && pattern.compareTo(popularPattern) < 0)) {
                popularPattern = pattern;
                maxCount = count;
            }
        }
        String[] parts = popularPattern.split(",");
        List<String> ans = new ArrayList<>();
        for (String part: parts) {
            ans.add(part);
        }
        return ans;
    }
    
    private void getSubsequence(List<Record> records) {
        if (records.size() < 3) return;
        
        Set<String> visited = new HashSet<>(); // for this user only
        Collections.sort(records, (a,b) -> a.timestamp - b.timestamp);
        for (int i=0; i<records.size()-2; i++) {
            for (int j=i+1; j<records.size()-1; j++) {
                for (int k=j+1; k<records.size(); k++) {
                    String pattern = records.get(i).website + "," 
                        + records.get(j).website + ","+ records.get(k).website;
                    if (!visited.contains(pattern)) {
                        visited.add(pattern);
                        freqMap.put(pattern, freqMap.getOrDefault(pattern, 0) + 1);
                    }
                }
            }
        }
    }
}