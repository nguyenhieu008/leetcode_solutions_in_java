// https://leetcode.com/problems/plates-between-candles/description/

// Solution 1: preprocessing left right index.
// Detail in comment.
// Time complexity: O(n);
// Space complexity: O(n)
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        /*
            For each query (start, end), we need to quickly find:
                - The first candle (|) to the right of start
                - The first candle (|) to left of end
                - Calculate how many plates (*) between them

            => preprcess:
                - array leftCandle, leftCandle[i]: the nearest candle to the left of i, including i
                - array rightCandle, rightCandle[i]: the nearest candle to the right of i, including i
                - array platePrefixSum, platePrefixSum[i]: how many plates from beginning up to i, including i
            plates = platePrefixSum[i] - platePrefixSum[j] = number of plates within (j, i]
        */

        int n = s.length();
        int[] leftCandle = new int[n], rightCandle = new int[n];
        int[] platePrefixSum = new int[n];

        int curSum = 0;
        int prevCandle = -1; 

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                // plate
                curSum++;
            } else {
                // candle
                prevCandle = i;
            }
            leftCandle[i] = prevCandle;
            platePrefixSum[i] = curSum;
        }

        int nextCandle = n;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '|') {
                nextCandle = i;
            }
            rightCandle[i] = nextCandle;
        }

        int m = queries.length;
        int[] res = new int[m];
        for (int q = 0; q < m; q++) {
            int start = queries[q][0], end = queries[q][1];

            int left = rightCandle[start];
            int right = leftCandle[end];

            if (left >= right) {
                res[q] = 0;
            } else {
                res[q] = platePrefixSum[right] - platePrefixSum[left];
            }
        }
        return res;
    }
}

// Solution 2: Binary search. 
// Notice that there is another way to store the indexes of candles in an array then binary search within this array.
// Time complexity: O(n + qlogn)
// Space complexity: O(n)
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int m = queries.length;

        int[] candleCount = new int[n]; // How many plates up to current index
        int curCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') {
                curCount++;
            }
            candleCount[i] = curCount;
        }

        int[] res = new int[m];
        for (int q = 0; q < m; q++) {
            int start = queries[q][0], end = queries[q][1];
            // If this is candle => just find the first index of cur count => cur index
            // Otherwise, find the first index of a larger count => somewhere after
            int countStart = (s.charAt(start) == '|') ? candleCount[start] : candleCount[start] + 1;
            
            // first index where count == count at end
            int countEnd = candleCount[end];

            int leftCandle = firstIndex(candleCount, start, end, countStart); 
            int rightCandle = firstIndex(candleCount, start, end, countEnd); 

            int numCandleBetween = candleCount[rightCandle] - candleCount[leftCandle] - 1;
            // (rightCandle - leftCandle - 1) = number of slots between 2 indexes
            res[q] = Math.max(0, (rightCandle - leftCandle - 1) - numCandleBetween);
        }
        return res;
    }

    // Find the first index of target in [start, end] (inclusive)
    public int firstIndex(int[] count, int start, int end, int target) {
        int left = start, right = end;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (count[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
