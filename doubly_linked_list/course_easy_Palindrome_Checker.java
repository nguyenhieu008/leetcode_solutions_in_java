// https://axonenterprise.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5819206#notes

/* DLL: Palindrome Checker ( ** Interview Question)
Write a method to determine whether a given doubly linked list reads the same forwards and backwards.

For example, if the list contains the values [1, 2, 3, 2, 1], then the method should return true, since the list is a palindrome.

If the list contains the values [1, 2, 3, 4, 5], then the method should return false, since the list is not a palindrome.

Method name: isPalindrome 

Return Type: boolean
  */

    public boolean isPalindrome() {
        if (length <= 1) return true;
        
        Node forwardNode = head;
        Node backwardNode = tail;
        for (int i = 0; i < length / 2; i++) {
            if (forwardNode.value != backwardNode.value) return false;
            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }
        return true;
    }
