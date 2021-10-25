class Solution {
    // dfs
    static int unvisited = 0;
    static int beingProcessed = 1;
    static int processed = 2;
    
    boolean isPossible;
    Map<Character, Integer> status;
    Map<Character, Set<Character>> outMap;
    
    public String alienOrder(String[] words) {
        isPossible = true;
        status = new HashMap<>();
        outMap = new HashMap<>();
        for (String word: words) {
            for (char c: word.toCharArray()) {
                status.put(c, unvisited);
            }
        }
        
        for (int i=0; i<words.length-1; i++) {
            addOneEdge(words[i], words[i+1]);
            if (!isPossible) return "";
        }
                
        StringBuilder sb = new StringBuilder();
        for (char key: status.keySet()) {
            if (status.get(key) == unvisited) {
                dfs(sb, key);
            }
        }
        if (sb.length() < status.size()) return "";
        return sb.reverse().toString();
    }
    
    private void dfs(StringBuilder sb, char c) {
        if (!isPossible) return;
        
        status.put(c, beingProcessed);
        if (outMap.containsKey(c)) {
            Set<Character> neighbours = outMap.get(c);
            for (char nei: neighbours) {
                if (status.get(nei) == unvisited) {
                    dfs(sb, nei);
                } else if (status.get(nei) == beingProcessed) {
                    isPossible = false;
                    return;
                }
            }
        }
        status.put(c, processed);
        sb.append(c);
    }
    
    private void addOneEdge(String s1, String s2) {
        int i=0;
        while (i<s1.length() && i<s2.length()) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                // it's an edge from c1 to c2
                outMap.putIfAbsent(c1, new HashSet<>());
                outMap.get(c1).add(c2);
                return;
            } else {
                i++;
            }
        }
        if (s1.length() > s2.length()) isPossible = false;
    }
}

class Solution {
    // bfs
    Map<Character, Integer> inMap;
    Map<Character, Set<Character>> outMap;
    public String alienOrder(String[] words) {
        // observations:
        // if we have "ab" and "abc" we know nothing about c, but it still needs to be included
        // if we have ["abc", "ab"], we can break because it is already invalid
        // we compare two words at a time and get some edges
        // use these edges to get a topological order
        
        inMap = new HashMap<>(); // a char and its indegree
        outMap = new HashMap<>(); // a char and its outgoing neighbours
        for (String word: words) {
            for (char c: word.toCharArray()) {
                inMap.put(c, 0);
            }
        }
        
        for (int i=0; i<words.length-1; i++) {
            boolean result = addOneEdge(words[i], words[i+1]);
            if (!result) return "";
        }
        
        Queue<Character> queue = new LinkedList<>();
        for (char key: inMap.keySet()) {
            if (inMap.get(key) == 0) {
                queue.add(key);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            ans.append(curr);
            if (outMap.containsKey(curr)) {
                Set<Character> neighbours = outMap.get(curr);
                for (char next: neighbours) {
                    inMap.put(next, inMap.get(next) - 1);
                    if (inMap.get(next) == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        if (ans.length() == inMap.size()) return ans.toString();
        return "";
    }
    
    private boolean addOneEdge(String s1, String s2) {
        int i=0;
        while (i<s1.length() && i<s2.length()) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                // it's an edge from c1 to c2
                outMap.putIfAbsent(c1, new HashSet<>());
                if (!outMap.get(c1).contains(c2)) {
                    outMap.get(c1).add(c2);
                    inMap.put(c2, inMap.get(c2) + 1);
                }
                return true;
            } else {
                i++;
            }
        }
        return s1.length() <= s2.length();
    }
}