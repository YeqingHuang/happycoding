class Solution {
    class TrieNode {
        Map<Character, TrieNode> children;
        String val;
        
        public TrieNode() {
            this.children = new HashMap<>();
        }
    }
    
    TrieNode root;
    List<String> ans;
    
    public List<String> findWords(char[][] board, String[] words) {
        // build a trie with the given words
        // then explore each path on the board according to this trie
        root = buildTrie(words);
        ans = new ArrayList<>();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                findPath(board, i, j, root);
            }
        }
        return ans;
    }
    
    private void findPath(char[][] board, int i, int j, TrieNode node) {
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        if (i<0 || i>=board.length || j<0 || j>=board[0].length) {
            return;
        }
        
        char c = board[i][j];
        if (node.children.containsKey(c)) {
            node = node.children.get(c);
            if (node.val != null) {
                ans.add(node.val);
                node.val = null; // important
            }
            board[i][j] = '*';
            for (int[] dir: dirs) {
                findPath(board, i+dir[0], j+dir[1], node);
            }
            board[i][j] = c; // restore
        }
        
    }
    
    private TrieNode buildTrie(String[] words) {
        root = new TrieNode();
        for (String word: words) {
            TrieNode curr = root;
            for (char c: word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    TrieNode newNode = new TrieNode();
                    curr.children.put(c, newNode);
                }
                curr = curr.children.get(c);
            }
            curr.val = word;
        }
        return root;
    }
}

// use an array to make it faster
class Solution1 {
    class TrieNode {
        TrieNode[] children;
        String val;
        
        public TrieNode() {
            children = new TrieNode[26];
        }
    }
    
    List<String> ans;
    TrieNode root;
    
    public List<String> findWords(char[][] board, String[] words) {
        ans = new ArrayList<>();
        root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root);
            }
        }
        return ans;
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode node) {
        char c = board[i][j];
        if (c == '*' || node.children[c-'a'] == null) return;
        
        node = node.children[c-'a'];
        // case1: this is an ending
        if (node.val != null) {
            ans.add(node.val);
            node.val = null;
        }
        
        // case2: continue on this path
        board[i][j] = '*';
        if (i>0) dfs(board, i - 1, j, node);
        if (j>0) dfs(board, i, j - 1, node);
        if (i<board.length - 1) dfs(board, i + 1, j, node);
        if (j<board[0].length - 1) dfs(board, i, j + 1, node);
        board[i][j] = c;
    }
    
    private TrieNode buildTrie(String[] words) {
        root = new TrieNode();
        for (String word: words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.val = word;
        }
        return root;
    }
}