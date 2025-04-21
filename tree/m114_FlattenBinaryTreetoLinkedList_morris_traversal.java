// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/

// Solution 2: morris traversal. At each node, we need to find the right-most node of the left sub-true
// (easily by go to left child, then go all the way to the last right child of them)
// In original morris traveral, we link the right-most to cur node. But for this problem, we link the right-most to the right child, set the left child to null.
// Then we only need to traverse the right branch.
// Time complexity: O(n), we visit each node at max 2 times.
// Space complexity: O(1)
class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            TreeNode temp = cur.left;
            if (temp != null) {
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}

// Solution 3: When traverse the tree with the postorder + right child first (order right-left-cur), it's the exact reverse order of the linked list we want to build.
// So, we just need to go to the end, then backtrack to build the reverse linked list. We should store the previous node to link to.
// Then we just need to link the curNode.right to the previous node we just saved.
// Time complexity: O(n), just basic postorder traversal
// Space complexity: O(n) for stack call.
class Solution {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        postorder(root);
    }
    private void postorder(TreeNode node) {
        if (node == null) {
            return ;
        }

        postorder(node.right);
        postorder(node.left);

        node.right = prev;
        node.left = null;
        prev = node;
    }
}

// Solution 1: self-done. for each node, we need to know 
//   - the last node of left subtree(right-most) => link to right subtree
//   - and the right-most of right subtree, (to return as the right-most of this tree, rooted at cur node)
// Of couse we can create a new tree to return, but it's O(n) space complexity:
// Time complexity: O(n)
// Space complexity: O(n) for the stack call
class Solution {
    public void flatten(TreeNode root) {
        preorder(root);
    }

    private TreeNode preorder(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode lastLeft = preorder(root.left);
        TreeNode lastRight = preorder(root.right);

        if (lastLeft == null) {
            return lastRight;
        }

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        lastLeft.right = temp;

        return temp == null ? lastLeft : lastRight;
    }
}
