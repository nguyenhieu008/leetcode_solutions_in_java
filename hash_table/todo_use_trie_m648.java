// https://leetcode.com/problems/replace-words/

// Solution: look up the prefix of each word in the hash set is a better way => for each word, we add characters 1-by-1 to form the prefix and look up in hash set.
// Store the root if found in the splitted array of words then form the result string.

// A BETTER WAY IS TO USING TRIE. TRY IT LATER.

// Assume the sentence has n words, longest word has m character.
// Time complexity: O(n * m^2) (because we loop through word m times to form prefix, each time has complexity of m to form a substring => m ^ 2) 
// => if time is priority, we can form prefix manually to avoid using substring method.
// Space complexity: O(l + d), where d = size of dictionary - we need to store it in hash set as a additional data structure,
//                                    l = size of sentence - we need to store words array.

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        HashSet<String> set = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");

        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            for (int i = 0; i < word.length(); i++) {
                String prefix = word.substring(0, i + 1);
                if (set.contains(prefix)) {
                    words[w] = prefix;
                    break;
                }
            }
        }

        return String.join(" ", words);
    }
}
