class Solution {
    private List<String> ans;
    
    public List<String> addOperators(String num, int target) {
        // between two digits, there are 4 options:
        // + , - , * , no operand
        // * is tricky, 1 + 2 - 4 * 12, it's not calculated from left to right
        // also, we cannot have 1 + 05 or 1 * 05, in this case, no operand does not work
        if (num == null || num.length() == 0) {
            return new ArrayList<String>();
        }
        
        ans = new ArrayList<String>();
        backtracking(num, target, new StringBuilder(), 0, 0, 0);
        return ans;
    }
    
    public void backtracking(String s, int target, StringBuilder path, int index, long val, long multiVal) {
        if (index == s.length()) {
            if (val == target) ans.add(path.toString());
            return;
        }
        
        // multiVal is the delta is previous round, we need this value when we add "*"
        for (int i=index; i<s.length(); i++) {
            if (s.charAt(index) == '0' && i > index) {
                // avoid cases like placing +/-/* between 0 and the next digit
                break;
            }
            int len = path.length();
            long delta = Long.parseLong(s.substring(index, i + 1));
            if (index == 0) {
                // for the first digit, we only have one option, i.e. + digit
                backtracking(s, target, path.append(delta), i+1, delta, delta);
                path.setLength(len);
            } else {
                backtracking(s, target, path.append("+").append(delta), i + 1, val + delta , delta);
                path.setLength(len);
                
                backtracking(s, target, path.append("-").append(delta), i + 1, val - delta, -delta);
                path.setLength(len);
                
                // 1 + 2 + 3 * 4, when we get *4, the ans is (1 + 2 + 3) - 3 + (3 * 4)
                backtracking(s, target, path.append("*").append(delta), i + 1, val - multiVal + multiVal * delta, multiVal * delta);
                path.setLength(len);
            }
        }
    }
}