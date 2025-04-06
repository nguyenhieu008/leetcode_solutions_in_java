// https://leetcode.com/problems/candy/description/


// Solution 1: greedy, self-done.
// The leftCandy means the candy should be given to the child, base of the ratings of left children. Same for the other directions.
// The final candies will be the max of 2 directions.
// So what is the intuition? res[i] = max(leftCandy[i], rightCandy[i])
// Let's examine: a[i] >= a[i+1] => leftCandy[i+1] reset to 1. It totally bases on the the right, which is done by the other direction.
//                                 The question is: Any chance that a[i] > a[i+1] but res[i] < res[i+1]??? No way, because in that case, res[i+1] bases on the rightCandy[i+1].
//                                                  And because a[i] > a[i+1] => rightCandy[i] = rightCandy[i+1] + 1 => will always greater => satisfy condition
//             if a[i] < a[i+1] => leftCandy[i+1] > leftCandy[i] already satisfy condition
class Solution {
    public int candy(int[] ratings) {
        // at least one candy
        // if a[i] > a[i - 1] and a[i+1] => candy[i] > candy[i-1] and candy[i+1]
        // input: n <= 2 * 10^4 => O(n^2) can work
        //         a[i] <= 2 * 10^4
        // what if a[i] == a[i+1]?
        // e.g: [0, 1, 1] => [1, 2, 1] => res = 4?


        // [1, 0, 2] => left = [1, 1, 2]    => res = [2, 1, 2]
        //              right = [2, 1, 1]
        // [1, 2, 2] => left = [1, 2, 1]    => res = [1, 2, 1]
        //              right = [1, 1, 1]
        // [1, 2, 3, 3, 2, 1] => left = [1, 2, 3, 1, 1, 1]
        //                      right = [1, 1, 1, 3, 2, 1]
        // [1, 2, 10, 7, 6, 5, 4, 1, 3, 2, 1] => left = [1, 2, 3, 1, 1, 1, 1, 1, 2, 1, 1]
        //                                       right =[1, 1, 6, 5, 4, 3, 2, 1, 3, 2, 1]

        int n = ratings.length;
        int[] leftCandy = new int[n];
        int[] rightCandy = new int[n];
        Arrays.fill(leftCandy, 1);
        Arrays.fill(rightCandy, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                leftCandy[i] = leftCandy[i-1] + 1;
            }
        }
        // We can merge into previous for, but it's a bit hard to read
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                rightCandy[i] = rightCandy[i+1] + 1;
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.max(leftCandy[i], rightCandy[i]);
        }
        
        return res;
    }
}

// Solution 2: reference only. Intuition: When going up => count number of increasing pile and for each 1, we are 1 unit higher.
// When going down => count number of decreasing piles, and because latest item is less than all, we need to increase all previous larger items by 1 => ret += down. But one notice,
// Need need to increase in case the larger pile is already the peak of the increasing side, and that peak >= peak in decreasing side.
// https://leetcode.com/problems/candy/solutions/4037646/99-20-greedy-two-one-pass/
class Solution:
    def candy(self, ratings: List[int]) -> int:
        if not ratings:
            return 0
        
        ret, up, down, peak = 1, 0, 0, 0
        
        for prev, curr in zip(ratings[:-1], ratings[1:]):
            if prev < curr:
                up, down, peak = up + 1, 0, up + 1
                ret += 1 + up
            elif prev == curr:
                up = down = peak = 0
                ret += 1
            else:
                up, down = 0, down + 1
                ret += 1 + down - int(peak >= down)
        
        return ret
