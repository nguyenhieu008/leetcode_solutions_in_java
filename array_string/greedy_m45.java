// https://leetcode.com/problems/jump-game-ii/description/

// Key observations: steps[i] = min jumps need to make it to i
// => for i < j, steps[i] always <= steps[j]
// => Assume at i, we can jump to max
// => for j > i and j <= max, 
//    -   if at j, we can jump further than max => extends to new max with steps[from max -> newMax] = steps[j] + 1
//             Because j is the first item that can jump to those item => jump from j is guaranteed to be the minimum step
//    - Otherwise, ignore, because if we need to jump from j => steps[j] always >= steps[i] => jump from steps[i] guaranteed the minimum

class Solution {
    public int jump(int[] nums) {
        // n = 1e4 => O(n^2) okay
        // 0 <= nums[i] <= 1000 => no negative step.
        // solution guarantee

        // We can have 2 pointers
        //  cur = current position
        //  max = max possible index we can jump to at the moment
        // steps[i < max] = min jumps need to make it to to i
        // At each cur, if we can extend max then:
        //  -  Update max = cur + nums[cur]
        //  - while increment i to new max, update steps[i] = steps[cur] + 1
        // Then return steps[n-1]

        int n = nums.length;
        int[] steps = new int[n];

        for (int cur = 0, max = 1; cur < n; cur++) {
            while (max <= cur + nums[cur] && max < n) {
                steps[max] = steps[cur] + 1;
                max++;
            }
        }
        return steps[n-1];
    }
}
