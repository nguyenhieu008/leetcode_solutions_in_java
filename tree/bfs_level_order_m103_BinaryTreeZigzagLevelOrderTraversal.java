// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
// Use normal level order traversal. However when adding nodes to queue for next level, we must reverse order
// So add the nodes in a level into a stack, then popping from it and add to queue based on the direction.
// Time complexity: O(n)
// Space complexity: O(n);

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> res = new LinkedList<>();
        boolean fromLeft = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            Stack<TreeNode> levelStack = new Stack<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                levelStack.push(node);
            }

            while (!levelStack.isEmpty()) {
                TreeNode node = levelStack.pop();
                if (fromLeft) {
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                } else {
                    if (node.right != null) queue.offer(node.right);
                    if (node.left != null) queue.offer(node.left);
                }
            }
            fromLeft = !fromLeft;
            res.add(level);
        }
        return res;
    }
}
