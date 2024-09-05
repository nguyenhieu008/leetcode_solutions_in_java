// https://leetcode.com/problems/middle-of-the-linked-list/

/* Solution 1:
Firstly, find the total length of the list. Then divide by 2, it's the number of moves we need to make.
- Time complexity: O(n)
- Space complexity: O(1)
*/
class Solution {
   // 
    public ListNode middleNode(ListNode head) {
      int length = 0;
	   ListNode temp = head;
	    while (temp != null) {
	         temp = temp.next;
	         length++;
	     }
	     temp = head;
	     int numToMoveNext = length / 2;
	     for (int i = 0; i < numToMoveNext; i++) {
	         temp = temp.next;
	     }
	     return temp;
     }

}

/* Solution 2: Slow, fast pointer. Or Floyd's Tortoise and Hare algorithm
// Slow and fast pointers start at head node, fast moves twice as fast as slow pointer.
    // If fast moves 2*n steps to take to the end (next == null) then slow (n steps) is at the middle
    // Otherwise, if fast move 2*n + 1 steps, we need to move slow n+1 steps.
- Time complexity: O(n)
- Space complexity: O(1)
*/

class Solution {

    
    public ListNode middleNode(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast.next == null) {
                return slow;
            }
            fast = fast.next;
        }
        return slow;
    }
    
}
