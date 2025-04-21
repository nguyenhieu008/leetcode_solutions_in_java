// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

// Solution 2: reference:
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/3231708/236-solution-with-step-by-step-explanation/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // search for p and q in two sub-trees
        // left and right will either be null, p and q (or lca of (p,q))
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            // If both != null => p and q are in 2 sub-trees => this i lca
            return root;
        }

        // 3 cases:
        //  - both null => p, q not in this sub-tree => return null (either p, q is fine)
        //  - left = null => return right, it hold the one node or lca
        //  - Otherwise, return left, 
        return left == null ? right : left;
    }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // search for p and q in two sub-trees
        // left and right will either be null, p and q (or lca of (p,q))
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            // If both != null => p and q are in 2 sub-trees => this i lca
            return root;
        }

        // 3 cases:
        //  - both null => p, q not in this sub-tree => return null (either p, q is fine)
        //  - left = null => return right, it hold the one node or lca
        //  - Otherwise, return left, 
        return left == null ? right : left;
    }

}

// Solution 1: self-done. Build the stack of path from root to P and Q.
// equalize two stack size.
// The pop them until found the LCA.
// Time complexity: O(n) just 2 times travel the tree to build stack and 1 stack popping.
// Space complexity: (n) for stack.

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();

        find(root, p, stackP);
        find(root, q, stackQ);

        while (stackP.size() > stackQ.size()) {
            stackP.pop();
        }
        while (stackQ.size() > stackP.size()) {
            stackQ.pop();
        }

        while (stackP.peek() != stackQ.peek()) {
            stackP.pop();
            stackQ.pop();
        }
        return stackP.peek();
    }

    private boolean find(TreeNode root, TreeNode target, Stack<TreeNode> stack) {
        if (root == null) {
            return false;
        }
        stack.push(root);
        if (root == target) {
            return true;
        }
        boolean found = find(root.left, target, stack) || find(root.right, target, stack);
        if (!found) {
            stack.pop();
        }
        return found;
    }
}
