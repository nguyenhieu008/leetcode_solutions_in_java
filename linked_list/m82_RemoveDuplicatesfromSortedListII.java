// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/

// Solution: start denotes the start of a duplicate segment
//           end denotes the start of next segment (bypass the duplicates)
// => if start.next != end => there are duplicates
// => prev is the one before start. use prev to rewire the list, and bypass the whole segment [start, end)
// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE, head);
        ListNode prev = dummyHead;
        ListNode start = prev.next, end = prev.next;
        
        while (end != null) {
            while (end != null && end.val == start.val) {
                end = end.next;
            }
            if (start.next == end) {
                prev = start;
                start = end = prev.next;
            } else {
                prev.next = end;
                start = end;
            }
        }

        return dummyHead.next;
    }
}
