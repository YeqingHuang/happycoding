class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] beingTrusted = new int[n+1];
        for (int[] pair: trust) {
            int person = pair[1];
            if (beingTrusted[person] != -1) {
                // this person has not trusted others yet
                beingTrusted[person]++;
            }
            beingTrusted[pair[0]] = -1; // pair[0] could not be the judge
        }
        
        // check the results
        for (int i=1; i<=n; i++) {
            if (beingTrusted[i] == n-1) {
                return i;
            }
        }
        return -1;
    }
}