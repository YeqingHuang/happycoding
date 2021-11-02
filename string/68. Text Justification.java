class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // thoughts: greedy approach
        // look ahead, if we can add another word to this line, add it
        // if we cannot add anymore,make alignment (last line is a special case)
        
        // helper functions needed:
        // 1. find how many words can be added to the current line -> findRight
        // 2. make alignment given maxWidth and words to be included -> justify
        // 3. justify the last line -> justifyLastline     
        List<String> ans = new ArrayList<>();
        int start = 0;
        while (start < words.length) {
            int end = findRight(start, words, maxWidth);
            String newLine = justify(start, end, words, maxWidth);
            ans.add(newLine);
            start = end + 1;
        }
        return ans;
    }
    
    private int findRight(int index, String[] words, int maxWidth) {
        int i = index;
        int currLen = words[i].length(); // at least one word can fit in this line
        while (i+1 < words.length && currLen + 1 + words[i+1].length() <= maxWidth) {
            currLen += 1 + words[i+1].length();
            i++;
        }
        return i;
    }
    
    private String justify(int start, int end, String[] words, int maxWidth) {
        // 3 cases:
        // 1) only one word in this line: append spaces to the end
        // 2) more than one word and it's not the last line: calculate average spaces, then add additional spaces
        // 3) more than one word and it's the last line: one space between each word
        
        // case1:
        if (start == end) {
            int remaining = maxWidth - words[start].length();
            return words[start] + " ".repeat(remaining);
        }
        
        // case3:
        if (end == words.length - 1) {
            return justifyLastLine(start, end, words, maxWidth);
        }
        
        // case2:
        int wordLength = 0;
        for (int k=start; k<=end; k++) {
            wordLength += words[k].length();
        }
        int spaces = maxWidth - wordLength;
        int slots = end - start; // word index 2,3,4,5,6, then there are 6 - 4 = 2 slots
        String slotString = " ".repeat(spaces / slots);
        int extraSpaces = spaces % slots;
        
        StringBuilder sb = new StringBuilder();
        // save the last word in this line
        for (int k=start; k< end; k++) {
            sb.append(words[k]);
            sb.append(slotString);
            if (extraSpaces > 0) {
                sb.append(" ");
                extraSpaces--;
            }
        }
        sb.append(words[end]);
        return sb.toString(); 
    }
    
    private String justifyLastLine(int start, int end, String[] words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int k = start; k<end; k++) {
            sb.append(words[k]);
            sb.append(" ");
        }
        sb.append(words[end]);
        // check how many spaces we still need to append
        sb.append(" ".repeat(maxWidth - sb.length()));
        return sb.toString();
    }
}