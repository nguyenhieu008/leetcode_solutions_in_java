// Solution 2: Because all words are in the same length, consider each word as "a character". 
// So the problems is changed to, find the subarray in string s that contains all "characters" in words.
/*
Time complexity: O(m + l * (n / l) * l) = O(m + n * l) = O(n * l)
where, m = number of words, n = string length, l = word length
(l * (n / l) * l) = (window size time for i loop) * (for r loop over n / step size of window size) * (substring of window size)
Space complexity: O(m * l)
    */

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // to simplify problem, each word can be consider "1 char" = windowSize = words[0].length()
        // and when looping s, we move the pointer "1 char" as well ()
        // so the problem come down to:
        // Find a substring in s, that contains all "1 char" characters => We can use simple sliding window technique.
        // Just notice that, we should do that loop (0 -> windowSize time)

        int n = s.length(), m = words.length;
        int windowSize = words[0].length();
        List<Integer> res = new ArrayList<>();
        HashMap<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < windowSize; i++) {
            List<Integer> temp = findSubstringWindow(s, frequency, i, windowSize, m);
            res.addAll(temp);
        }
        return res;
    }

    private List<Integer> findSubstringWindow(String s, HashMap<String, Integer> frequency, int startIdx, int windowSize, int m) {
        int n = s.length();
        HashMap<String, Integer> seenFrequency = new HashMap<>();
        int count = 0;
        List<Integer> res = new ArrayList<>();
        int l = startIdx;
        for (int r = startIdx; r + windowSize <= n; r += windowSize) {
            String ss = s.substring(r, r + windowSize);
            seenFrequency.put(ss, seenFrequency.getOrDefault(ss, 0) + 1);
            count++;
            while (l <= r && (!frequency.containsKey(ss) || seenFrequency.get(ss) > frequency.get(ss))) {
                String removedSs = s.substring(l, l + windowSize);
                seenFrequency.put(removedSs, seenFrequency.get(removedSs) - 1);
                count--;
                l += windowSize;
            }
            if (count == m) {
                res.add(l);
            }
        }
        return res;
    }
}

// Solution 1

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
