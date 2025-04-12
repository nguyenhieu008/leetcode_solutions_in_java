// https://leetcode.com/problems/add-two-numbers/description/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode i1 = l1, i2 = l2;
        ListNode res = new ListNode();
        ListNode i = res;
        while (i1 != null || i2 != null || carry != 0) {
            if (i1 != null) {
                carry += i1.val;
                i1 = i1.next;
            }
            if (i2 != null) {
                carry += + i2.val;
                i2 = i2.next;
            }
             
            ListNode newNode = new ListNode(carry % 10);
            carry /= 10;
            i.next = newNode;
            i = i.next;
        }
        return res.next;
    }
}
