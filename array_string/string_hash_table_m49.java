// https://leetcode.com/problems/group-anagrams/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // length = 1e4 => O(n^2) can work
        // s.length = 10 => O(n^2 * m) = 1e10 => cannot work
        // english letters => can duplicate => must count frequency by hash map
        // How to define a group? How can compare a new string with a group? Can we create n hash map and compare them?
        // Below is an intelligent way to make the hash key

        // if (strs == null || strs.length == 0) return new ArrayList<>();
        // Map<String, List<String>> map = new HashMap<>();
        // for (String s : strs) {
        //     // make char[] so it can be converted to string
        //     // but each char item is instead use as integer to count the frequency
        //     // after that, we have a frequency table => convert it to a unique string to use as key for hash table
        //     // notice that the string cannot be displayed as usual, because its char values are the frequency => very likely to be special character.
        //     char[] ca = new char[26];
        //     for (char c : s.toCharArray()) ca[c - 'a']++;
        //     String keyStr = String.valueOf(ca);
        //     if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
        //     map.get(keyStr).add(s);
        // }
        // return new ArrayList<>(map.values());

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars);
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
