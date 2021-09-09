class Solution {
    public String orderlyQueue(String s, int k) {
        // k == 1 is a special case, we rotate the array by 1 each time
        // until we find the best answer
        // k >= 2 is easier, once we are allowed to choose either one of two
        // it's like we can swap two adjacent characters
        // consider bubble sort, all permutations are possible, just sort
        char[] chars = s.toCharArray();
        if (k == 1) {
            return findBestString(chars);
        }
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
    
    private String findBestString(char[] chars) {
        List<String> possible = new ArrayList<>();
        char smallest = '|';
        for (char c: chars) {
            if (c < smallest) {
                smallest = c;
            }
        }
        for (int i=0; i<chars.length; i++) {
            if (chars[i] == smallest) {
                char[] rotation = new char[chars.length];
                for (int k=i; k<chars.length; k++) {
                    rotation[k-i] = chars[k];
                }
                for (int k=0; k<i; k++) {
                    rotation[k+chars.length-i] = chars[k];
                }
                possible.add(String.valueOf(rotation));
            }
        }
        Collections.sort(possible);
        return possible.get(0);
    }
}

class Solution1 {
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            return findBestString(s);
        } 
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    private String findBestString(String s) {
        String ans = s;
        for (int i = 0; i < s.length(); ++i) {
            String temp = s.substring(i) + s.substring(0, i);
            if (temp.compareTo(ans) < 0) {
                ans = temp;
            }
        }
        return ans;
    }
}