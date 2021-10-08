class Trie {
    
    class TrieNode {
        HashMap<Character, TrieNode> children;
        String val; // stores the word at end node
        
        public TrieNode() {
            children = new HashMap<>();
        }
    }
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                TrieNode newNode = new TrieNode();
                curr.children.put(c, newNode);
                curr = newNode;
            } else {
                curr = curr.children.get(c);
            }
        }
        curr.val = word;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;
        for (char c: word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return false;
            } else {
               curr = curr.children.get(c); 
            }
        }
        // don't use curr.val.equals(word), NullPointerException
        return curr.val != null;
    }
    
    public boolean startsWith(String prefix) {
        // suppose an existing word is "apple"
        // prefix can be "app" or "apple", we return true
        TrieNode curr = root;
        for (char c: prefix.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return false;
            } else {
               curr = curr.children.get(c); 
            }
        }
        return true;
    }
}