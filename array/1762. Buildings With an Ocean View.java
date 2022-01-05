class Solution {
    public int[] findBuildings(int[] heights) {
        List<Integer> hasView = new ArrayList<>();
        int n = heights.length;
        int currMax = heights[n-1];
        hasView.add(n-1);
        for (int i=n-2; i>=0; i--) {
            if (heights[i] > currMax) {
                currMax = heights[i];
                hasView.add(i);
            }
        }
        Collections.reverse(hasView);
        
        int[] ans = new int[hasView.size()];
        for (int k=0; k<hasView.size(); k++) {
            ans[k] = hasView.get(k);
        }
        return ans;
    }
}