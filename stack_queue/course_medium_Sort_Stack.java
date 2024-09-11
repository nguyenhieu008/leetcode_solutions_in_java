// https://axonenterprise.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5822520#notes

/* 
The sortStack method takes a single argument, a Stack object.  
  The method should sort the elements in the stack in ascending order (the lowest value will be at the top of the stack) using only one additional stack.  
  The function should use the pop, push, peek, and isEmpty methods of the Stack object.
  */

/* Solution:
- We only be able to use 1 additional stack, so in order to put the items back to the original stack, we must put items into sorted stack in reverse order,
it means in descending order (large one at top, small one at bottom).
- When pop 1 item from original stack, we do some thing:
  - Compare it to the top of the sortedStack, if it is smaller than the top, we pop that top item and push to the original stack 
  (as a temporary storage for our moving operations)
  - Continue to do that until the sorted stack is empty, or the item is greater than the top. Remember to count the number of moving, so we can move them back
  to sorted stack.
  - Push the item to the sortedStack, then move back all (count items) from original stack to sortedStack.
- After all, we push all items from sortedStack to original stack to get final result.

- Notice that: if we pour items from stack A -> B, then pour back from B -> A. The order of items in A is the same. That why when we move items from sortedStack
to original and move back, the order keeps the same -> We keep the sorted order.

- Time complexity: O(n^2)
- Space complexity: O(n);

*/

    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> sortedStack = new Stack<Integer>();
        
        while (!stack.isEmpty()) {
            Integer temp = stack.pop();
            
            int count = 0;
            while (!sortedStack.isEmpty() && sortedStack.peek() > temp) {
                count++;
                stack.push(sortedStack.pop());
            }
            sortedStack.push(temp);
            
            for (int i = 0; i < count; i++) {
                sortedStack.push(stack.pop());
            }
        }
        while (!sortedStack.isEmpty()) {
            stack.push(sortedStack.pop());
        }
    }
