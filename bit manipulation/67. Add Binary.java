class Solution {
    public String addBinary(String a, String b) {
        // a and b could be long, do not convert them into decimals
        if (a.equals("0") && b.equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        while (i>=0 || j>=0) {
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = x + y + carry;
            sb.append(sum % 2);
            carry = sum / 2;
            i--;
            j--;
        }
        if (carry == 1) sb.append('1');
        
        sb.reverse();
        return sb.toString();
    }
}