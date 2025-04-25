// https://leetcode.com/problems/validate-binary-search-tree/submissions/

// Solution 2: Iterative inorder
class Solution {
    private Integer prev = null;
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        
        pushLeft(stack, root);
        while (!stack.isEmpty()) { // check condition
            TreeNode node = stack.pop();

            if (prev != null && node.val <= prev) return false;
            prev = node.val;

            pushLeft(stack, node.right);
        }
        return true;
    }
    private void pushLeft(Stack<TreeNode> stack, TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}

// Solution 1: DFS + inorder.
class Solution {
    private Integer prev = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean res = isValidBST(root.left);
        
        if (prev != null) {
            res &= (root.val > prev);
        }
        prev = root.val;
        res &= isValidBST(root.right);

        return res;
    }
}
