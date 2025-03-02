

// Solution 3: Because 1 <= words[i].length <= 10 => score[i] <= 10 => we use 10-sized array to store score distribution, score[i]=  number of scores >= i
// for each queries[j], find score[queries[j]] in the score distribution array.
// Time complexity: O(10 * max(m, n))
// Space complexity: O(n), no need to store scores of queries

class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length, m = queries.length;
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = getScore(words[i]);
        }

        // count number of each score, from 1 -> 10
        int[] scoreCount = new int[11];
        for (int s : scores) {
            scoreCount[s]++;
        }

        // scoreCount[k] stores number of scores >= k
        int count = scoreCount[10];
        for (int k = 9; k > 0; k--) {
            count += scoreCount[k];
            scoreCount[k] = count;
        }

        int[] answer = new int[m];
        for (int j = 0; j < m; j++) {
            int sj = getScore(queries[j]);
            if (sj >= 10) {
                answer[j] = 0;
            } else {
                // scoreCount[sj + 1] means number of score > sj (f[queries[j]])
                answer[j] = scoreCount[sj + 1];
            }
        }

        return answer;

    }

    private int getScore(String word) {
        char minChar = 'z';
        int score = 0;

        for (char c : word.toCharArray()) {
            if (c < minChar) {
                minChar = c;
                score = 1;
            } else if (c == minChar) {
                score++;
            }
        }
        return score;
    }
}

// Solution 2: instead of finding the score array, we sort and use binary search
// Time complexity: O(m * max(10, log(n)))
// space complexity: O(n);


class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length, m = queries.length;
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = getScore(words[i]);
        }

        Arrays.sort(scores);
        int[] answer = new int[m];

        for (int j = 0; j < m; j++) {
            int queryScore = getScore(queries[j]);
            int l = 0, r = n;
            while (r - l > 0) {
                int mid = l + (r - l) / 2;
                if (scores[mid] <= queryScore) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }

            answer[j] = n - l;
        }

        return answer;
    }

    private int getScore(String word) {
        char minChar = 'z';
        int score = 0;

        for (char c : word.toCharArray()) {
            if (c < minChar) {
                minChar = c;
                score = 1;
            } else if (c == minChar) {
                score++;
            }
        }
        return score;
    }
}

// Solution 1: In order to get answer, for all the scores array and find any score that greater than f[queries[i]]
// Time complexity: O(n * m)
// Space complexity: O(max(m, n))
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length, m = queries.length;
        int[] scores = new int[n];
        int[] qScores = new int[m];

        for (int i = 0; i < n; i++) {
            scores[i] = getScore(words[i]);
        }
        for (int j = 0; j < m; j++) {
            qScores[j] = getScore(queries[j]);
        }

        int[] answer = new int[m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (scores[i] > qScores[j]) {
                    answer[j]++;
                }
            }
        }

        return answer;

    }

    private int getScore(String word) {
        char minChar = 'z';
        int score = 0;

        for (char c : word.toCharArray()) {
            if (c < minChar) {
                minChar = c;
                score = 1;
            } else if (c == minChar) {
                score++;
            }
        }
        return score;
    }
}
