// https://leetcode.com/problems/gas-station/
// Solution: The key point is that:
//  - if gas >= cost => we definitely can finish the curcuit
//  - the solution is guaranteed to be unique => we can use new starting point whenever the gas falls below 0. Defail in comment.
// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sumGas = 0;
        for (int g : gas) {
            sumGas += g;
        }
        int sumCost = 0;
        for (int c : cost) {
            sumCost += c;
        }
        // If sum of gas < sum of cost => we can not make it
        if (sumGas < sumCost) return -1;

        int res = 0;
        int curGas = 0;
        // We only need to loop to n (not need full circular) because we check for the completion condition before (sumGas >= sumCost)
        for (int i = 0; i < n; i++) {
            if (curGas >= 0) {
                // The previous gas is more than enough 
                // => current index cannot be starting point, because the starting point is unique.
                // If cur i is the result => previous i can also be => break the rule
                curGas += gas[i] - cost[i];
            } else {
                // The previous choice is not sufficient, we try to choose this as a starting point.
                res = i;
                curGas = gas[i] - cost[i];
            }
        }
        return res;
    }
}
