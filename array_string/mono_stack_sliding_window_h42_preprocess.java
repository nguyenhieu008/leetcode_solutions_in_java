// https://leetcode.com/problems/trapping-rain-water/description/

// Solution 1: self-done, montonic stack. We see that, we can trap some water, if we meet some high pile => pop out the less piles 
// => decreasing stack of indexes (for calculate distance).
// What to add to the result? 
// After pop a pile (in fact can be multiple piles of same height), the water trapped is the area: with = (i - top - 1, because inner only) * height = min(a[i], a[top]) - a[pop]
// Then it's all good.
// Time complexity: O(n)
// Space complexity: O(n) for stack
class Solution {
    public int trap(int[] height) {
        // 1 => 1 0 => 
        // => push 2 => pop 0 => sum = 0 => pop 1, sum = 1 => area = 1 * 2 = 2 => res += 2 - 1 = 1
        // => [2, 1, 0] => push 1 => pop 0 => sum = 0 => Complicated.
        
        // stack: store index, of decreasing order of a[i]
        // while (a[i] > a[top]) 
        //      pile = a[pop()]
        //      res += (i - top) * (min(a[i], a[top]) - pile)

        int n = height.length;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int pile = height[stack.pop()];
                if (!stack.isEmpty()) {
                    int prev = stack.peek();
                    res += (i - prev - 1) * (Math.min(height[i], height[prev]) - pile);
                }
            }
            stack.push(i);
        }
        return res;
    }
}

// Solution 2: for each i, calculate how match water can be trapper at the index
// => it is: min(max left pile, max right pile) - height[i]
// We preprocess the max to the left, and max to the right of each item.
// Time complexity: O(n)
// Space complexity: O(n)
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i-1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i+1]);
        }

        int res = 0;
        for (int i = 0; i < n; i++){
            int waterLevel = Math.min(maxLeft[i], maxRight[i]);
            res += Math.max(0, waterLevel - height[i]);
        }
        return res;
    }
}

// Solution 3: 2 pointers. Go from both ends, the trapped water depends on the less pile, because if the inner pile smaller than both => trapped water based on min of 2 maxs
//  => we update the less pile. If we see a less pile, just update the result. If we see a higher pile, just update the corresponding max.
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int maxLeft = height[0], maxRight = height[n-1];

        int res = 0;
        while (l <= r) {
            if (maxLeft <= maxRight) {
                if (height[l] >= maxLeft) {
                    maxLeft = height[l];
                } else {
                    res += maxLeft - height[l];
                }
                l++;
            } else {
                if (height[r] >= maxRight) {
                    maxRight = height[r];
                } else {
                    res += maxRight - height[r];
                }
                r--;
            }
        }
        return res;
    }
}
