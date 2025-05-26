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

// Solution 2: Elegant solution using stack
// Reference: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/solutions/154908/python-easy-solution-using-stack/

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        // stack is guaranteed to NOT contain any null value.
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node dummy = new Node();

        Node prev = dummy; // Prev here just for the first case not throw exception

        while (!stack.isEmpty()) {
            // The curNode will traverse through the stack in the correct order of flattened list. It means:
            //  - If no child => then go to next node;
            //  - If has child => push next node first, to traverse after the child finished
            //                 => then push child node, then traverse child node first.
            //  - Store the prev and curNode, so we can rewire them.
            Node curNode = stack.pop();

            curNode.prev = prev;
            prev.next = curNode;

            if (curNode.next != null) {
                // Always push next node into the stack
                stack.push(curNode.next);
            }
            if (curNode.child != null) {

                stack.push(curNode.child);
                curNode.child = null;
            }

            prev = curNode;
        }

        head.prev = null;
        return head;
    }

    
}
