// https://leetcode.com/problems/invert-binary-tree/description/

// Solution 2. A bit cleaner.
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftChild = root.left, rightChild = root.right;
        root.right = invertTree(leftChild);
        root.left = invertTree(rightChild);

        return root;
    }
}

// Solution 1: DFS
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }
}
