class WordDistance {
    
    Map<String,List<Integer>> map;
    public WordDistance(String[] wordsDict) {
        // [["practice", "makes", "perfect", "coding", "makes"]]
        // if we query ["makes", "coding"], first pair has a distance of 2, second pair has 1
        // therefore return 1
        map = new HashMap<>();
        for (int i=0; i< wordsDict.length; i++) {
            String word = wordsDict[i];
            map.putIfAbsent(word, new ArrayList<>());
            map.get(word).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> a = map.get(word1);
        List<Integer> b = map.get(word2);
        int i = 0, j = 0;
        int minDist = Integer.MAX_VALUE;
        while (i < a.size() && j < b.size()) {
            minDist = Math.min(minDist, Math.abs(a.get(i) - b.get(j)));
            if (a.get(i) < b.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return minDist;
    }
}