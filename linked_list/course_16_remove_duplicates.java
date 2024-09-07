// In course: https://axonenterprise.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5851810#notes

/* LL: Remove Duplicates ( ** Interview Question)
You are given a singly linked list that contains integer values, where some of these values may be duplicated.



Note: this linked list class does NOT have a tail which will make this method easier to implement.



Your task is to implement a method called removeDuplicates() within the LinkedList class that removes all duplicate values from the list.

Your method should not create a new list, but rather modify the existing list in-place, preserving the relative order of the nodes.

You can implement the removeDuplicates() method in two different ways:



Using a Set (HashSet) - This approach will have a time complexity of O(n), where n is the number of nodes in the linked list. You are allowed to use the provided Set data structure in your implementation.

Without using a Set - This approach will have a time complexity of O(n^2), where n is the number of nodes in the linked list. You are not allowed to use any additional data structures for this implementation.



Here is the method signature you need to implement:

public void removeDuplicates() {
    // Your implementation goes here
}
*/

/* Solution 1:
- While traversing the list, we store the nodes values into the hash set to store distinct node values. 
- If we find a node that has been added to the set, we simply remove it (by skipping the next pointer of the previous node), otherwise, we just store that new value to set and move forward.
- In order to skip a duplicate node, we moves 2 pointers together, prev and current.
- Time complexity: O(n), because HashSet lookup is O(1)
- Space complexity: O(n), need additional hash set

- Solution 2: is O(n^2), at each item, we make another for loop to the end to remove duplicates
*/

public void removeDuplicates() {
        HashSet<Integer> set = new HashSet<Integer>();
        Node temp = head;
        Node prev = null;
        
        while (temp != null) {
            if (set.contains(temp.value)) {
                prev.next = temp.next;
                length--;
            } else {
                set.add(temp.value);
                prev = temp;
            }
            temp = temp.next;
        }
    }
