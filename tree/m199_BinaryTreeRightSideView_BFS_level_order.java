// https://leetcode.com/problems/binary-tree-right-side-view/description/

// Solution 2: Level-order traversal, use for inside while
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>(); // check the initialization
        queue.offer(root);

        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (i == 0) {
                    res.add(node.val);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return res;
    }
}

// Solution 3: DFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return ;
        }

        if (res.size() <= level) {
            res.add(root.val);
        }

        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }
}

// Solution 1: Use null to separate levels
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>(); // check the initialization
        boolean isFirst = true;
        queue.offer(root);
        queue.offer(null);

        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                isFirst = true;
                if (!queue.isEmpty()) {
                    queue.offer(null);
                }
                continue;
            }

            if (isFirst) {
                res.add(node.val);
            }
            isFirst = false;
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
        }
        return res;
    }
}
