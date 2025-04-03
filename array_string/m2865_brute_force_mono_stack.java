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
        long ans = 0;
        Deque<long[]> stack = new LinkedList<>();

        long[] left = new long[n];
        long sum = heights[0];
        stack.addLast(new long[]{heights[0], 1});
        left[0] = sum;
        for (int i = 1; i < n; i++) {
            int cnt = 1;
            while (!stack.isEmpty() && heights[i] <= stack.peekLast()[0]) {
                long height = stack.peekLast()[0];
                long freq = stack.peekLast()[1];
                cnt += freq;
                sum -= height * freq;
                stack.removeLast();
            }

            stack.addLast(new long[]{heights[i], cnt});
            sum += stack.peekLast()[0] * cnt;
            left[i] = sum;
        }

        stack = new LinkedList<>();
        long[] right = new long[n];
        sum = heights[n - 1];
        stack.addLast(new long[]{heights[n - 1], 1});
        right[n - 1] = sum;
        for (int i = n - 2; i >= 0; i--) {
            int cnt = 1;
            while (!stack.isEmpty() && heights[i] <= stack.peekLast()[0]) {
                long height = stack.peekLast()[0];
                long freq = stack.peekLast()[1];
                cnt += freq;
                sum -= height * freq;
                stack.removeLast();
            }

            stack.addLast(new long[]{heights[i], cnt});
            sum += stack.peekLast()[0] * cnt;
            right[i] = sum;
        }

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, left[i] + right[i] - heights[i]);
        }

        return ans;
    }
}
