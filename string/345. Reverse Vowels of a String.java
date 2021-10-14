class Solution {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // step1: locate to a vowel
            while (i<j && !isVowel(chars[i])) {
                i++;
            }
            while (i<j && !isVowel(chars[j])) {
                j--;
            }
            // step2: swap and continue
            swap(chars, i, j);
            i++;
            j--;
        }
        return String.valueOf(chars);
    }
    
    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}