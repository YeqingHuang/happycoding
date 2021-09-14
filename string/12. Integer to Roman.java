class Solution {
    // p.s. we don't need to use map, we can use another String[] with same length
    final static int[] values = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
    final static Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
    }
    
    public String intToRoman(int num) {
        // 1994 is breakdown to 1000 + 500 + 4*100 + 50 + 4*10 + 4*1
        StringBuilder sb = new StringBuilder();
        char symbol;
        while (num > 0) {
            int divisor = findDivisor(num);
            int q = num / divisor;
            sb.append(map.get(divisor).repeat(q));
            num = num % divisor;
        }
        return sb.toString();
    }
    
    private int findDivisor(int num) {
        for (int i=0; i<values.length-1; i++) {
            if (num >= values[i] && num < values[i+1]) {
                return values[i];
            }
        }
        return values[values.length-1];
    }
}

// a much cleaner way
class Solution {
    static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
    static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num > 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
