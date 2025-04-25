// https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
// For the follow-up, when there are multiple inserts/delete, we should add size of each node 
// => we can easily know the position of this node in sorted array (if there are k-1 nodes in left subtree)
// => this node is at kth index,

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
