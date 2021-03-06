class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // a mutable hashmap cannot be used as a key
        Map<String, List<String>> map = new HashMap<>();
        for (String word: strs) {
            String key = getKey(word);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }
        return new ArrayList<>(map.values());
    }
    
    
    private String getKey(String s) {
        int[] freq = new int[26];
        for (char c: s.toCharArray()) {
            freq[c-'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<26; i++) {
            // another way is not checking if freq[i] > 0
            // use sb.append('#'); sb.append(count[i]);
            if (freq[i] > 0) {
                sb.append((char) i+'a');
                sb.append(freq[i]);
            }
        }
        return sb.toString();
    }
    // a much simpler way it to sort the string
    private String getKey1(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}