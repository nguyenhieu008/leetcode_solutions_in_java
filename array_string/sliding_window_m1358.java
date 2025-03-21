// Solution: We keep track counts, when a window (subarray) satisfies the condition, we add all subarrays start from l to the end to the res, 
// it is (r - l), because we all remaining (r - l) ends for the subarray start from l will also satisfy the condition.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] count = new int[3];
        int l = 0, res = 0;

        for (int r = 0; r < n; r++) {
            count[s.charAt(r) - 'a']++;

            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                res += n - r;
                count[s.charAt(l) - 'a']--;
                l++;
            }
        }
        return res;
    }
}
