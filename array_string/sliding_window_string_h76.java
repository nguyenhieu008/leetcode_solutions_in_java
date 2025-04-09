// https://leetcode.com/problems/minimum-window-substring/description/

// Solution 2: Same as solution 1, but implement a bit more subtle.
// User char array => shorter access code
// Store result as length and index => better than handle result string
// store frequency as array of integers => shorter access code. But notice that: 
//    - for characters not in t (and in t as well) the frequency when growing r can gets negative, but those not contribute to count because "if (frequency[c] >= 0)"
//    - when put back in (increase frequency), the frequency of characters not in t will not exceed 0 => it will not contribute to count because "if (frequency[removing] > 0)"
//    => we are not impacted by characters not in t

// Time complexity: O(m + n) = O(max(m, n))
// Space complexity: O(m)

class Solution {
    public String minWindow(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int n = sChars.length, m = tChars.length;
        int[] frequency = new int[128];
        for (char c : tChars) {
            frequency[c]++;
        }

        int minLength = Integer.MAX_VALUE, startIdx = -1;   // Store the min window length and the start idx of it
        int count = 0;  // How many chars in t have been included in the window
        int l = 0;
        for (int r = 0; r < n; r++) {
            char c = sChars[r];
            // Notice that now, the frequency map can contains negative values
            frequency[c]--;
            if (frequency[c] >= 0) {
                // Only couting the characters if not exceeds the original numbers in t.
                count++;
            }

            while (count == m) {
                // If all characters are in the window, then we record the result and shrink it
                if (r - l + 1 < minLength) {
                    minLength = r - l + 1;
                    startIdx = l;
                }

                // when shrink, increase the frequency of char in the frequency map (put back in).
                // It frequency gets more than 0 => the windows no more contains enough characters, then decrease count and stop srhinking.
                char removing = sChars[l];
                frequency[removing]++;
                if (frequency[removing] > 0) {
                    count--;
                }
                l++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLength);
    }
}

// Solution 1: usual sliding window, we need
//   - hash table to calculate the frequency of characters in t
//   - count variable to know that we already match all characters
//   - string to store result (can store as indexes later

// Time complexity: O(m + n) = O(max(m, n))
// Space complexity: O(m)
class Solution {
    public String minWindow(String s, String t) {
        int n = s.length(), m = t.length();
        HashMap<Character, Integer> frequency = new HashMap<>();
        for (char c : t.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        String res = s + "a";   // Make the result a bit larger, so easily check if the result is valid or not
        int count = 0;  // How many words in t have been included in the window
        int l = 0;
        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);
            if (frequency.containsKey(c)) {
                // Only modify the frequency map if the character is within it
                // Notice that now, the frequency map can contains negative values
                int newFrequency = frequency.get(c) - 1;
                frequency.put(c, newFrequency);
                if (newFrequency >= 0) {
                    count++;
                }
            }

            while (count == m) {
                // If all characters are in the window, then we record the result and shrink it
                if (r - l + 1 < res.length()) {
                    res = s.substring(l, r + 1);
                }
                char removing = s.charAt(l);
                if (frequency.containsKey(removing)) {
                    // when shrink, increase the frequency of char in the frequency map (put back in).
                    // It frequency gets more than 0 => the windows no more contains enough characters, then decrease count and stop srhinking.
                    int oldFrequency = frequency.get(removing);
                    frequency.put(removing, oldFrequency + 1);
                    if (oldFrequency >= 0) {
                        count--;
                    }
                }
                l++;
            }
        }
        return res.length() == n + 1 ? "" : res;
    }
}
