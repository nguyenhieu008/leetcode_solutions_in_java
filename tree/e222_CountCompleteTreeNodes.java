// https://leetcode.com/problems/count-complete-tree-nodes/description/

// Solution 3: at each node, check if it's a full tree by comparing left height and right height (O(logn)). 
// If yes => it's a full tree then the numNodes of that tree = 2^height - 1
// Otherwise, we go to cound both left and right subtree, 1 of them will be full tree => we just need to do most of task on the other half (O(logn))
// Detailed explanation can be found here: 
// https://leetcode.com/problems/count-complete-tree-nodes/solutions/2815375/python-c-java-rust-logn-logn-with-proof-bonus-complete-list-of-solutions-explained/
// Time complexity: O(logn * logn)
// Space complexity: O(logn for stack)
class Solution {
    public int countNodes(TreeNode root) {

        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth)
            return (1 << leftDepth) - 1;
        else
            return 1+countNodes(root.left) + countNodes(root.right);

    }

    private int rightDepth(TreeNode root) {
        // TODO Auto-generated method stub
        int dep = 0;
        while (root != null) {
            root = root.right;
            dep++;
        }
        return dep;
    }

    private int leftDepth(TreeNode root) {
        // TODO Auto-generated method stub
        int dep = 0;
        while (root != null) {
            root = root.left;
            dep++;
        }
        return dep;
    }
}

// Solution 2: for a current node, if the rightmost node of left subtree has height = tree height 
// => all leaf nodes of right subtree exist => we add suitable number of nodes to count (based on height of curNode)
// Time complexity: O(logn * logn) = O(h), h = height of tree = log(n)
// Space complexity: O(1)

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int height = 0;
        TreeNode node = root;
        while (node != null) {
            height++;
            node = node.left;
        }
        int countNode = (int)Math.pow(2, height - 1) - 1; // internal nodes
        countNode += binarySearch(root, height);
        return countNode;
    }

    // Try to count leaf nodes
    private int binarySearch(TreeNode root, int maxHeight) {
        int curHeight = 1;
        TreeNode curNode = root;

        int countLeafNodes = 0;
        while (curNode != null) {
            TreeNode rightMost = curNode.left;

            int tempHeight = curHeight;

            if (tempHeight == maxHeight) {
                return countLeafNodes + (curNode == null ? 0 : 1);
            }

            while (rightMost != null) {
                rightMost = rightMost.right;
                tempHeight++;
            }
            if (tempHeight == maxHeight) {
                curNode = curNode.right;
                countLeafNodes += (int) Math.pow(2, maxHeight - curHeight - 1);
            } else {
                curNode = curNode.left;
            }
            curHeight++;
        }
        return countLeafNodes;
    }
}

// Solution 1: dfs
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
