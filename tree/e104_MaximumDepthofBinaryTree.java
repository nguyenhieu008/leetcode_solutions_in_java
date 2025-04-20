// https://leetcode.com/problems/maximum-depth-of-binary-tree/

class Solution {
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }
    private int maxDepth(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        return Math.max(maxDepth(node.left, depth + 1), maxDepth(node.right, depth + 1));
    }
}
