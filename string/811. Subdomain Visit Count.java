class Solution {
    Map<String, Integer> map;
    
    public List<String> subdomainVisits(String[] cpdomains) {
        // key is domain or subdomain, value is its count
        map = new HashMap<>();
        for (String s: cpdomains) {
            analyzeDomain(s);
        }
        
        List<String> ans = new ArrayList<>();
        for (String key: map.keySet()) {
            String record = map.get(key) + " " + key;
            ans.add(record);
        }
        return ans;
    }
    
    private void analyzeDomain(String s) {
        String[] parts = s.split(" ");
        int count = Integer.parseInt(parts[0]);
        String domain = parts[1]; 
        int index = domain.indexOf('.');
        while (index != -1) {
            map.put(domain, map.getOrDefault(domain, 0) + count);
            domain = domain.substring(index+1, domain.length());
            index = domain.indexOf('.');
        }
        // don't forget the last part
        map.put(domain, map.getOrDefault(domain, 0) + count);
    }
}