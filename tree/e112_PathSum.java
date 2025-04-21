// https://leetcode.com/problems/path-sum/description/

// Beware of edge cases:
// - null root must be handled separate ly
// - if dfs to null, but previous node is not a leaf (1 child, 1 null) => we need to handle the leaf node separately (both children are null)

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return hasPathSum(root, targetSum, 0);
    }
    private boolean hasPathSum(TreeNode node, int targetSum, int curSum) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return curSum + node.val == targetSum;
        }
        return hasPathSum(node.left, targetSum, curSum + node.val) || hasPathSum(node.right, targetSum, curSum + node.val);
    }
}
