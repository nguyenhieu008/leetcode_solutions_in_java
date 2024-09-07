// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

/* Solution 1: 
- Firstly, count the number of nodes, based on that, count the number of moves we need to take us to the node before of the removed node.
- Then we remove the next node of the temp node. 1 edge case could be the removed is the head => we cannot get to the node before it => we handle it separately.
- Time complexity: O(m), m = length of list
- Space complexity: O(1)
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int numNodes = 0;
        ListNode temp = head;
        while (temp != null) {
            numNodes++;
            temp = temp.next;
        }

        if (numNodes == n) {
            head = head.next;
        } else {
            temp = head;
            int numMoves = numNodes - n - 1;
            for (int i = 0; i < numMoves; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        return head;
    }
}

/* Solution 2:
- We want to keep a window of (n + 1) node, by moving a pair of start - end pointers 
=> When end points to the last node (node.next == null), the start points to the node before the removed node.
- So first, we move the end pointers n nodes from head, then we moves the pair of nodes together, 1 node per step, until we reach the last node.
- An exception case is we need to remove the head node.
- Time complexity: O(m), m = length of list
- Space complexity: O(1)
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode end = head;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }
        if (end == null) {
            head = head.next;
            return head;
        }

        ListNode start = head;

        while (end.next != null) {
            end = end.next;
            start = start.next;
        }
        start.next = start.next.next;
        return head;
    }
}

/* Solution 3:
- We maintain an array of n items to store N NODES BEFORE THE LAST NODE. The index of this array is incremented in circular basis.
- When the pointer reaches the last node, the index points to the BEFORE TARGET node
- One edge case is that there is only (n - 1) nodes before the last node => the last item of the array is null because it's not set. => we need to remove the head
  */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] window = new ListNode[n];
        int i = 0;
        ListNode temp = head;
        
        while (temp.next != null) {
            window[i] = temp;
            i = (i + 1 == n)? 0 : i + 1;
            temp = temp.next;
        }
        
        if (window[i] == null) {
            head = head.next;
        } else {
            window[i].next = window[i].next.next;
        }
        return head;
    }
}
