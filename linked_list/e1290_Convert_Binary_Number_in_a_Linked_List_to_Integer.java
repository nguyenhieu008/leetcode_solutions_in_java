// https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/description/

/*
- Time complexity: O(n)
- Space complexity: O(1)
  */
class Solution {
    public int getDecimalValue(ListNode head) {
        int num = 0;
        ListNode cur = head;
        while (cur != null) {
            num = num * 2 + cur.val;
            cur = cur.next;
        }
        return num;
    }
}
