// https://leetcode.com/problems/combinations/
// Time complexity: O(2^n), beware that there is more n!/(n-k)!*k! * n, because of copying when: res.add(new ArrayList<>(curList)); but it's smaller than 2^n => ignore

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        listCombinations(n, k, 1, new ArrayList<>(), res);
        return res;
    }
    
    private void listCombinations(int n, int remain, int curItem, List<Integer> curList, List<List<Integer>> res) {
        if (n - curItem + 1 < remain) {
            return;
        }
        if (remain == 0) {
            res.add(new ArrayList<>(curList));
            return;
        }

        listCombinations(n, remain, curItem + 1, curList, res);
        curList.add(curItem);
        listCombinations(n, remain - 1, curItem + 1, curList, res);
        curList.removeLast();
    }
}
