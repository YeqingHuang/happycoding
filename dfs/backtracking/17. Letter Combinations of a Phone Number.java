class Solution {
    final static String[] numbers = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    List<String> ans;
    
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        if (digits.length() == 0) return ans;
        StringBuilder path = new StringBuilder();
        backtrack(digits, path, 0);
        return ans;
    }
    
    private void backtrack(String digits, StringBuilder path, int start) {
        if (start == digits.length()) {
            StringBuilder copied = new StringBuilder(path);
            ans.add(copied.toString());
            return;
        }
        
        int index = digits.charAt(start) - '0';
        for (char c: numbers[index].toCharArray()) {
            path.append(c);
            backtrack(digits, path, start+1);
            path.deleteCharAt(path.length()-1);
        } 
    }
}