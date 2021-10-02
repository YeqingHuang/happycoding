class Solution {
    public String countOfAtoms(String formula) {
        // "K4(ON(SO3)2)2"
        // {'K': 4, 'N': 2, 'O': 14, 'S': 4}
        // similar to Decode String, we can only calculate when we meet ')' and then X2
        // obviously, we need a stack, but in the stack what do we store?
        // (SO3)2, the stack stores a hashmap, key is string because of "Mg","Be" etc
        Stack<Map<String,Integer>> stack = new Stack<>();
        Map<String,Integer> map = new HashMap<>();
        int i = 0;
        int n = formula.length();
        while (i < n) {
            char c = formula.charAt(i++);
            if (c == '(') {
                stack.push(map);
                map = new HashMap<>();
            } else if (c == ')') {
                // first thing: find the multiplier
                int multi = 0;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    multi = multi * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                // secondly: do the multiplication
                if (multi == 0) {
                    multi = 1;
                }
                Map<String, Integer> prevMap = stack.pop();
                for (String key: map.keySet()) {
                    int addedCount = map.get(key) * multi;
                    prevMap.put(key, prevMap.getOrDefault(key, 0) + addedCount);
                }
                map = prevMap;
            } else {
                // this is a letter, we should not skip it
                int start = i-1;
                if (i<n && Character.isLowerCase(formula.charAt(i))){
                    i++;
                }
                String s= formula.substring(start,i);
                int count = 0;
                while(i<n && Character.isDigit(formula.charAt(i))) {
                    count = count * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                if (count == 0) count = 1;
                map.put(s,map.getOrDefault(s, 0) + count);
            }
        }

        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (String element: list) {
            sb.append(element);
            if (map.get(element) > 1) {
                sb.append(map.get(element));
            }
        }
        return sb.toString();
    }
}