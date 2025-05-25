// https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/

// Solution 1: Use hash map to store the existence of strings
// Time complexity: O(n * l), where l is average length of word, l == 2 => O(n)
// Space complexity: O(26 * 26), approximate O(1)
class Solution {
    public int longestPalindrome(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {
            String reversed = reverse2(word);
            int reversedCount = map.getOrDefault(reversed, 0);
            if (reversedCount >= 1) {
                // Decreased the count of reversed word, since it will be used to form palin
                // Increase the final length, and decrease the count of reversed
                res += 4;
                map.put(reversed, reversedCount - 1);
            } else {
                // Cannot find the word to form palin, just put this into map
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        // Need to find the word of 2 same chars, e.g. "aa", "bb", ... to add into the middle
        // There will not be case where there are more than 1 "aa" left, because the reversed must exist in the map and already decreased.
        // If no similar string => just return the result from before calculation

        for (int i = 0; i < 26; i++) {
            char c = (char)('a' + i);
            String similar = "" + c + c;
            if (map.getOrDefault(similar, 0) > 0) {
                res += 2;
                return res;
            }
        }

        return res;
    }

    // Reverse string of length 2 only
    private String reverse2(String w) {
        return "" + w.charAt(1) + w.charAt(0);
    }
}   

// Solution 2: Use array of int as hastable.
// Time complexity: O(n * L), where L = 2 => O(n)
// Space complexity: O(26 * 26) = O(1)
class Solution {
    public int longestPalindrome(String[] words) {
        int size = 26;
        int[][] wordCount = new int[size][size];

        int res = 0;
        int middle = 0; // how many similar words that can be use as middle of string
        for (String word : words) {
            int firstIdx = word.charAt(0) - 'a';
            int secondIdx = word.charAt(1) - 'a';

            if (wordCount[secondIdx][firstIdx] > 0) {
                res += 4;
                wordCount[secondIdx][firstIdx]--;
                if (firstIdx == secondIdx) {
                    middle--;
                }
            } else {
                wordCount[firstIdx][secondIdx]++;
                if (firstIdx == secondIdx) {
                    middle++;
                }
            }
        }
        if (middle > 0) {
            return res + 2;
        }
        return res;
    }
}
