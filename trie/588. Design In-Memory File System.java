class FileSystem {
    
    class Node {
        HashMap<String, Node> children;
        StringBuilder content;
        boolean isFile;
        
        public Node() {
            this.children = new HashMap<>();
            this.content = new StringBuilder();
            this.isFile = false;
        }
    }
    
    private Node root;
    
    public FileSystem() {
        root = new Node(); // a dummy node
    }
    
    public List<String> ls(String path) {
        // note that the answer should in lexicographic order
        Node curr = root;
        List<String> ans = new ArrayList<>();
    
        // case1: it's the root
        if (path.equals("/")) {
            ans.addAll(curr.children.keySet());
            Collections.sort(ans);
            return ans;
        }
        
        // case2: it's a normal path
        // "/a/b/c" becomes [, a, b, c]
        // after we split by "/", we should start from index = 1  
        String[] parts = path.split("/");
        for (int i=1; i<parts.length; i++) {
            curr = curr.children.get(parts[i]);
        }
        // now curr stays at either a file path or a folder
        if (curr.isFile) {
            // the answer only contails one string, i.e. file name
            ans.add(parts[parts.length - 1]);
        } else {
            ans.addAll(curr.children.keySet());
            Collections.sort(ans);
        }
        return ans;
    }
    
    public void mkdir(String path) {
        Node curr = root;
        String[] parts = path.split("/");
        for (int i=1; i< parts.length; i++) {
            String part = parts[i];
            if (!curr.children.containsKey(part)) {
                curr.children.put(part, new Node());
            }
            curr = curr.children.get(part);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        Node curr = root;
        String[] parts = filePath.split("/");
        // save the last step, because the file name may not exist
        for (int i=1; i<parts.length - 1; i++) {
            curr = curr.children.get(parts[i]);
        }
        
        String fileName = parts[parts.length - 1];
        if (!curr.children.containsKey(fileName)) {
            curr.children.put(fileName, new Node());
        }
        curr = curr.children.get(fileName);
        
        curr.isFile = true;
        curr.content.append(content);
    }
    
    public String readContentFromFile(String filePath) {
        Node curr = root;
        String[] parts = filePath.split("/");
        for (int i=1; i<parts.length; i++) {
            curr = curr.children.get(parts[i]);
        }
        return curr.content.toString();
    }
}