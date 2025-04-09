// https://leetcode.com/problems/word-pattern/

class Solution {
    public boolean wordPattern(String pattern, String s) {
        // input wellformed, not need to clarify more
        String[] words = s.split(" ");
        int n = pattern.length(), m = words.length;
        if (n != m) {
            return false;
        }

        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // if char c is mapped to another string, or vice verse => it conflicts with the condition => false
            if ((charToWord.containsKey(c) && !charToWord.get(c).equals(word))
                || (wordToChar.containsKey(word) && wordToChar.get(word) != c)) {
                   return false; 
                }

            charToWord.put(c, word);
            wordToChar.put(word, c);
        }
        return true;
    }
}
