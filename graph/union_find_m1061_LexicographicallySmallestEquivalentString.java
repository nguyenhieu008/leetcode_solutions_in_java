// https://leetcode.com/problems/lexicographically-smallest-equivalent-string/

// We must label the sets of character. But because the labels may change, so we must use disjoint set/union find.
// We can do it right in the code, but separate to a class to be clean and understand the structure.

// Time complexity: O(n), Because the depth of the set is at most 26, but we also use path compression, so the find complexity is approximately O(1) => union O(1), totally O(n)
// Space complexity: O(26) = O(1). String builder is use to build result and not count to the complexity.
class Solution {
    class DisjointSet {
        Map<Character, Character> parent; // smallest character for a set

        DisjointSet() {
            parent = new HashMap<>();
            for (char c = 'a'; c <= 'z'; c++) {
                parent.put(c, c);
            }
        }

        char find(char c) {
            if (parent.get(c) != c) {
                parent.put(c, find(parent.get(c))); // Set to smallest
            }
            return parent.get(c);
        }

        void union(char c1, char c2) {
            char root1 = find(c1), root2 = find(c2);
            if (root1 == root2) {
                return;
            }
            if (root1 < root2) {
                parent.put(root2, root1);
            } else {
                parent.put(root1, root2);
            }
        }
    }
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        DisjointSet sets = new DisjointSet();
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            sets.union(c1, c2);
        }
        StringBuilder res = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            res.append(sets.find(c));
        }
        return res.toString();
    }
}
