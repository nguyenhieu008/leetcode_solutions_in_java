// https://leetcode.com/problems/longest-substring-without-repeating-characters/

// Time complexity: O(n)
// Space complexity: O(128) = O(1) (must less than ASCII table size)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // length = 5 * 10^4 => O(n^2) could work.
        // What if there are 2 substring with same length? => output int => need to read the output carefully
        // Lets try build every possible substring (i - j), if j is a duplicated character
        // => try next i
        // Yes, it works, but beats 9.93% => not optimal

        // int n = s.length();
        // Set<Character> set = new HashSet<>();

        // int res = 0;
        // for (int i = 0; i < n; i++) {
        //     set.clear();
        //     for (int j = i; j < n; j++) {
        //         char c = s.charAt(j);
        //         if (set.contains(c)) {
        //             break;
        //         }
        //         set.add(c);
        //         res = Math.max(res, j - i + 1);
        //     }
        // }
        // return res;

        // How can optimize:
        // As with j, the character is duplicate
        //  => Observation 1: substring with (i, >=j) will also contains duplicate => we already break => it's good
        //  => Observation 2: substring with (<=i, j) will also contains duplicate
        // With observation 2 => when (i, j) already contains duplicate, we no need to expand the window to the right and left anymore
        // Rather we need to shrink the window (by ++i, because j is increasing) to remove duplicate, so we can continue to expand.
        // => sliding window
        
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int l = 0, res = 0;
        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);
            while (set.contains(c)) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(c);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
