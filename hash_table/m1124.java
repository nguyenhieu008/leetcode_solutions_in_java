// https://leetcode.com/problems/longest-well-performing-interval/

// We rate each day as +1, -1 score, then we calculate score of the whole subarray from beginning to i position.
// If score > 0 => the whole subarray is WPI and we update the max WPI to (i + 1).
// If not, then we store the first occurence of that score into a hash table. Because we go first with 0, so the first occurent of -1 will always smaller than -2, and so on.
// Then for each pos i, with score < 0, we calculate the maximum possible longest WPI to i, by finding the first occurent of (score - 1) in the hash table. (score - 2) and others still yield a WPI, but not longest possible as above idea.

class Solution {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int score = 0;
        
        int max = 0;
        HashMap<Integer, Integer> firstOccurence = new HashMap<>();

        for (int i = 0; i < n; i++) {
            score += (hours[i] > 8) ? 1 : -1;

            if (score > 0) {
                max = i + 1;
            } else {
                firstOccurence.putIfAbsent(score, i);

                if (firstOccurence.containsKey(score - 1)) {
                    max = Math.max(max, i - firstOccurence.get(score - 1));
                }
            }
        }

        return max;
    }
}
