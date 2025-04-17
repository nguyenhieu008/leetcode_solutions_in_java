// https://leetcode.com/problems/generate-parentheses

// At each item, we to add '(' or ')'. We determine based on the number of open and close parentheses already added.
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        generate(n, 0, 0, "", res);
        return res;
    }
    private void generate(int n, int open, int close, String cur, List<String> res) {
        if (open == close && open == n) {
            res.add(cur);
            return;
        }
        if (open < n) {
            generate(n, open + 1, close, cur + "(", res);
        }
        if (close < open) {
            generate(n, open, close + 1, cur + ")", res);
        }
    }
}
