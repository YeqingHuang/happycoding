class Solution {
    final Map<String, Integer> map = new HashMap<>();
    
    public int romanToInt(String s) {
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
           
        int ans = 0;
        int i = 0;
        while (i < s.length()) {
            if (i < s.length() - 1 && map.containsKey(s.substring(i, i+2))) {
                ans += map.get(s.substring(i, i+2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i+1));
                i++;
            }
        }
        return ans;
    }
}

class Solution {
    // a faster solution with no s.substring()
    final static Map<Character, Integer> map = new HashMap<>();
    
    static {
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
    }
    
    public int romanToInt(String s) {
        // observations:
        // if I appears on the left of V or X, we -1, IV = 4, IX = 9
        // if X appears on the left of L and C, we -10, XL = 40, XC = 90
        // if C appears on the left of D or M, we -100, CD = 400, CM = 900
        int[] nums = new int[s.length()];
        for (int i=0; i<s.length(); i++) {
            nums[i] = map.get(s.charAt(i));
        }
        
        int ans = 0;
        for (int i=0; i<nums.length-1; i++) {
            if (nums[i] < nums[i+1]) {
                // this is definitely a minus case
                ans -= nums[i];
            } else {
                ans += nums[i];
            }
        }
        return ans + nums[nums.length-1];
    }
}