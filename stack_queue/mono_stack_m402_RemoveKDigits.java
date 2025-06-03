
// Solution 2: Store the increasing character inside the stack, not the index.
// If we use up all k removals, we keep pushing to the stack => it will be the reversed of the result string
// Otherwise, after first for loop, we remove the result until we reach k removals
// After all, trim the last '0's and reverse string and return

// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        Stack<Character> keep = new Stack<>();
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (!keep.isEmpty() && keep.peek() > c && count < k) {
                // Pop and mark this as removed from the final string
                keep.pop();
                count++;
            }
            keep.push(c);
        }

        while (count < k) {
            // k is < n, and because we push all to the stack => guaranteed stack not empty
            keep.pop();
            count++;
        }

        StringBuilder result = new StringBuilder();
        while (!keep.isEmpty()) {
            result.append(keep.pop());
        }
        while (!result.isEmpty() && result.charAt(result.length() - 1) == '0') {
            result.deleteCharAt(result.length() - 1);
        }
        return result.isEmpty() ? "0" : result.reverse().toString();
    }
}

// Solution 1: Store index of the increasing numbers, so it's a bit complex to code
// Time complexity: O(n)
// Space complexity: O(n)
class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        Stack<Integer> increasing = new Stack<>();
        int count = 0;
        boolean[] removed = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (!increasing.isEmpty() && num.charAt(increasing.peek()) > c && count < k) {
                int prev = increasing.pop();
                removed[prev] = true;
                count++;
            }

            if (count == k) {
                break;
            }
            if (increasing.isEmpty() && c == '0') {
                continue;
            }
            increasing.push(i);
        }

        while (count < k) {
            if (increasing.isEmpty()) {
                return "0";
            }
            int idx = increasing.pop();
            removed[idx] = true;
            count++;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (removed[i] || (result.isEmpty() && c == '0')) {
                continue;
            }
            result.append(c);
        }
        return result.isEmpty() ? "0" : result.toString();
    }
}
