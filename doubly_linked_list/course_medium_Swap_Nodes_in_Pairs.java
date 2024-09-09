// https://axonenterprise.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5853358#notes

/*
DLL: Swap Nodes in Pairs ( ** Interview Question)

ATTENTION: Advanced Doubly Linked List Challenge Ahead!

This question is acknowledged as one of the more intricate challenges within the Doubly Linked List section. It's not unusual for students to find this task quite formidable at this point in the course.

If you're considering diving into this problem, it's crucial to approach it methodically. I recommend drawing out the list structures and operations to better visualize the problem. This challenge demands a deep understanding of Doubly Linked Lists' unique characteristics and manipulation techniques.

Use this opportunity to deepen your understanding, but also remember it's absolutely fine to come back to this problem later if it feels overwhelming now. Progress in complex concepts like these sometimes requires stepping back and revisiting with fresh insights. Good luck, and remember that perseverance is key in mastering these advanced topics!

Now, here is the problem:

_________________________________



You are given a doubly linked list.

Implement a method called swapPairs within the class that swaps the values of adjacent nodes in the linked list. The method should not take any input parameters.

Note: This DoublyLinkedList does not have a tail pointer which will make the implementation easier.


Example:

1 <-> 2 <-> 3 <-> 4

should become

2 <-> 1 <-> 4 <-> 3


Your implementation should handle edge cases such as an empty linked list or a linked list with only one node.


Note: You must solve the problem WITHOUT MODIFYING THE VALUES in the list's nodes (i.e., only the nodes' prev and next pointers may be changed.)


*/

/*
- Try to handle 2 nodes at once, draw on paper how to re-wires the nodes properly. 
- Remember we need to also update the previous node and after node (of the current 2 nodes), because the nodes they are pointing to already swapped.
- Edge cases are the prev is null (the head), and the after is null (the end). Need to handle them carefully.
- Time complexity: O(n)
- Space complexity: O(1)
*/
public void swapPairs() {
        if (length < 2) {
            return;
        }
        
        Node cur = head;
        while (cur != null && cur.next != null) {
            Node prev = cur.prev;
            Node toSwap = cur.next;
            Node after = toSwap.next;
            
            cur.next = after;
            cur.prev = toSwap;
            toSwap.next = cur;
            toSwap.prev = prev;
            
            if (prev != null) {
                prev.next = toSwap;
            } else {
                head = toSwap;
            }
            if (after != null) {
                after.prev = cur;
            }
            
            cur = cur.next;
        }
        
    }
