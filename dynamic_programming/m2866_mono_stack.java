// https://leetcode.com/problems/beautiful-towers-ii/
// Solution: Same as https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/d5a14cdc6099326c600d787526d6c25f9e12d27c/array_string/m2865_brute_force_mono_stack.java

// here are the hints:
// Try all the possible indices i as the peak.
// Let left[i] be the maximum sum of heights for the prefix 0, …, i when index i is the peak.'
// Let right[i] be the maximum sum of heights for suffix i, …, (n - 1) when i is the peak
// Compute values of left[i] from left to right using DP. 
//    For each i from 0 to n - 1, left[i] = maxHeights * (i - j) + answer[j], where j is the rightmost index to the left of i such that maxHeights[j] < maxHeights[i] .
//    For each i from n - 1 to 0, right[i] = maxHeights * (j - i) + answer[j], where j is the leftmost index to the right of i such that maxHeights[j] < maxHeights[i] .

class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();

        Stack<Integer> incIdx = new Stack<>();
        incIdx.push(-1);
        long curSum = 0;
        long[] left = new long[n];

        for (int i = 0; i < n; i++) {
            while (incIdx.size() > 1 && maxHeights.get(incIdx.peek()) >= maxHeights.get(i)) {
                int truncatedIdx = incIdx.pop();
                curSum -= (long)(truncatedIdx - incIdx.peek()) * maxHeights.get(truncatedIdx);
            }
            curSum += (long)(i - incIdx.peek()) * maxHeights.get(i);
            incIdx.push(i);
            left[i] = curSum;
        }

        long[] right = new long[n];
        incIdx.clear();
        incIdx.push(n);
        curSum = 0;

        for (int i = n - 1; i >= 0; i--) {
            while (incIdx.size() > 1 && maxHeights.get(incIdx.peek()) >= maxHeights.get(i)) {
                int truncatedIdx = incIdx.pop();
                curSum -= (long) (incIdx.peek() - truncatedIdx) * maxHeights.get(truncatedIdx);
            }
            curSum += (long) (incIdx.peek() - i) * maxHeights.get(i);
            right[i] = curSum;
            incIdx.push(i);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, left[i] + right[i] - maxHeights.get(i));
        }
        return res;
    }
}
