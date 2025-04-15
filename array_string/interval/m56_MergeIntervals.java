// https://leetcode.com/problems/merge-intervals/description/

// Solution 2: sort by start time and merge by (the end time of previous interval and start time of current interval)
// Time complexity: O(n*log(n)) for sort
// Space complexity: O(logn) or O(n) based on sorting algorithm and whether we can sort in-place.

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                // otherwise, there is overlap, so we merge the current and previous
                // intervals.
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}

// Solution 3: Graph + DFS. https://leetcode.com/problems/merge-intervals/solutions/127480/merge-intervals/
// Solution 1: self-done.
// Put all the intervals into time direction, use start, end arrays.
// Notice that we should not store both start and end to the same array time[]
// to detect the case where start[i] == end[i];
// Loop through the time array and merge intervals.
// Notice that we can do this because the range of time is small enough (<= 1e4)
// Time complexity: O(n + m), n is intervals length, m is max time
// Space complexity: O(m) for two start, end arrays.

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        int MAX_TIME = 10_000;

        // Notice that we should not store both start and end to the same array time[]
        // to detect the case where start[i] == end[i];
        int[] start = new int[MAX_TIME + 1];
        int[] end = new int[MAX_TIME + 1];

        for (int i = 0; i < n; i++) {
            int[] interval = intervals[i];
            start[interval[0]]++;
            end[interval[1]]++;    
        }

        // As 0 <= start[i] <= end[i] <= 1e4

        int cur = 0, startInterval = -1, endInterval = -1;
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < MAX_TIME + 1; i++) {
            if (start[i] > 0) {
                if (cur == 0) {
                    startInterval = i;
                }
                cur += start[i];
            }

            if (end[i] > 0) {
                cur -= end[i];
                if (cur == 0) {
                    endInterval = i;
                    res.add(new int[]{startInterval, endInterval});
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
