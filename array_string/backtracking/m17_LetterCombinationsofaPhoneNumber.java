// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

class Solution {
    // can make it as a list of string => easier to initalize.
    private List<Character>[] map = new List[10];

    Solution() {
        map[2] = Arrays.asList('a', 'b', 'c');
        map[3] = Arrays.asList('d', 'e', 'f');
        map[4] = Arrays.asList('g', 'h', 'i');
        map[5] = Arrays.asList('j', 'k', 'l');
        map[6] = Arrays.asList('m', 'n', 'o');
        map[7] = Arrays.asList('p', 'q', 'r', 's');
        map[8] = Arrays.asList('t', 'u', 'v');
        map[9] = Arrays.asList('w', 'x', 'y', 'z');
    }
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new LinkedList<>();
        }
        return construct(digits, 0, new StringBuilder());
    }
    private List<String> construct(String digits, int idx, StringBuilder sb) {
        if (idx >= digits.length()) {
            return Arrays.asList(sb.toString());
        }
        int digit = digits.charAt(idx) - '0';
        List<String> res = new LinkedList<>();
        for (Character c : map[digit]) {
            sb.append(c);
            res.addAll(construct(digits, idx + 1, sb));
            sb.deleteCharAt(sb.length() - 1);
        }
        return res;
    }
}
