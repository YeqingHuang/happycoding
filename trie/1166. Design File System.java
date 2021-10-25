class FileSystem {
    class TrieNode {
        Map<String, TrieNode> children;
        int val; // default null
        
        public TrieNode() {
            this.children = new HashMap<>();
            val = 0;
        }
    }
    
    TrieNode root;
    public FileSystem() {
        root = new TrieNode();
    }
    
    public boolean createPath(String path, int value) {
        // Returns false if the path already exists 
        // or its parent path doesn't exist.
        if (path.length() <= 1 || path.charAt(0) != '/') {
            return false; // invalid input
        }
        
        TrieNode curr = root;
        String[] parts = path.split("/");
        for (int i=1; i<parts.length - 1; i++) {
            String part = parts[i];
            if (!curr.children.containsKey(part)) {
                return false; // parent path doesn't exist.
            }
            curr = curr.children.get(part);
        }
        String lastPart = parts[parts.length - 1];
        if (curr.children.containsKey(lastPart)) {
            return false; // path already exist
        }
        TrieNode newNode = new TrieNode();
        newNode.val = value;
        curr.children.put(lastPart, newNode);
        return true;
    }
    
    public int get(String path) {
        TrieNode curr = root;
        String[] parts = path.split("/");
        for (int i=1; i<parts.length; i++) {
            String part = parts[i];
            if (!curr.children.containsKey(part)) {
                return -1;
            }
            curr = curr.children.get(part);
        }
        return curr.val == 0 ? -1 : curr.val;
    }
}
