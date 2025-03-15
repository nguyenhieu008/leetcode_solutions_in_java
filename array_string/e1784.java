// https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/description/

// Solution: Because the first char is always '1', whenever we find a '1' that preceded by a '0' => There are more than 1 segment of '1'.
// because the first character does not have prev char. For easy implement, we intialize the prev char to '1' => we can handle the first character '1' without specific condition.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public boolean checkOnesSegment(String s) {
        char prev = '1';
        for (char c : s.toCharArray()) {
            if (c == '1' && prev == '0') {
                return false;
            } else {
                prev = c;
            }
        }
        return true;
    }
}
