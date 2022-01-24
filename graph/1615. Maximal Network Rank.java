class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int maxRank = 0;
        if (roads.length == 0) return maxRank;
        
        boolean[][] connected = new boolean[n][n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] road: roads) {
            int a = road[0], b = road[1];
            connected[a][b] = true;
            connected[b][a] = true;
            map.put(a, map.getOrDefault(a, 0) + 1);
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        
        // suppose we only examine half of the matrix
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                int rank = map.getOrDefault(i,0) + map.getOrDefault(j,0);
                if (connected[i][j]) rank--;
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }
}