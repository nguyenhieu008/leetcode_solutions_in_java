class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // s length = 1e4
        // words length = 5 * 1e3
        // words[i] length = 30
        // => total length = 15 * 1e4 > s
        // call, windowSize = words[i].length()

        // Can store that words in hash set => O(1) check
        // duplicate words okay => need hash map of <string, int> as frequency

        // starting from an index, continuously get the substring of windowSize and check if exists in the hast table with freq > 0
        // Instead of modify the original hash table, we create a new one that counts the current freq of words, and check that freq < freq in original frequency table
        // if all windows matched => add to result

        // Time complexity: O(n * m ^ 2) => not enough. TLE on last test case, but work for most

        int n = s.length(), m = words.length;
        int windowSize = words[0].length();
        HashMap<String, Integer> frequency = new HashMap<>();
        
        for (int w = 0; w < m; w++) {
            frequency.put(words[w], frequency.getOrDefault(words[w], 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= n - (m * windowSize); i++) { 
            // for all items, check maximum m windows (to match permutation)
            HashMap<String, Integer> seenFrequency = new HashMap<>(); // store frequency of words within the s

            boolean containAll = true;

            for (int w = 0; w < m; w++) {
                int ssIndex = i + w * windowSize;
                if (ssIndex >= n) {
                    containAll = false;
                    break;
                }
                String ss = s.substring(ssIndex, ssIndex + windowSize);
                
                if (frequency.containsKey(ss) && seenFrequency.getOrDefault(ss, 0) < frequency.get(ss)) {
                    seenFrequency.put(ss, seenFrequency.getOrDefault(ss, 0) + 1);
                } else {
                    containAll = false;
                    break;
                }
            }

            if (containAll) {
                res.add(i);
            }
        }


        return res;
    }
}
