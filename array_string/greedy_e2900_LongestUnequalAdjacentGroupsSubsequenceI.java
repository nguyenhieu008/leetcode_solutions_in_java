// https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/description/

// Solution 2: DP longest subsequency, but O(n^2) and no need to implement.

// Solution 1: Greedy. Always add a new word to result when we encounter a new item in groups
class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        res.add(words[0]);
        for (int i = 1; i < n; i++) {
            if (groups[i] != groups[i-1]) {
                res.add(words[i]);
            }
        }
        return res;
    }
}

