// https://leetcode.com/problems/jump-game-ii/description/

// Solution 1: Greedy: Key observations: steps[i] = min jumps need to make it to i
// => for i < j, steps[i] always <= steps[j]
// => Assume at i, we can jump to max
// => for j > i and j <= max, 
//    -   if at j, we can jump further than max => extends to new max with steps[from max -> newMax] = steps[j] + 1
//             Because j is the first item that can jump to those item => jump from j is guaranteed to be the minimum step
//    - Otherwise, ignore, because if we need to jump from j => steps[j] always >= steps[i] => jump from steps[i] guaranteed the minimum

// Time complexity: O(n)
// Space complexity: O(n)

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

// Solution 2: Greedy: think of it as BFS or level-order traversal => minimum path to items.
// reference: https://leetcode.com/problems/jump-game-ii/solutions/1192401/easy-solutions-w-explanation-optimizations-from-brute-force-to-dp-to-greedy-bfs/?envType=study-plan-v2&envId=top-interview-150
// e.g. [2, 3, 1, 1, 4]
// queue = [2], level = 0 
// => pop 2 => queue = [3, 1] => lastJumpedPos = 2, jump 1
// => pop 3 => queue = [1, 1, 4] (level 1 + 2) => lastJumpedPos = 2, maxReachable = 4, jump = 1
// => pop 1 => queue = [1, 4] (level = 2) => lastJumpedPos = 4,  jump = 2 => end

// Time complexity: O(n)
// Space complexity: O(1)

int jump(vector<int>& nums) {
	int n = size(nums), i = 0, maxReachable = 0, lastJumpedPos = 0, jumps = 0;
	while(lastJumpedPos < n - 1) {  // loop till last jump hasn't taken us till the end
		maxReachable = max(maxReachable, i + nums[i]);  // furthest index reachable on the next level from current level
		if(i == lastJumpedPos) {			  // current level has been iterated & maxReachable position on next level has been finalised
			lastJumpedPos = maxReachable;     // so just move to that maxReachable position
			jumps++;                          // and increment the level
	// NOTE: jump^ only gets updated after we iterate all possible jumps from previous level
	//       This ensures jumps will only store minimum jump required to reach lastJumpedPos
		}            
		i++;
	}
	return jumps;
}

// Solution 3: DP, self-done, just for ref:
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; (j <= i + nums[i]) && j < n; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n-1];
    }
}
