class Solution {
    int index;
    public TreeNode bstFromPreorder(int[] preorder) {
        index = 0;
        return helper(preorder,  Integer.MIN_VALUE , Integer.MAX_VALUE);
    }
    
    // O(n) time, O(n) space to keep the entire tree
    private TreeNode helper(int[] values, int lowBound, int highBound) {
        if (index == values.length || values[index] < lowBound || values[index] > highBound) {
            return null;
        }
        
        int rootVal = values[index++];
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(values, lowBound, rootVal);
        root.right = helper(values, rootVal, highBound);
        return root;
    }
}

class Solution {
    
    int index;
    Map<Integer, Integer> indexMap;
    // O(nlogn) time and O(n) space
    public TreeNode bstFromPreorder(int[] preorder) {
        index = 0;
        
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        indexMap = new HashMap<>();
        for (int i=0; i<inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        
        return helper(preorder, 0, inorder.length);
    }
    
    private TreeNode helper(int[] values, int start, int end) {
        // rightBound is exclusive, so start == end means no element
        if (start == end) return null;
        
        int rootVal = values[index++];
        TreeNode root = new TreeNode(rootVal);
        int currIndex = indexMap.get(rootVal);
        
        root.left = helper(values, start, currIndex);
        root.right = helper(values, currIndex+1, end);
        return root;
    }
}

class Solution {
    // O(n^2) time
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length-1);
    }
    
    private TreeNode helper(int[] values, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(values[start]);
        
        int rightStart = findBigger(values, start, end);
        TreeNode root = new TreeNode(values[start]);
        if (rightStart != -1) {
            root.left = helper(values, start+1, rightStart - 1);
            root.right = helper(values, rightStart, end);
        } else {
            root.left = helper(values, start+1, end);
        }
        return root;
    }
    
    private int findBigger(int[] values, int start, int end) {
        for (int i=start+1; i<=end; i++) {
            if (values[i] > values[start]) {
                return i;
            }
        }
        return -1; // no right subtree
    }
}