// Solution 2: Instead of mapping from/to 2 strings, we map both of them to same values, it is:
//   - If both characters point to same value => okay then continue
//   - Can map to any integer, (i + 1) guarantee the uniqueness. (i + 1) is important, because i can == 0 => it will fail on the very first character.
//   - For the first occurence of characters, they all map to 0 => can handle first case gracefully
// Time complexity: O(n)
// Space complexity: O(512) = O(1).

class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mapCharInS = new int[256];
        int[] mapCharInT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            if (mapCharInS[s.charAt(i)] != mapCharInT[t.charAt(i)]) {
                return false;
            }
            mapCharInS[s.charAt(i)] = mapCharInT[t.charAt(i)] = i + 1;
        }
        return true;
    }
}


// Solution 1: Use 2 maps from s -> t and t -> s, to eliminate the case where 2 chars map to the same char in t
class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> mapS2T = new HashMap<>();
        HashMap<Character, Character> mapT2S = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char cChar = s.charAt(i);
            char tChar = t.charAt(i);
            if ((mapS2T.containsKey(cChar) && mapS2T.get(cChar) != tChar) || (mapT2S.containsKey(tChar) && mapT2S.get(tChar) != cChar)) {
                return false;
            }
            mapS2T.put(cChar, tChar);
            mapT2S.put(tChar, cChar);
        }
        return true;
    }
}
