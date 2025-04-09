// https://leetcode.com/problems/longest-consecutive-sequence/

// Solution 2: I preprocess by adding all items to a set
// The for all items in nums, I check it in the set, and search to 2 directions to get the maximum contiguous sequence if possible. Then remove items on the path, and update the result.
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int v : nums) {
            set.add(v);
        }

        int res = 0;
        for (int v : nums) {
            int length = 0;

            int decreasing = v - 1;
            while (set.remove(decreasing)) {
                length++;
                decreasing--;
            }
            
            int increasing = v;
            while (set.remove(increasing)) {
                length++;
                increasing++;
            }
            res = Math.max(res, length);
        }
        return res;
    }
}

// Solution 1a: Same idea as my solution 1, but handle better then no bug.
// The key observation here is that if we encounter a number that already used, we do not handle them again 
// => no need to store the direction (positive/negative) along with the length of the contiguous sequence, because we never run into a case where the number is inside of a contiguos sequence.
// It's only adjacent to the sequence => only store the length of the sequence is enough

class Solution {
    public int longestConsecutive(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);
                
                // keep track of the max length 
                res = Math.max(res, sum);
                
                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
            else {
                // KEY POINT
                // duplicates
                continue;
            }
        }
        return res;
    }
}

// Solution 1: WRONG ANSWER!!!
// when iterate a number, we update its 2-side length at endpoints only. However, because I store the direction as well, so it's hard to do and have BUGS.
class Solution {
    public int longestConsecutive(int[] nums) {
        // unsorted, can negative
        // output no need order.
        // length 1e5 => O(n^2) not work

        // How about sort? It's okay but need to be in O(n)
        // We can store them in hash table, but what next? we also need to update existing items in the table 
        // what if we only update to ends of a segment?
        // => possible

        // hash table:
        //      key: a number v
        //      value: > 0 => number of contiguous items starts at v
        //             < 0 => number of contiguous items ends at v
        // 

        HashMap<Integer, Integer> consecutiveIndex = new HashMap<>();
        int res = 0;
        for (int v : nums) {
            int prevLength = 0;
            int nextLength = 0;
            if (consecutiveIndex.containsKey(v - 1)) {
                prevLength = Math.max(1, consecutiveIndex.get(v - 1));
            }
            if (consecutiveIndex.containsKey(v + 1)) {
                nextLength = Math.max(1, -consecutiveIndex.get(v + 1));
            }

            int totalLength = 1 + prevLength + nextLength;
            consecutiveIndex.put(v - prevLength, Math.min(consecutiveIndex.getOrDefault(v - prevLength, 0), -totalLength));
            consecutiveIndex.put(v + nextLength, Math.max(consecutiveIndex.getOrDefault(v + nextLength, 0), totalLength));

            // System.out.println(consecutiveIndex);
            res = Math.max(res, totalLength);
        }
        return res;
    }
}
