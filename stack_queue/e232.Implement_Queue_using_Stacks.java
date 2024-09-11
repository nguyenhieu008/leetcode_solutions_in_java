// https://leetcode.com/problems/implement-queue-using-stacks/description/

/* Solution:
- We keep pushing to a specific stack.
- When popping item from my queue, we check if the "popping stack" has items. If not, we pour the "pushing stack" into "popping stack"
- Because the pouring action will reverse order of a stack, so we will change the LIFO to FIFO characteristic.

- Time complexity: 
  - Push: O(1)
  - Pop: Armortized O(1)
- Space complexity: O(n), n is number of push.
*/

class MyQueue {

    private Stack<Integer> pushingStack;
    private Stack<Integer> poppingStack;

    public MyQueue() {
        pushingStack = new Stack<Integer>();
        poppingStack = new Stack<Integer>();    
    }
    
    public void push(int x) {
        pushingStack.push(x);
    }
    
    public int pop() {
        if (poppingStack.isEmpty()) {
            pouring();
        }
        return poppingStack.pop();
    }

    private void pouring() {
        
        while (!pushingStack.isEmpty()) {
            poppingStack.push(pushingStack.pop());
        }
    }
    
    public int peek() {
        if (poppingStack.isEmpty()) {
            pouring();
        }
        return poppingStack.peek();
    }
    
    public boolean empty() {
        return pushingStack.isEmpty() && poppingStack.isEmpty();
    }
}
