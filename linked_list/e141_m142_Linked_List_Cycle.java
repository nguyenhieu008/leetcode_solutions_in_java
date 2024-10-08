// https://leetcode.com/problems/linked-list-cycle/description/

/* Solution 1: 
- Mark traversed node by pointing the next pointer to a "Mark" Node that we created.
- When traversing the list:
  - If reach null, no cycle
  - If the next of current node points to "Mark" node, it means we go back to a traversed node.
- Time complexity: O(n)
- Space complexity: O(1)
  */

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode mark = new ListNode(2999);
	    ListNode temp = head;
	    while (temp != null) {
	        if (temp.next == mark) {
	            return true;
	        }
	        ListNode after = temp.next;
	        temp.next = mark;
	        temp = after;
	    }
	    return false;
    }
}

/* Solution 2:
- We use slow fast pointers, if they meet => the fast pointer has gone back in a cycle to reach the slow pointer
- Notice: There is an edge case where there is only 1 item => we must check for condition inside the while loop (after move the pointers)
  So it passes edge case. If we check condition at the last return statement => Maybe the while loop not run for the edge case => wrong
- Time complexity: O(n)
- Time complexity: O(1)
  */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

// https://leetcode.com/problems/linked-list-cycle-ii/description/
/* Follow up for Solution 2: doing some math, assume:
- 1st node of cycle: x steps from head;
- 2 Pointers meet at (x + y) steps => slow = (x + y) steps, fast = (x + x) + (y + y) steps => cycle length = (x + y)
=> If now, slow runs from head x steps => it reaches the 1st node of cycle
	fast runs from meet point x steps => it reaches node (x + y) + x = x + (y + x) = x + cycle length => 1st node of cycle
	*/
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean meet = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                meet = true;
                break;
            }
        }

        if (meet == false) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
  
