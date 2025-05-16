// https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii/description/

// Solution: consider all possible previous positions and update the dp correspondingly.
// Time complexity: O(n^2 * L), where n is length of words, L is max length of a words, limited at 10
// Space complexity: O(n);

class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        // dp[i] = length of longest subsequence that ends at i
        // dp[i] = max(dp[j] for j from 0 -> i-1: 
        //              groups[i] != groups[j]
        //              && words[j].length == words[i].length 
        //              && hammingDistance(words[j], words[i]) == 1) + 1
        // prev[i] = index of previous string in the longest subsequence
        int[] dp = new int[n];
        int[] prev = new int[n];

        // Base cases;
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int longest = 1;
        int endIdx = 0;

        for (int cur = 1; cur < n; cur++) {
            for (int before = 0; before < cur; before++) {
                if (groups[cur] != groups[before] && dp[before] >= dp[cur] && hammingDistance(words[before], words[cur]) == 1) {
                    dp[cur] = dp[before] + 1;
                    prev[cur] = before;
                }
            }

            if (dp[cur] > longest) {
                longest = dp[cur];
                endIdx = cur;
            }
        }

        List<String> res = new ArrayList<>();
        while (endIdx != -1) {
            res.add(words[endIdx]);
            endIdx = prev[endIdx];
        }
        Collections.reverse(res);
        return res;
    }

    private int hammingDistance(String a, String b) {
        if (a.length() != b.length()) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                res++;
            }
        }
        return res;
    }
}
