// https://leetcode.com/problems/min-stack/description

// Solution 2: Use 1 stack, with a pair of value - min of stack at that value.
// The min values are surely decreasing.

class MinStack {

    // getMin with O(1) => must maintain a structure to store min.
    // Cannot be a single min item, because it cannot tell what it the next min, after we pop it.
    // => Need to store the min items in a decreasing stack. => we have 2 stacks.
    //  - What if pushing an item greater than the minimum:
    //      => Push it to normal stack, and ignore it for the decreasing stack (because it will never be the min item, because the min item will be popped after it)
    //  - What if pushing an item == min (top of stack)
    //      => Push it to both stacks, because we need a way to keep track number of min items, so we can pop them easily
    //  - Ofcourse, if it's smaller, we push to both

    class Pair {
        int val;
        int min;

        Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    Stack<Pair> stack;

    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Pair(val, val));
        } else {
            stack.push(new Pair(val, Math.min(val, stack.peek().min)));
        }
        
    }
    
    // Methods pop, top and getMin operations will always be called on non-empty stacks.
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek().val;
    }
    
    public int getMin() {
        return stack.peek().min;
    }
}

// Solution 1: use two stack, one is the real stack, another is the decreasing stack.

class MinStack {

    // getMin with O(1) => must maintain a structure to store min.
    // Cannot be a single min item, because it cannot tell what it the next min, after we pop it.
    // => Need to store the min items in a decreasing stack. => we have 2 stacks.
    //  - What if pushing an item greater than the minimum:
    //      => Push it to normal stack, and ignore it for the decreasing stack (because it will never be the min item, because the min item will be popped after it)
    //  - What if pushing an item == min (top of stack)
    //      => Push it to both stacks, because we need a way to keep track number of min items, so we can pop them easily
    //  - Ofcourse, if it's smaller, we push to both

    Stack<Integer> master;
    Stack<Integer> decreasing;

    public MinStack() {
        master = new Stack<>();
        decreasing = new Stack<>();
    }
    
    public void push(int val) {
        master.push(val);
        if (decreasing.isEmpty() || val <= decreasing.peek()) {
            decreasing.push(val);
        }
    }
    
    // Methods pop, top and getMin operations will always be called on non-empty stacks.
    public void pop() {
        if (master.pop().equals(decreasing.peek())) {
            decreasing.pop();
        }
    }
    
    public int top() {
        return master.peek();
    }
    
    public int getMin() {
        return decreasing.peek();
    }
}
