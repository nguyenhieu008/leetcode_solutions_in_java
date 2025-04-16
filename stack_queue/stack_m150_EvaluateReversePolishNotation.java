// https://leetcode.com/problems/evaluate-reverse-polish-notation/description/

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String t : tokens) {
            switch (t) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int subtractor = stack.pop();
                    stack.push(stack.pop() - subtractor);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divident = stack.pop();
                    stack.push(stack.pop() / divident);
                    break;
                default:
                    stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }
}
