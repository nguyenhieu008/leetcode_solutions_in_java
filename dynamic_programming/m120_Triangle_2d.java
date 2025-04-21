// https://leetcode.com/problems/triangle/description/

// Solution 2: Build the path from bottom to top. Detail in comment. We just need to memoise using 1 array.
// Time complexity: O(n * m), height and with 
// Space complexity: O(m), width

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> lastLine = triangle.getLast();
        int[] minPath = new int[lastLine.size()];
        // Initially, the min path is the last line
        for (int i = 0; i < lastLine.size(); i++) {
            minPath[i] = lastLine.get(i);
        }

        // Build the min path bottom up
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // min path from here to bottom is (min of j and j+1 at next line), plus this item value.
                // We use only one array to memoise, because we modify the item minPath[j], which will not affect next iteration in loop
                minPath[j] = Math.min(minPath[j], minPath[j+1]) + triangle.get(i).get(j);
            }
        }
        return minPath[0];
    }
}

// Solution 1: Build the dp table from top to bottom. 
// dp table[i] is: the shortest path from top to item i.
// Then finally, we will have a dp table of m items (width of triangle), we need to loop through it find the minimum one.
// Time complexity: O(n * m)
// Space complexity: O(m);

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] curSum = new int[]{0};
        for (List<Integer> curList : triangle) {
            int n = curList.size();
            int[] prevSum = curSum;
            curSum = new int[n];

            for (int i = 0; i < n; i++) {
                int minSum = Integer.MAX_VALUE;
                if (i > 0) {
                    minSum = Math.min(minSum, prevSum[i - 1] + curList.get(i));
                }
                if (i < prevSum.length) {
                    minSum = Math.min(minSum, prevSum[i] + curList.get(i));
                }
                curSum[i] = minSum;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int sum : curSum) {
            res = Math.min(res, sum);
        }
        return res;
    }
}
