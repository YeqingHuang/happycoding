class Solution {
    class Item {
        int dist;
        int price;
        int row, col;
        
        public Item(int dist, int price, int row, int col) {
            this.dist = dist;
            this.price = price;
            this.row = row;
            this.col = col;
        }
    }
    
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        // pricing contains low and high
        // start is (x,y), the starting coordinate
        // k means we should return the first k highest-ranked items' coordinates
        
        // ranking is decided by several criteria
        // 1. shorter distance is preferred (from the starting cell)
        // 2. lower price is preferred
        // 3. lower row number is preferred
        // 4. lower col number is preferred
        List<Item> items = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        int low = pricing[0], high = pricing[1];
        
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        int startX = start[0], startY = start[1];
        visited.add(startX * n + startY);
        // the starting point itself may be selected
        if (grid[startX][startY] >= low && grid[startX][startY] <= high) {
            items.add(new Item(0, grid[startX][startY], startX, startY));
        }
        
        int step = 1;
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        while (!queue.isEmpty() && items.size() < k) {
            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++) {
                int[] curr = queue.poll();
                for (int[] dir: dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && 
                        grid[x][y] != 0 && !visited.contains(x * n + y)) {
                        if (grid[x][y] >=low && grid[x][y] <= high) {
                            items.add(new Item(step, grid[x][y], x, y));
                        }
                        visited.add(x * n + y);
                        queue.add(new int[] {x, y});
                    }
                }
            }
            step++;
        }
        
        Collections.sort(items, new Comparator<>(){
            @Override
            public int compare(Item a, Item b) {
                if (a.dist != b.dist) return Integer.compare(a.dist, b.dist);
                else if (a.price != b.price) return Integer.compare(a.price, b.price);
                else if (a.row != b.row) return Integer.compare(a.row, b.row);
                else return Integer.compare(a.col, b.col);
            }
        });
        
        // printItems(items);
    
        List<List<Integer>> ans = new ArrayList<>();
        // don't forget to check the size of the answer
        int qualified = Math.min(k, items.size());
        for (int i=0; i<qualified; i++) {
            Item item = items.get(i);
            ans.add(Arrays.asList(item.row, item.col));
        }
        return ans;
    }
    
    private void printItems(List<Item> items) {
        for (Item item: items) {
            System.out.println("dist" + item.dist + " price" + item.price + " (x,y)" + item.row + "," + item.col);
        }
    }
}