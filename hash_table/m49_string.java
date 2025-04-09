// https://leetcode.com/problems/group-anagrams/description/

// Same as: https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/array_string/string_hash_table_m49.java
// But try to use distribution counting sort.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            String sorted = sort(s);
            groups.putIfAbsent(sorted, new ArrayList<>());
            groups.get(sorted).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    private String sort(String s) {
        int[] frequency = new int[128];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            frequency[c]++;
        }
        int fillIdx = 0;
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < frequency[i]; j++) {
                chars[fillIdx] = (char)i;
                fillIdx++;
            } 
        }
        return new String(chars);
    }
}
