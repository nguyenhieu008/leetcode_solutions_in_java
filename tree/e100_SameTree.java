// https://leetcode.com/problems/same-tree/description/

// Solution 1: DFS
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}

// Solution 2: BFS
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(p);
        queue2.offer(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll(), node2 = queue2.poll();

            if (node1 == null && node2 == null) {
                // both children are nulls => same structure
                continue;
            }
            if (node1 != null && node2 != null && node1.val == node2.val) {
                // there are some vals and they are the same => same structure.
                // => offer next children, even null value
                queue1.offer(node1.left);
                queue1.offer(node1.right);
                queue2.offer(node2.left);
                queue2.offer(node2.right);
                continue;
            }
            // Other cases
            return false;
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
