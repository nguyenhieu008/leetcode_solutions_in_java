// https://leetcode.com/problems/sum-root-to-leaf-numbers/description/

// Can also be implemented using DFS-interative (stack), BFS or morris-traversal.

class Solution {
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }
    private int sumNumbers(TreeNode node, int curNum) {
        if (node.left == null && node.right == null) {
            // reach leaf node, return sum
            return curNum * 10 + node.val;
        }

        int curSum = 0;
        // Dont recurse to null node
        if (node.left != null) {
            curSum += sumNumbers(node.left, curNum * 10 + node.val);
        }
        if (node.right != null) {
            curSum += sumNumbers(node.right, curNum * 10 + node.val);
        }
        // sum of internal node is sum of both its children
        return curSum;
    }
}
