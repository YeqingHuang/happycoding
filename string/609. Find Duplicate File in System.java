class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        // dup files: have the same content, but different file path or name
        // key is content, value is a list of file path + file name
        Map<String, List<String>> map = new HashMap<>();
        for (String path: paths) {
            List<String[]> processed = processInput(path);
            for (String[] piece: processed) {
                map.putIfAbsent(piece[0], new ArrayList<>());
                map.get(piece[0]).add(piece[1]);
            }
        }
        
        // only when a key has at least two strings, it can be an answer
        List<List<String>> ans = new ArrayList<>();
        for (List<String> files: map.values()) {
            if (files.size() >= 2) {
                ans.add(files);
            }
        }
        return ans;
    }
    
    private List<String[]> processInput(String s) {
        // input "root/a 1.txt(abcd) 2.txt(efgh)"
        // each string[] has two parts: content, path + file name
        String[] parts = s.split(" ");
        String filePath = parts[0] + "/";
        List<String[]> ans = new ArrayList<>();
        for (int i=1; i<parts.length; i++) {
            int leftBracket = parts[i].indexOf('(');
            String fileName = parts[i].substring(0, leftBracket);
            String content = parts[i].substring(leftBracket+1, parts[i].length()-1);
            ans.add(new String[]{content, filePath + fileName});
        }
        return ans;
    }

    private List<String[]> processInput1(String s) {
        // use s.split is faster than s.substring
        String[] parts = s.split(" ");
        String filePath = parts[0] + "/";
        List<String[]> ans = new ArrayList<>();
        for (int i=1; i<parts.length; i++) {
            String[] nameContent =parts[i].split("\\(");
            nameContent[1] = nameContent[1].replace(")", "");
            ans.add(new String[]{nameContent[1], filePath + nameContent[0]});
        }
        return ans;
    }
}