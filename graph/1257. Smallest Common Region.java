class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> ancestors = new HashMap<>();
        for (List<String> region : regions) {
            for (int i=1; i<region.size(); i++) {
                ancestors.put(region.get(i), region.get(0));
            }
        }
        
        // build a trace of region1's ancestors
        Set<String> trace = new HashSet<>();
        String curr = region1;
        while (curr != null) {
            trace.add(curr);
            curr = ancestors.get(curr);
        }
        // now go upward again for region2
        // this time we can stop once an ancestor appears in trace
        curr = region2;
        while (!trace.contains(curr)) {
            curr = ancestors.get(curr);
        }
        return curr;      
    }
}