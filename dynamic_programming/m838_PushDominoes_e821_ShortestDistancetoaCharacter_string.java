// https://leetcode.com/problems/push-dominoes/description/

class Solution {
    public String pushDominoes(String dominoes) {
        // need:
        // n
        // left2right: int[] _ distance to the nearest R
        // right2left: int[] - distance to the nearest L
        // res: String

        int n = dominoes.length();
        char[] dominoChars = dominoes.toCharArray();

        int[] left2right = new int[n];
        int[] right2left = new int[n];

        final int INFINITY = 1_000_000; 

        for (int i = 0; i < n; i++) {
            // from 0
            if (dominoChars[i] == 'R') {
                left2right[i] = 0;
            } else if (dominoChars[i] == 'L') {
                left2right[i] = INFINITY;
            } else {
                if (i > 0) {
                    left2right[i] = Math.min(INFINITY, left2right[i-1] + 1);
                } else {
                    left2right[i] = INFINITY;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            // from 0
            if (dominoChars[i] == 'L') {
                right2left[i] = 0;
            } else if (dominoChars[i] == 'R') {
                right2left[i] = INFINITY;
            } else {
                if (i < n - 1) {
                    right2left[i] = Math.min(INFINITY, right2left[i+1] + 1);
                } else {
                    right2left[i] = INFINITY;
                }
            }
        }

        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            if (left2right[i] < right2left[i]) {
                // right bias
                res[i] = 'R';
            } else if (left2right[i] > right2left[i]) {
                res[i] = 'L';
            } else {
                res[i] = '.';
            }
        }
        
        return new String(res);
    }
}

// https://leetcode.com/problems/shortest-distance-to-a-character/
class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] distanceLeft = new int[n], distanceRight = new int[n];
        int prev = n;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                prev = 0;
            } else {
                prev++; // if no c on the left, the distance can be > n, it's does not have negative impact, because the c is guaranteed to exist
            }
            distanceLeft[i] = prev;
        }

        prev = n;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                prev = 0;
            } else {
                prev++; // Same, prev can > n
            }
            distanceRight[i] = prev;
        }
        
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.min(distanceLeft[i], distanceRight[i]);
        }
        return res;
    }
}
