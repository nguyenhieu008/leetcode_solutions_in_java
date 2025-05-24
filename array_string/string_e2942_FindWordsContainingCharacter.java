// https://leetcode.com/problems/find-words-containing-character/description/

class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (words[i].indexOf(x) >= 0) {
                res.add(i);
            }
        }
        return res;
    }
}
