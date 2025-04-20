// https://leetcode.com/problems/symmetric-tree/description/

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) {
            return true;
        }

        if (leftRoot != null && rightRoot != null && leftRoot.val == rightRoot.val) {
            return isSymmetric(leftRoot.left, rightRoot.right) && isSymmetric(leftRoot.right, rightRoot.left);
        }

        return false;
    }
}
