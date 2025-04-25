// https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

class Solution {
    private int smallestIdx = 0;

    public int kthSmallest(TreeNode root, int k) {
        return inorder(root, k);
    }

    private int inorder(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        int resLeft = inorder(root.left, k);
        if (resLeft != -1) {
            return resLeft;
        }

        smallestIdx++;
        if (smallestIdx == k) {
            return root.val;
        }

        return inorder(root.right, k);
    }
}
