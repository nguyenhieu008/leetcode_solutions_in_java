// https://leetcode.com/problems/ransom-note/description/
// user int[] as hash table for short.

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] charFrequency = new int[26];
        for (char c : magazine.toCharArray()) {
            charFrequency[c - 'a']++;
        }

        for (char r : ransomNote.toCharArray()) {
            if (charFrequency[r - 'a'] <= 0) {
                return false;
            }
            charFrequency[r - 'a']--;
        }
        return true;
    }
}
