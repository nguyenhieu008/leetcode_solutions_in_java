// https://leetcode.com/problems/word-break/description/

// Solution 4: Build a trie. For each i, if i == 0 || dp[i-1] == true => can break the words up to (i - 1)
// => traverse the trie from i, if we meet an isWord == true, then mark dp[j] = true;
// Continue to the end and return dp[n - 1]
// Time complexity: O(n^2 + m * k), m * k for building trie, n^2 for iterate the string s
// Space complexity: O(n + m * k), where m = number of words, k = average length of words, n for the dp array

class Solution {
    class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children;

        public TrieNode() {
            this.children = new HashMap<>();
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();

        for (String word : wordDict) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isWord = true;
        }

        int n = s.length();
        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (i != 0 && !dp[i - 1]) {
                continue;
            }

            TrieNode node = root;
            for (int j = i; j < n; j++) {
                if (node.children.containsKey(s.charAt(j))) {
                    node = node.children.get(s.charAt(j));
                    if (node.isWord) {
                        dp[j] = true;
                    }
                } else {
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}

// Solution 3: DP, same as solution 2 but use bottom-up
// At each i, we loop through all possible words and check that whether the dp[i - word.length] = true and word == substring from [i - w + 1,  i]
// Time complexity: O(n * m * k), n for loop through array, m for loop through words, k for the substring of average k characters.
// Space complexity: O(n) for dp
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (String word : wordDict) {
                int w = word.length();
                if (w > i + 1) {
                    continue;
                }
                if ((w == i + 1 || dp[i - w]) && word.equals(s.substring(i - w + 1, i + 1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}

// Solution 2: DP recursive, same as solution 3 but use top-down approach.
// For the result of (i), we use memo array to store with 3 states (-1 = not calculated, 0 = false, 1 = true)
// At each dp call, we loop through all the words and check that the memo[i-w] is true, and string string from [i-w+1, i+1) == word
// Time complexity: O(m * n * k)
// Space complexity: O(n + n) = O(n) for stack + memo
class Solution {
    private String s;
    private List<String> wordDict;
    private int[] memo;

    private boolean dp(int i) {
        if (i < 0) {
            return true;
        }
        if (memo[i] != -1) {
            return memo[i] == 1;
        }
        for (String word : wordDict) {
            int w = word.length();
            if (i - w + 1 < 0) {
                continue;
            }
            if (s.substring(i - w + 1, i + 1).equals(word) && dp(i - w)) {
                memo[i] = 1;
                return true;
            }
        }
        memo[i] = 0;
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = wordDict;
        this.memo = new int[s.length()];
        Arrays.fill(this.memo, -1);
        return dp(s.length() - 1);
    }
}

// Solution 1: BFS, reference: https://leetcode.com/problems/word-break/solutions/3615672/word-break/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        HashSet<String> dict = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] seen = new boolean[n + 1];

        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (start == n) {
                return true;
            }

            for (int end = start + 1; end <= n; end++) {
                if (seen[end]) {
                    continue;
                }
                String str = s.substring(start, end);
                if (dict.contains(str)) {
                    queue.offer(end);
                    seen[end] = true;
                }
            }
        }
        return false;
    }
}

// Solution 0: self-done, DP
// At each i, we check that whether we break words that ends at i
// => Loop for all j before i, if canBreak[j] => check if substring from [j+1, i] is in the set
// => this is another approach, rather than we iterate through the list of strings, we iterate through possible substrings.
// Time complexity: O(n^3 + m * k) , i, j run n times, substring take O(n), m*k for building set
// Space complexity: O(n + m * k) for set, n for dp table
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] canBreak = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = -1; j < i; j++) {
                if (j == -1 || canBreak[j]) {
                    String str = s.substring(j + 1, i + 1);
                    if (dict.contains(str)) {
                        canBreak[i] = true;
                    }
                }
            }
        }
        return canBreak[n - 1];
    }
}
