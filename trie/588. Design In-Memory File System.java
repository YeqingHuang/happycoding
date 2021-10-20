class FileSystem {
    // use a trie to preserve the structure
    // for the trieNode class, let's call it node
    // if it's a file, it should have some string content
    // otherwise it's treated as a folder 
    class Node {
        boolean isFile;
        HashMap<String, Node> children;
        String content;
        
        public Node() {
            this.isFile = false;
            this.children = new HashMap<>();
            this.content = "";
        }
    }
    
    Node root;
    
    public FileSystem() {
        root = new Node();
    }
    
    // "/a/b/c" becomes [, a, b, c]
    // after we split by "/", we always start from index = 1   
    public List<String> ls(String path) {
        Node curr = root;
        List<String> ans = new ArrayList<>();
        if (!path.equals("/")) {
            // general case
            String[] parts = path.split("/");
            for (int i=1; i<parts.length; i++) {
                curr = curr.children.get(parts[i]);
            }
            if (curr.isFile) {
                // return a list that only contains this file's name
                String fileName = parts[parts.length -1];
                ans.add(fileName);
            } else {
                // return the list of file and directory names in this folder
                ans.addAll(curr.children.keySet());
                Collections.sort(ans);
            }
            return ans;
        }
        
        // we are checking "/", it cannot be a file itself
        ans.addAll(curr.children.keySet());
        Collections.sort(ans);
        return ans;
    }
    
    public void mkdir(String path) {
        Node curr = root;
        String[] parts = path.split("/");
        for (int i=1; i< parts.length; i++) {
            String dest = parts[i];
            if (!curr.children.containsKey(dest)) {
                curr.children.put(dest, new Node());
            }
            curr = curr.children.get(dest);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        // this is an existing filePath, we don't need to worry about mkdir
        Node curr = root;
        String[] parts = filePath.split("/");
        for (int i=1; i<parts.length - 1; i++) {
            curr = curr.children.get(parts[i]);
        }
        
        // we either create a new file, or we append content to the existing file
        String fileName = parts[parts.length - 1];
        if (!curr.children.containsKey(fileName)) {
            curr.children.put(fileName, new Node());
        }
        curr = curr.children.get(fileName);
        curr.isFile = true;
        curr.content = curr.content + content;
    }
    
    public String readContentFromFile(String filePath) {
        Node curr = root;
        String[] parts = filePath.split("/");
        for (int i=1; i<parts.length; i++) {
            curr = curr.children.get(parts[i]);
        }
        return curr.content;
    }
}