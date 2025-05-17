// https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description/

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxNumber = 0;
        for (int c : candies) {
            maxNumber = Math.max(maxNumber, c);
        }

        List<Boolean> res = new ArrayList<>();
        for (int c : candies) {
            if (c + extraCandies >= maxNumber) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }
}
