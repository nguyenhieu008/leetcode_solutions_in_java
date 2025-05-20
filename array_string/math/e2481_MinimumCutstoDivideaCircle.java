// https://leetcode.com/problems/minimum-cuts-to-divide-a-circle/

class Solution {
    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            // If even slices, first we cut by diameter, then cut each half to n/2 slices.
            // We need (n / 2 - 1) cuts to do that, then it's reflected to the other side
            // => no need to cut more => total = 1 + (n / 2 - 1) = n / 2.
            return n / 2;
        }

        // If odd slices, we cannot cut by diameter => cut one-by-one
        return n;
    }
}
