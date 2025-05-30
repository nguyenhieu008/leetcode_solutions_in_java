// https://leetcode.com/problems/min-cost-climbing-stairs/

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // prevCost2 = min cost to get to the 2-previous-steps from current
        // prevCost2 + cost[i-2] can reach current
        // prevCost1 = min cost to get to the 1-previous-steps from current
        
        // Get min of both => then we will get min
        int prevCost2 = 0, prevCost1 = 0;
        for (int i = 2; i <= n; i++) {
            int curCost = Math.min(prevCost2 + cost[i - 2], prevCost1 + cost[i - 1]);
            prevCost2 = prevCost1;
            prevCost1 = curCost;
        }
        return prevCost1;
    }
}

// Solution 2: Do it the reverse direction
// costNext2 = min cost from the 2-next-steps to the top
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int costNext2 = cost[n - 1], costNext1 = cost[n - 2];

        for (int i = n - 3; i >= 0; i--) {
            int curCost = Math.min(costNext2, costNext1) + cost[i];
            costNext2 = costNext1;
            costNext1 = curCost;
        }
        return Math.min(costNext2, costNext1);
    }
}
