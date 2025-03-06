// https://leetcode.com/problems/koko-eating-bananas/description/

// Solution: Assume k is the minimum point, so if i < less k, it does not have enough time to eat all withn h hours. Otherwise, all points to the right are able to eat.
// Because we find minimum, so we force the target point to the r pointer.
// Trick here for easy coding is that, the helper function should return true/false, so it's easier to code in main func

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int l = 1, r = Integer.MAX_VALUE;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;

            if (canEat(piles, mid, h)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean canEat(int[] piles, int k, int required) {
        int h = 0;
        for (int p : piles) {
            h += p / k;
            if (p % k != 0) {
                h++;
            }
        }
        return h <= required;
    }
}
