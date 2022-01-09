class Solution {
    public boolean isRobotBounded(String instructions) {
        // case1: after following the instruction, pos is (0,0) -> circle
        // case2: ending pos is not (0,0)
        //    2a: ending up facing north before start again (never go back)
        //    2b: opposite direction, another round of instructions will form a circle
        //    2c: 90 degree or 270 degree, similar to 2b, will form a circle
        
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}}; // up, right, down, left
        int x = 0, y = 0;
        int facing = 0;  // i.e. facing north
        
        for (char c: instructions.toCharArray()) {
            if (c == 'G') {
                int[] dir = dirs[facing];
                x += dir[0];
                y += dir[1];
            } else if (c == 'R') {
                // up -> right, right -> down, down -> left, left -> up
                facing = (facing + 1) % 4;
            } else {
                facing = (facing - 1) % 4;
                if (facing < 0) facing += 4;
            }
        }
        
        // check ending position
        if (x == 0 && y == 0) return true;
        return facing != 0;
    }
}