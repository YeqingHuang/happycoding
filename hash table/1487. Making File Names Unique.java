class Solution {
    public String[] getFolderNames(String[] names) {
        // ["gta(2)","gta(5)","gta(2)"]
        // the answer is not ["gta(2)","gta(5)","gta"]
        // it should be ["gta(2)","gta(5)","gta(2)(1)"]
        
        // ["gta(2)","gta(2)","gta(2)(1)", "gta(2)(1)"]
        // output ["gta(2)","gta(2)(1)","gta(2)(1)(1)","gta(2)(1)(2)"]
        HashMap<String, Integer> map = new HashMap<>(); // current file name, next available serial num
        
        String[] ans = new String[names.length];
        for(int i = 0; i < names.length; i++) {
            String name = names[i];
            // just two cases: collision or no collision
            if (map.containsKey(name)) {
                // we need to find a valid serial num for it
                int val = map.get(name);
                String proposed = generateNext(name, val);
                while (map.containsKey(proposed)) {
                    val++;
                    proposed = generateNext(name, val);
                }
                ans[i] = proposed;
                map.put(proposed, 1);
                // we dont't use name successfully, why we still need to put {name, val+1}?
                map.put(name, val+1);
            } else {
                ans[i] = name;
                map.put(name, 1);
            }
        }
        return ans;
    }
    
    private String generateNext(String base, int version) {
        return base + "(" + version + ")";
    }
}