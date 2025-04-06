// https://leetcode.com/problems/h-index/

// Solution 1: self-done, try distribution counting

class Solution {
    public int hIndex(int[] citations) {
        // input range: citations[i] <= 1000 => can make hash map of (num citations, paper count)
        // n = 5 * 1e3 => O(n^2) should be fine
        // output: a citation number (h), where there are more than h "counts of paper" with citations >= h
        // Maybe using tree
        // But try to use hash map with 1000 keys first
        // Yeah, it works for the first implementation
        // Time complexity: O(max(n=5000, citation[i]=1000))=O(n)
        // Space complexity: O(1000)

        int n = citations.length;
        int[] citationCount = new int[1001];

        for (int c : citations) {
            citationCount[c]++;
        }

        int totalPaperWithMoreCitations = 0;
        for (int i = 1000; i >= 0; i--) {
            totalPaperWithMoreCitations += citationCount[i];
            if (totalPaperWithMoreCitations >= i) {
                return i;
            }
        }
        return 0;

        // For the next implementation, try to use tree to store (citation nums, number of paper for that citation nums)
        // Then iterate the map from largest to lowest key, accumulate the num of paper that have greater citations than the key
        // Then try to use binary search.
        // Time complexity: O(max(n=5000, log(citations[i] = 1000) = 10)) = O(n)
        // Space complexity: O(1000) for tree

        // int n = citations.length;
        // TreeMap<Integer, Integer> citationCount = new TreeMap<>();

        // for (int c : citations) {
        //     citationCount.put(c, citationCount.getOrDefault(c, 0) + 1);
        // }
        // int totalPapersWithMoreCitations = 0;
        // for (int c : citationCount.descendingKeySet()) {
        //     totalPapersWithMoreCitations += citationCount.get(c);
        //     citationCount.put(c, totalPapersWithMoreCitations);
        // }

        // int l = 0, r = 1001;
        // int res = 0;
        // while (r - l >= 0) {
        //     int mid = l + (r - l) / 2;
        //     Integer greaterEqualCitation = citationCount.ceilingKey(mid);
        //     if (greaterEqualCitation == null) {
        //         r = mid - 1;
        //         continue;
        //     }
        //     int numPapersWithMoreCitations = citationCount.get(greaterEqualCitation);
        //     if (numPapersWithMoreCitations >= mid) {
        //         l = mid + 1;
        //         res = mid;
        //     } else {
        //         r = mid - 1;
        //     }
        // }
        // return res;
    }
}

// Solution 2: reference, sorting
// After sorted, iterate from left to right means there are (n - i) papers with citations >= citations[i].
// As i increased, the number of papers decreases.
// => we want the first i, that satisfy citations[i] >= (n - i) => we have (n - i) papers with citations >= (n - i) => h-index = (n - i)
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);

        for (int i = 0; i < n; i++) {
            if (citations[i] >= n - i) {
                return n - i;
            }
        }

        return 0; 
    }
}
