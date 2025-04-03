// https://leetcode.com/problems/beautiful-towers-i/description/

// Solution 1: Brute-force, we try every item as peak. As going from peak to the 2-ends, the heights must go down.
// => going far from peak, the height of current item is the min of this and the previous one (nearer to peak). 
// It means the current item cannot greater than the items nearer to peak.
// => Sum and update result at each try.
//
// We can not easily detect the peak (e.g. the largest item), let's try an example
//   [ 80 90 100 1 101 ]
//     => if peak at 100 => total sum = 80 + 90 + 100 + 1 + 1 = 272
//     => if peak at 101 => total sum = 1 + 1 + 1 + 1 + 101 = 105
//   => We should not assume the peak is the largest one, rather we need to try every peak to find the maximum.
// Since the constraints is possible, we can use brute force solution:
//     1 <= n == heights.length <= 103
//     1 <= heights[i] <= 109
//
// Time complexity: O(n^2)
// Space complexity: O(1);
class Solution {
    public long maximumSumOfHeights(int[] heights) {
        int n = heights.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            long curSum = heights[i];
            long h = heights[i];
            for (int l = i - 1; l >= 0; l--) {
                h = Math.min(heights[l], h);
                curSum += h;
            }
            h = heights[i];
            for (int r = i + 1; r < n; r++) {
                h = Math.min(heights[r], h);
                curSum += h;
            }
            res = Math.max(res, curSum);
        }
        return res;
    }
}

// Solution 2: MONOTONIC STACK, NOT RECOMMENDED IN INTERVIEW AS IT'S COMPLICATED TO IMPLEMENT.
// Same idea as solution 1, but increase of looping to find the sum at each try, 
// we preprocess and save the left[] and right[] as the maximum sum of left and right (including i) if i the the peak item. Do that by using mono stack.
// Time complexity: O(n)
// Space complexity: O(n)
class Solution {
    public long maximumSumOfHeights(int[] heights) {
        int n = heights.length;

        long[] left = new long[n];
        Stack<Integer> stack = new Stack<>(); // increasing, store the index of previous local peaks
        stack.push(-1);
        long curSum = 0;
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                int truncatedIdx = stack.pop();
                curSum -= (long)(truncatedIdx - stack.peek()) * heights[truncatedIdx];
            }
            curSum += (long)(i - stack.peek()) * heights[i];
            stack.push(i);
            left[i] = curSum;
        }

        long[] right = new long[n];
        stack.clear();
        stack.push(n);
        curSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                int truncatedIdx = stack.pop();
                curSum -= (long)(stack.peek() - truncatedIdx) * heights[truncatedIdx];
            }
            curSum += (long)(stack.peek() - i) * heights[i];
            stack.push(i);
            right[i] = curSum;
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, left[i] + right[i] - heights[i]);
        }

        return res;
    }
}
