// https://leetcode.com/problems/combination-sum/description/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> curList = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, curList, res, 0);
        return res;
    }
    private void backtrack(int[] candidates, int target, int curSum, List<Integer> curList, List<List<Integer>> res, int startIdx) {
        if (curSum == target) {
            res.add(new ArrayList<>(curList));
            return;
        }
        for (int i = startIdx; i < candidates.length; i++) {
            if (curSum + candidates[i] > target) continue;
            curList.add(candidates[i]);
            backtrack(candidates, target, curSum + candidates[i], curList, res, i);
            curList.removeLast();
        }
    }
}
