// https://leetcode.com/problems/replace-the-substring-for-balanced-string/description/


// Solution: We can form a balanced string if outside of the subarray, every character has frequency <= n / 4. 
// It means nothing exceed n/4 frequency, and we can replace the subarray with something that makes all character frequency = n/4.
// So we count the frequency first, then each time we put item into the window, we decrease the frequency, until the outside can form a balanced string.
// The we shrink the left, until it does not satisfy the condition. We need to update the result each shrink, in order to get the minimum possible subarray.
// Once possible edge case, because the window is intended to always have at least 1 item 
// => for the result 0 (no need to replace substring), we have another condition check possibleBalanced before running window

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int balancedString(String s) {
        int n = s.length();
        HashMap<Character, Integer> count = new HashMap<>(Map.of('Q', 0, 'R', 0, 'W', 0, 'E', 0));

        for (char c : s.toCharArray()) {
            count.put(c, count.get(c) + 1);
        }

        if (possibleBalanced(count, n)) return 0;

        int l = 0, res = n;
        for (int r = 0; r < n; r++) {
            count.put(s.charAt(r), count.get(s.charAt(r)) - 1);

            // We can check l < n here, then remove the condition: if (possibleBalanced(count, n)) return 0;
            // But I personally do not like it, as now the window can have l greater than r, and it adds more frequency than the original count.
            while (l <= r && possibleBalanced(count, n)) {
                res = Math.min(res, r - l + 1);
                count.put(s.charAt(l), count.get(s.charAt(l)) + 1);
                l++;
            }
        }
        return res;
    }

    private boolean possibleBalanced(HashMap<Character, Integer> count, int n) {
        return count.get('Q') <= n / 4 
            && count.get('W') <= n / 4
            && count.get('E') <= n / 4
            && count.get('R') <= n / 4;
    }
}
