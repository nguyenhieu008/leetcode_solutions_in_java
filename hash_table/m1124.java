// https://leetcode.com/problems/longest-well-performing-interval/

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
