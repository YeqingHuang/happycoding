class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        List<Pair<Character, Integer>> list = new ArrayList<>();
        for (char key: map.keySet()) {
            list.add(new Pair<Character, Integer>(key, map.get(key)));
        }
        Collections.sort(list, (a,b) -> b.getValue() - a.getValue());
        
        StringBuilder sb = new StringBuilder();
        for (Pair pair: list) {
            int freq = (int) pair.getValue();
            char curr = (char) pair.getKey();
            for (int i=0; i<freq; i++) {
                sb.append(curr);
            }
        }
        return sb.toString();
    }
}


class Solution1 {
    // another method without sorting the key-value pairs
    // use bucket sort
    public String frequencySort(String s) {
            
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        int maximumFrequency = Collections.max(counts.values());

        // Make the list of buckets and apply bucket sort.
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= maximumFrequency; i++) {
            buckets.add(new ArrayList<Character>());
        }
        for (Character key : counts.keySet()) {
            int freq = counts.get(key);
            buckets.get(freq).add(key);
        }

        // Build up the string. 
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.size() - 1; i >= 1; i--) {
            for (Character c : buckets.get(i)) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}