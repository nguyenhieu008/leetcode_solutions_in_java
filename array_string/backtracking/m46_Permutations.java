// https://leetcode.com/problems/permutations/description/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        List<List<Integer>> res = new ArrayList<>();
        listPermutations(nums, visited, new ArrayList<Integer>(), res);
        return res;
    }
    private void listPermutations(int[] nums, boolean[] visited, List<Integer> curPermutation, List<List<Integer>> res) {
        if (curPermutation.size() == nums.length) {
            res.add(new ArrayList<>(curPermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curPermutation.add(nums[i]);
                listPermutations(nums, visited, curPermutation, res);
                curPermutation.removeLast();
                visited[i] = false;
            }
        }
    }
}
