// https://leetcode.com/problems/valid-anagram/description/

class Solution {
    public boolean isAnagram(String s, String t) {
        int[] charFrequency = new int[128];

        for (char c : s.toCharArray()) {
            charFrequency[c]++;
        }

        for (char c : t.toCharArray()) {
            charFrequency[c]--;
        }
        for (int freq : charFrequency) {
            if (freq != 0) {
                return false;
            }
        }
        return true;
    }
}
