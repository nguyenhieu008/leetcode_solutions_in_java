// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/

// Solution: We recursively flatten each level and return the tail for that level
// We store the important node to rewire after recursive call:
//  - curNode: Current node which has the child list
//  - childNode: The child node will be head of flattened list
//  - childTail: Return the tail node of flattened list
//  - after: The current next node of current node (need to care about the null case)
//  - curTail: the tail of this level which will be returned (because the curNode reach null after done.

// Time complexity: O(n), number of nodes
// Space complexity: O(h), number of levels of lists

class Solution {
    public Node flatten(Node head) {
        flattenRecursion(head);
        return head;
    }

    // return tail of that level
    // all children of this level already flattened.

    public Node flattenRecursion(Node head) {
        Node curNode = head;
        Node curTail = head;
        
        while (curNode != null) {
            if (curNode.child == null) {
                curTail = curNode;
                curNode = curNode.next;
                continue;
            }

            Node after = curNode.next;  // the node stays right after the child list, after it's flattened

            Node childHead = curNode.child;
            Node childTail = flattenRecursion(childHead);

            // rewire
            curNode.next = childHead;
            childHead.prev = curNode;
            curNode.child = null;

            childTail.next = after;
            if (after != null) {
                after.prev = childTail;
            }

            // Skip the flattened child list as it's already flattened.
            curTail = childTail;
            curNode = after;
        }

        // curNode now points to null of this level, so we return the curTail.
        return curTail;
    }
}
