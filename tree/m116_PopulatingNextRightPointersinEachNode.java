// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/

// This is just a special case where this is perfect tree => can handle easier because we can always get next node left child as the next node for cur node right child.
// The general case for this problem is here:
// https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/tree/m117_PopulatingNextRightPointersinEachNodeII.java

class Solution {
    public Node connect(Node root) {
        return connect(root, null);
    }

    private Node connect(Node root, Node nextNode) {
        if (root == null) {
            return null;
        }

        root.next = nextNode;
        connect(root.left, root.right);
        connect(root.right, root.next == null ? null : root.next.left);
        return root;
    }
}
