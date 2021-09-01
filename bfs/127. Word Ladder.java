class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // shortest path: bfs
        // step1: preprocess wordList, key is a generic word {*ot: hot, dot, lot}
        // so words in its value are neighbours with 1 step
        // step2: bfs how to prevent going backwards?
        // use a hashset to record
        
        if (!wordList.contains(endWord)) return 0;
        
        Map<String, List<String>> map = buildGraph(wordList);
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        int n = beginWord.length();
        int step = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); 
            step++; // add 1 once we land on a new level
            for (int k=0; k<levelSize; k++) {
                String curr = queue.poll();
                visited.add(curr);
                for (int i=0; i<n; i++) {
                    String generic = curr.substring(0,i) + "*" + curr.substring(i+1,n);
                    if (map.containsKey(generic)) {
                        List<String> neighbours = map.get(generic);
                        for (String neighbour: neighbours) {
                            if (neighbour.equals(endWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(neighbour)) {
                                queue.add(neighbour);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    private Map<String, List<String>> buildGraph(List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s: wordList) {
            for (int i=0; i<s.length(); i++) {
                String generic = s.substring(0,i) + "*" + s.substring(i+1,s.length());
                map.putIfAbsent(generic, new ArrayList<>());
                map.get(generic).add(s);
            }
        }
        return map;
    }
}