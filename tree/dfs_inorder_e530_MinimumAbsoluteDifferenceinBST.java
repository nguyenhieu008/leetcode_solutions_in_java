// https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/

class Solution {
    private int prev = -1;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        
        int min = getMinimumDifference(root.left);

        if (prev >= 0) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;

        min = Math.min(min, getMinimumDifference(root.right));
        return min;
    }
}
