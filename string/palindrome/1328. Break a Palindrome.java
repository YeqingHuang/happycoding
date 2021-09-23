class Solution {
    public String breakPalindrome(String palindrome) {
        // "abccba" -> "aaccba"
        // strategy is to change the first non-'a' char to 'a'
        // but for "aba" -> "abb" this does not work
        // when there are all "a"s, change the last char to 'b'
        int n = palindrome.length();
        if (n == 1) return "";
        
        int count = countA(palindrome);
        if (count == n || count == n-1) {
            return palindrome.substring(0,n-1) + "b";
        } else {
            int index = findFirstNonA(palindrome);
            return palindrome.substring(0, index) + "a" + palindrome.substring(index+1);
        }
    }
    
    private int countA(String s) {
        int count = 0;
        for (char c: s.toCharArray()) {
            if (c == 'a') count++;
        }
        return count;
    }
    
    private int findFirstNonA(String s) {
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) != 'a') {
                return i;
            }
        }
        return -1;
    }
}